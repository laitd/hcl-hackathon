package com.hcl.hackathon.demo;
import com.hcl.hackathon.demo.configuration.exceptionHandler.NotFoundException;
import com.hcl.hackathon.demo.constants.InvestmentStrategy;
import com.hcl.hackathon.demo.constants.StatusCode;
import com.hcl.hackathon.demo.domain.portfolio.PortfolioResponse;
import com.hcl.hackathon.demo.entity.portfolio.Portfolio;
import com.hcl.hackathon.demo.repository.PortfolioRepository;
import com.hcl.hackathon.demo.service.PortfolioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PortfolioDTOServiceTest {

    @Mock
    private PortfolioRepository portfolioRepository;

    @InjectMocks
    private PortfolioService portfolioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCustomerPortfolio_WhenExists() {
        UUID customerId = UUID.randomUUID();
        Portfolio existingPortfolio = new Portfolio();
        existingPortfolio.setCustomerName("Test");
        existingPortfolio.setPortfolioValue(new BigDecimal(100));
        existingPortfolio.setPortfolioNumber("5555444444");
        existingPortfolio.setInvestmentStrategy(InvestmentStrategy.SAFE);
        existingPortfolio.setCurrentPerformance(20d);

        when(portfolioRepository.findById(customerId)).thenReturn(Optional.of(existingPortfolio));

        PortfolioResponse response = portfolioService.getCustomerPortfolio(customerId);

        assertEquals(existingPortfolio, response.getData());
        assertEquals(StatusCode.SUCCESS.getCode(), response.getStatusCode());
        assertEquals(StatusCode.SUCCESS.getDesc(), response.getStatusMessage());

        verify(portfolioRepository, times(1)).findById(customerId);
    }

    @Test
    public void testGetCustomerPortfolio_WhenNotExists() {
        UUID customerId = UUID.randomUUID();

        when(portfolioRepository.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> portfolioService.getCustomerPortfolio(customerId));

        verify(portfolioRepository, times(1)).findById(customerId);
    }
}