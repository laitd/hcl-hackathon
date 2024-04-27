package com.hcl.hackathon.demo.entity.portfolio;


import com.hcl.hackathon.demo.constants.TradeType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "positions")
@Data
public class Position {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String symbol;
  private int quantity;
  private Long instrumentId;

  private UUID customerId;

  private String transactionRef;

  @Enumerated(EnumType.STRING)
  private TradeType tradeType;

  private int units;

  // Getters and setters
  @CreationTimestamp
  private Instant createdDate;

}
