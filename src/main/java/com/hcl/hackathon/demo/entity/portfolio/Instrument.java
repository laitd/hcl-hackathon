package com.hcl.hackathon.demo.entity.portfolio;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "instrument")
@Data
public class Instrument {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String symbol;
  private String name;
  private BigDecimal value;
  private String type;
}
