package com.hcl.hackathon.demo.mapper;

import com.hcl.hackathon.demo.domain.position.TradeRequest;
import com.hcl.hackathon.demo.entity.portfolio.Position;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PositionMapper {

  public Position toEntity(TradeRequest positionRequest){
    Position position = new Position();
    position.setUnits(positionRequest.getUnits());
    position.setCustomerId(positionRequest.getCustomerId());
    position.setInstrumentId(positionRequest.getInstrumentId());
    position.setTransactionRef(UUID.randomUUID().toString());
    return position;
  }
}
