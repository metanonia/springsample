package com.metanonia.springsample;

import com.metanonia.springsample.model.Candle;
import com.metanonia.springsample.model.CandleId;
import com.metanonia.springsample.repository.CandleRepository;
import com.metanonia.springsample.service.CandleService;
import com.metanonia.springsample.service.RestService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Slf4j
@SpringBootApplication
public class SpringsampleApplication implements CommandLineRunner {
    @Autowired
    CandleService candleService;

    public static void main(String[] args) {
        SpringApplication.run(SpringsampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String symbol = "ETHUSDT";
        String ret = RestService.Rest(null, null, "GET", "/api/v3/klines?symbol="+symbol+"&interval=1m", null);
        JSONArray jsonArray = new JSONArray(ret);

        for(int i=0; i<jsonArray.length(); i++) {
            JSONArray item = jsonArray.getJSONArray(i);

            long openTime = item.getLong(0);
            BigDecimal openPrice = item.getBigDecimal(1);
            BigDecimal highPrice = item.getBigDecimal(2);
            BigDecimal lowPrice = item.getBigDecimal(3);
            BigDecimal closePrice = item.getBigDecimal(4);
            BigDecimal vol = item.getBigDecimal(5);
            long closeTime = item.getLong(6);
            BigDecimal volAsset = item.getBigDecimal(7);
            long numTrade = item.getLong(8);
            BigDecimal takeBaseAsset = item.getBigDecimal(9);
            BigDecimal takeQuoteAsset = item.getBigDecimal(10);
            BigDecimal ignore = item.getBigDecimal(11);
            Date dd = new Date(openTime);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
            String str = sdf.format(dd);
            Timestamp openTimestamp = Timestamp.valueOf(str);

            CandleId candleId = new CandleId(symbol, openTimestamp);
            Candle candle = new Candle();
            candle.setCandleId(candleId);
            candle.setOpen(openPrice);
            candle.setHigh(highPrice);
            candle.setLow(lowPrice);
            candle.setClose(closePrice);
            candle.setVolume(vol);

            candleService.save(candle);
        }
        log.info("<<getLast10Candles>>");
        List<HashMap<String,Object>>llist = candleService.getLast10Candles();
        for(HashMap<String,Object>item:llist) {
            log.info(item.toString());
        }
        log.info("<<getFirst10Candles>>");
        List<HashMap<String,Object>>flist = candleService.getFirst10Candles();
        for(HashMap<String,Object> item:flist) {
            log.info(item.toString());
        }
        log.info("<<getLastCandles>>");
        List<Candle>candles = candleService.getLastCandles(10);
        for(Candle item:candles) {
            log.info(item.toString());
        }
        log.info("<<getLastCandlesBySymbol>>");
        List<Candle>btc = candleService.getLastCandles("BTCUSDT", 5);
        for(Candle item:btc) {
            log.info(item.toString());
        }
        log.info("<<getLikeCandles>>");
        List<Candle>lik = candleService.getLikeCandles("BTC", 2);
        for(Candle item:lik) {
            log.info(item.toString());
        }
    }
}
