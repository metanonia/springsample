package com.metanonia.springsample.repository;

import com.metanonia.springsample.model.Candle;
import com.metanonia.springsample.model.CandleId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;
import java.util.List;

@Repository
public interface CandleRepository extends JpaRepository<Candle, CandleId> {

    @Query(value = "SELECT m FROM Candle m WHERE m.candleId.symbol like :str% ORDER BY m.candleId.timeStamp desc", nativeQuery = false)
    Page<Candle> findCandleLike(@Param("str") String str, Pageable pageable);
}
