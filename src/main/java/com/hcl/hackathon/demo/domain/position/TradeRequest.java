package com.hcl.hackathon.demo.domain.position;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hcl.hackathon.demo.constants.TradeType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TradeRequest {

  private UUID customerId;

  @NotNull
  private Long instrumentId;

  @NotNull
  private BigDecimal instrumentPrice;

  @NotNull
  private TradeType tradeType;

  @Min(1)
  private int units;
}
