package com.hcl.hackathon.demo.controller;

import com.hcl.hackathon.demo.domain.portfolio.PortfolioResponse;
import com.hcl.hackathon.demo.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.hcl.hackathon.demo.configuration.Constants.BY_CUSTOMER_ID;
import static com.hcl.hackathon.demo.configuration.Constants.PORTFOLIOS;

@RestController
@RequestMapping(value = PORTFOLIOS)
public class PortfolioController {
    @Autowired
    private PortfolioService portfolioService;

    @GetMapping(value = BY_CUSTOMER_ID)
    public ResponseEntity<PortfolioResponse> portfolio(@PathVariable UUID customerId){
        return ResponseEntity.status(HttpStatus.OK).body(portfolioService.getCustomerPortfolio(customerId));
    }
}
