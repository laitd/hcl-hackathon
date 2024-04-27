package com.hcl.hackathon.demo.service;

import com.hcl.hackathon.demo.configuration.exceptionHandler.NotFoundException;
import com.hcl.hackathon.demo.constants.StatusCode;
import com.hcl.hackathon.demo.domain.portfolio.PortfolioDTO;
import com.hcl.hackathon.demo.domain.portfolio.PortfolioResponse;
import com.hcl.hackathon.demo.entity.portfolio.Portfolio;
import com.hcl.hackathon.demo.repository.PortfolioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PortfolioService {
    @Autowired
    private PortfolioRepository portfolioRepository;
    public PortfolioResponse getCustomerPortfolio(UUID customerId){
        final Optional<Portfolio> byId = portfolioRepository.findById(customerId);
        PortfolioResponse response = new PortfolioResponse();
        if(byId.isPresent()){
            PortfolioDTO portfolio = new PortfolioDTO();
            BeanUtils.copyProperties(byId.get(), portfolio);
            response.setData(byId.get());
            response.setStatusCode(StatusCode.SUCCESS.getCode());
            response.setStatusMessage(StatusCode.SUCCESS.getDesc());
        } else {
            throw new NotFoundException();
        }
        return response;
    }
}
