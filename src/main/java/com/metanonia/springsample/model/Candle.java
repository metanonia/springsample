package com.metanonia.springsample.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "candle")
public class Candle {
    @EmbeddedId
    private CandleId candleId;
    @Column(name = "o")
    private BigDecimal open;
    @Column(name = "h")
    private BigDecimal high;
    @Column(name = "l")
    private BigDecimal low;
    @Column(name = "c")
    private BigDecimal close;
    @Column(name = "v")
    private BigDecimal volume;
}
