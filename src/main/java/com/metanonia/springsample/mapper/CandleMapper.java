package com.metanonia.springsample.mapper;

import com.metanonia.springsample.model.Candle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface CandleMapper {
    @Select("SELECT * FROM candle ORDER BY ts DESC LIMIT 10")
    List<HashMap<String,Object>> findLast10Candle();

    List<HashMap<String,Object>>findFirst10Candle();

}
