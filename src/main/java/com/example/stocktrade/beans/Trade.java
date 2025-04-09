package com.example.stocktrade.beans;

import com.example.stocktrade.utils.UnixTimestampDeserializer;
import com.example.stocktrade.utils.UnixTimestampSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Entity
public class Trade {

    @Id
    private String id;
    private String type;
    private int userId;
    private String symbol;
    private int shares;
    private double price;
    @JsonSerialize(using = UnixTimestampSerializer.class)
    @JsonDeserialize(using = UnixTimestampDeserializer.class)
    private LocalDateTime timeStamp;

}
