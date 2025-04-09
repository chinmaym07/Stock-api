package com.example.stocktrade.dto;

import com.example.stocktrade.beans.Trade;
import com.example.stocktrade.utils.UnixTimestampDeserializer;
import com.example.stocktrade.utils.UnixTimestampSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class TradeDto {
    private String type;
    @JsonProperty("user_id")
    @Column(name = "user_id")
    private int userId;
    private String symbol;
    private double price;
    private int shares;
    @JsonProperty("timestamp")
    @JsonSerialize(using = UnixTimestampSerializer.class)
    @JsonDeserialize(using = UnixTimestampDeserializer.class)
    private LocalDateTime timeStamp;

    public Trade toTrade() {
        return Trade.builder()
                .id(UUID.randomUUID().toString())
                .type(type)
                .userId(userId)
                .symbol(symbol)
                .price(price)
                .shares(shares)
                .timeStamp(timeStamp)
                .build();

    }
}
