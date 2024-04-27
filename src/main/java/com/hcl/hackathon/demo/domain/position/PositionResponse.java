package com.hcl.hackathon.demo.domain.position;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hcl.hackathon.demo.entity.portfolio.Instrument;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PositionResponse {

  private Long instrumentId;
  private int units;
}
