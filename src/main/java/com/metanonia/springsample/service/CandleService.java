package com.metanonia.springsample.service;

import com.metanonia.springsample.mapper.CandleMapper;
import com.metanonia.springsample.model.Candle;
import com.metanonia.springsample.repository.CandleRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CandleService {
    @Autowired
    private CandleRepository candleRepository;
    @Autowired
    private CandleMapper candleMapper;

    public void save(Candle candle) {
        candleRepository.save(candle);
    }

    public List<HashMap<String,Object>> getLast10Candles() {
        return candleMapper.findLast10Candle();
    }

    public List<HashMap<String,Object>> getFirst10Candles() {
        return candleMapper.findFirst10Candle();
    }
}
