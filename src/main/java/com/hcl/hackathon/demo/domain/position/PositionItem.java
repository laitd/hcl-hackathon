package com.hcl.hackathon.demo.domain.position;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PositionItem {

  private Long instrumentId;
  private String instrumentSymbol;
  private String instrumentName;
  private BigDecimal instrumentValue;
  private String instrumentType;
  private int units;
}
