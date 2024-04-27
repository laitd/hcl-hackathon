package com.hcl.hackathon.demo.domain.portfolio;

import com.hcl.hackathon.demo.constants.InvestmentStrategy;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class PortfolioDTO {
    private UUID customerId;
    private String customerName;
    private String portfolioNumber;
    private BigDecimal portfolioValue;
    private Double currentPerformance;
    private InvestmentStrategy investmentStrategy;
}
