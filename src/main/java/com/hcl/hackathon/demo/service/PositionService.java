package com.hcl.hackathon.demo.service;

import com.hcl.hackathon.demo.domain.position.TradeRequest;
import com.hcl.hackathon.demo.domain.position.TradeResponse;
import com.hcl.hackathon.demo.domain.position.PositionResponse;

import java.util.List;
import java.util.UUID;

public interface PositionService {

  TradeResponse trade(TradeRequest positionRequest);

  List<PositionResponse> findMyPositions(UUID customerId, int pageNumber, int pageSize);
}
