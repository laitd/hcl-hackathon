package com.hcl.hackathon.demo.service;

import com.hcl.hackathon.demo.configuration.exceptionHandler.TradingException;
import com.hcl.hackathon.demo.constants.TradeType;
import com.hcl.hackathon.demo.domain.position.TradeRequest;
import com.hcl.hackathon.demo.entity.portfolio.Position;
import com.hcl.hackathon.demo.mapper.PositionMapper;
import com.hcl.hackathon.demo.repository.PositionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class PositionServiceImplTest {

  @Mock
  private PositionRepository positionRepository;

  @Mock
  private PositionMapper positionMapper;

  @InjectMocks
  private PositionServiceImpl positionService;

  public PositionServiceImplTest() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testTrade_NewPosition() {
    // Arrange
    TradeRequest request = new TradeRequest();
    request.setTradeType(TradeType.buy);
    request.setCustomerId(UUID.randomUUID());
    request.setInstrumentId(1l);
    request.setUnits(100);

    Position position = new Position();
    when(positionMapper.toEntity(request)).thenReturn(position);
    when(positionRepository.getByCustomerIdAndInstrumentId(request.getCustomerId(), request.getInstrumentId())).thenReturn(Optional.empty());

    Position responseMock = new Position();
    responseMock.setUnits(100);
    when(positionRepository.save(position)).thenReturn(responseMock);

    // Act
    var response = positionService.trade(request);

    // Assert
    assertNotNull(response);
    assertEquals(request.getInstrumentId(), response.getInstrumentId());
    assertEquals(100, response.getUnits());
    verify(positionRepository).getByCustomerIdAndInstrumentId(request.getCustomerId(), request.getInstrumentId());
    verify(positionRepository).save(position);
  }

  @Test
  void testTrade_UpdatePosition() {
    // Arrange
    TradeRequest request = new TradeRequest();
    request.setTradeType(TradeType.sell);
    request.setCustomerId(UUID.randomUUID());
    request.setInstrumentId(1l);
    request.setUnits(50);

    Position previousPosition = new Position();
    previousPosition.setUnits(100);
    when(positionRepository.getByCustomerIdAndInstrumentId(request.getCustomerId(), request.getInstrumentId())).thenReturn(Optional.of(previousPosition));
    when(positionRepository.save(any())).thenReturn(previousPosition);

    // Act
    var response = positionService.trade(request);

    // Assert
    assertNotNull(response);
    assertEquals(request.getInstrumentId(), response.getInstrumentId());
    assertEquals(50, response.getUnits());
    verify(positionRepository).getByCustomerIdAndInstrumentId(request.getCustomerId(), request.getInstrumentId());
    verify(positionRepository).save(previousPosition);
  }

  @Test
  void testTrade_NotEnoughUnitsToSell() {
    // Arrange
    TradeRequest request = new TradeRequest();
    request.setTradeType(TradeType.sell);
    request.setCustomerId(UUID.randomUUID());
    request.setInstrumentId(1l);
    request.setUnits(150);

    Position previousPosition = new Position();
    previousPosition.setUnits(100);
    when(positionRepository.getByCustomerIdAndInstrumentId(request.getCustomerId(), request.getInstrumentId())).thenReturn(Optional.of(previousPosition));

    // Act & Assert
    assertThrows(TradingException.class, () -> positionService.trade(request));
    verify(positionRepository).getByCustomerIdAndInstrumentId(request.getCustomerId(), request.getInstrumentId());

    verify(positionRepository, times(0)).save(any());
  }
}