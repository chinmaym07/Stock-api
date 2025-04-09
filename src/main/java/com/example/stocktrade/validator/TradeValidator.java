package com.example.stocktrade.validator;

import com.example.stocktrade.dto.TradeDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TradeValidator {
    private final String BUY = "buy";
    private final String SELL = "sell";

    public boolean validateTrade(TradeDto tradeDto) {
        return !tradeDto.getType().isBlank()
                && tradeDto.getUserId() > 0
                && !tradeDto.getSymbol().isBlank()
                && (SELL.equals(tradeDto.getType()) || BUY.equals(tradeDto.getType()))
                && !(tradeDto.getPrice() < 0)
                && tradeDto.getShares() >= 1
                && tradeDto.getShares() <= 100;
    }
}
