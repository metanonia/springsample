package com.metanonia.springsample.repository;

import com.metanonia.springsample.model.Candle;
import com.metanonia.springsample.model.CandleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandleRepository extends JpaRepository<Candle, CandleId> {
}
