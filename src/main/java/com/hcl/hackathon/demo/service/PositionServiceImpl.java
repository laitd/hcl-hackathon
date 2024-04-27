package com.hcl.hackathon.demo.service;

import com.hcl.hackathon.demo.configuration.exceptionHandler.TradingException;
import com.hcl.hackathon.demo.constants.StatusCode;
import com.hcl.hackathon.demo.constants.TradeType;
import com.hcl.hackathon.demo.domain.position.TradeRequest;
import com.hcl.hackathon.demo.domain.position.TradeResponse;
import com.hcl.hackathon.demo.domain.position.PositionResponse;
import com.hcl.hackathon.demo.entity.portfolio.Position;
import com.hcl.hackathon.demo.mapper.PositionMapper;
import com.hcl.hackathon.demo.repository.PositionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PositionServiceImpl implements PositionService {

  private PositionRepository positionRepository;
  private PositionMapper positionMapper;

  @Override
  @Transactional
  public TradeResponse trade(TradeRequest positionRequest) {
    Position position;
    var previousPositionOptional = positionRepository.getByCustomerIdAndInstrumentId(positionRequest.getCustomerId(), positionRequest.getInstrumentId());
    var transactionRef = generateTransactionRef();
    if (previousPositionOptional.isPresent()) {
      //update instrument
      position = updatePositionInstrument(previousPositionOptional.get(), positionRequest, transactionRef);
    } else {
      //insert new instrument to position
      if (TradeType.sell.equals(positionRequest.getTradeType())) {
        throw new TradingException(StatusCode.TRADE_NO_INSTRUMENT.getCode(), StatusCode.TRADE_NO_INSTRUMENT.getDesc());
      }
      //handle BUY instrument
      position = positionMapper.toEntity(positionRequest);
      position.setTransactionRef(transactionRef);
      position = positionRepository.save(position);
    }
    TradeResponse response = new TradeResponse();
    response.setInstrumentId(positionRequest.getInstrumentId());
    response.setUnits(position.getUnits());

    //TODO integrate with Kafka to send audit data to audit service
    return response;
  }

  private String generateTransactionRef(){
    return UUID.randomUUID().toString();
  }

  private Position updatePositionInstrument(Position previousPosition, TradeRequest positionRequest, String transactionRef) {
    if (TradeType.buy.equals(positionRequest.getTradeType())) {
      previousPosition.setUnits(previousPosition.getUnits() + positionRequest.getUnits());
      previousPosition.setTransactionRef(transactionRef);
      return positionRepository.save(previousPosition);
    }

    //SELL handle
    Position position;
    int newUnit = previousPosition.getUnits() - positionRequest.getUnits();
    if (newUnit < 0) {
      throw new TradingException(StatusCode.TRADE_NOT_ENOUGH_INSTRUMENT.getCode(), StatusCode.TRADE_NOT_ENOUGH_INSTRUMENT.getDesc());
    }
    if (newUnit == 0) {
      positionRepository.delete(previousPosition);
      position = previousPosition;
      position.setUnits(0);
    } else {
      previousPosition.setUnits(newUnit);
      previousPosition.setTransactionRef(transactionRef);
      position = positionRepository.save(previousPosition);
    }
    return position;
  }

  public List<PositionResponse> findMyPositions(UUID customerId, int pageNumber, int pageSize) {
    var pageable = PageRequest.of(pageNumber, pageSize);
    var data = positionRepository.findPositionByCustomerId(customerId, pageable);
    return data.stream().map(p -> {
      PositionResponse positionResponse = new PositionResponse();
      positionResponse.setInstrumentId(p.getInstrumentId());
      positionResponse.setUnits(p.getUnits());
      return positionResponse;
    }).collect(Collectors.toList());
  }
}
