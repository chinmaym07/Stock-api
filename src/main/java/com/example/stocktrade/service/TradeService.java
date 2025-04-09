package com.example.stocktrade.service;

import com.example.stocktrade.beans.Trade;
import com.example.stocktrade.dto.TradeDto;

import java.util.List;

public interface TradeService {
    Trade createTrade(TradeDto trade);

    Trade findTradeById(Long id);

    List<Trade> findAllTrade();

    List<Trade> findTradesByTypeAndUserId(String type, String userId);
}
