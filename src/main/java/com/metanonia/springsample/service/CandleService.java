package com.metanonia.springsample.service;

import com.metanonia.springsample.mapper.CandleMapper;
import com.metanonia.springsample.model.Candle;
import com.metanonia.springsample.model.CandleId;
import com.metanonia.springsample.repository.CandleRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
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

    public List<Candle> getLastCandles(String symbol, int cnt) {
        CandleId exid = new CandleId();
        exid.setSymbol(symbol);
        Candle ex = new Candle();
        ex.setCandleId(exid);
        Example<Candle>example = Example.of(ex);
        PageRequest pageable = PageRequest.of(0, cnt, Sort.by("candleId.timeStamp").descending().and(Sort.by("candleId.symbol")));
        Page<Candle> page = candleRepository.findAll(example, pageable);
        List<Candle> ret= new ArrayList<>();
        for(Candle candle:page) {
            ret.add(candle);
        }
        return ret;
    }

    public List<Candle> getLikeCandles(String symbol, int cnt) {
        PageRequest pageable = PageRequest.of(0, cnt, Sort.by("candleId.timeStamp").descending().and(Sort.by("candleId.symbol")));
        Page<Candle> page = candleRepository.findCandleLike(symbol, pageable);
        List<Candle> ret= new ArrayList<>();
        for(Candle candle:page) {
            ret.add(candle);
        }

        return ret;
    }
}
