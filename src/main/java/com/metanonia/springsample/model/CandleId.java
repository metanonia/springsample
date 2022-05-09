package com.metanonia.springsample.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class CandleId implements Serializable {
    @Column(name = "symbol")
    private String symbol;
    @Column(name = "ts")
    private Timestamp timeStamp;

}
