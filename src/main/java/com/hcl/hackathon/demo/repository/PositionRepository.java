package com.hcl.hackathon.demo.repository;

import com.hcl.hackathon.demo.entity.portfolio.Position;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PositionRepository extends JpaRepository<Position, Long> {

  Optional<Position> getByCustomerIdAndInstrumentId(UUID customerId, Long instrumentId);
  List<Position> findPositionByCustomerId(UUID customerId, Pageable pageable);
}

