package com.metanonia.springsample.service;

import com.metanonia.springsample.mapper.CandleMapper;
import com.metanonia.springsample.model.Candle;
import com.metanonia.springsample.repository.CandleRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Candle> getLastCandles(int cnt) {
        PageRequest pageable = PageRequest.of(0, cnt, Sort.by("candleId.timeStamp").descending().and(Sort.by("candleId.symbol")));
        Page<Candle> page = candleRepository.findAll(pageable);
        List<Candle> ret= new ArrayList<>();
        for(Candle candle:page) {
            ret.add(candle);
        }

        return ret;
    }
}
