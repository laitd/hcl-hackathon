package com.hcl.hackathon.demo.domain.position;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TradeResponse {

  private long instrumentId;
  private int units;
}
