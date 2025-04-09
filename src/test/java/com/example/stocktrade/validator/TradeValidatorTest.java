
package com.example.stocktrade.validator;

import com.example.stocktrade.dto.TradeDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class TradeValidatorTest {

    @Test
    public void shouldReturnTrueForValidTrade() {
        TradeDto tradeDto = TradeDto.builder()
                .type("buy")
                .userId(123)
                .symbol("AAPL")
                .price(150.0)
                .shares(50)
                .build();
        assertTrue(TradeValidator.validateTrade(tradeDto));
    }

    @Test
    public void shouldReturnFalseForBlankType() {
        TradeDto tradeDto = TradeDto.builder()
                .type(" ")
                .userId(123)
                .symbol("AAPL")
                .price(150.0)
                .shares(50)
                .build();
        assertFalse(TradeValidator.validateTrade(tradeDto));
    }

    @Test
    public void shouldReturnFalseForBlankUserId() {
        TradeDto tradeDto = TradeDto.builder()
                .type("sell")
                .userId(0)
                .symbol("AAPL")
                .price(150.0)
                .shares(50)
                .build();
        assertFalse(TradeValidator.validateTrade(tradeDto));
    }

    @Test
    public void shouldReturnFalseForBlankSymbol() {
        TradeDto tradeDto = TradeDto.builder()
                .type("buy")
                .userId(123)
                .symbol(" ")
                .price(150.0)
                .shares(50)
                .build();
        assertFalse(TradeValidator.validateTrade(tradeDto));
    }

    @Test
    public void shouldReturnFalseForInvalidType() {
        TradeDto tradeDto = TradeDto.builder()
                .type("HOLD")
                .userId(123)
                .symbol("AAPL")
                .price(150.0)
                .shares(50)
                .build();

        assertFalse(TradeValidator.validateTrade(tradeDto));
    }

    @Test
    public void shouldReturnFalseForNegativePrice() {
        TradeDto tradeDto = TradeDto.builder()
                .type("sell")
                .userId(123)
                .symbol("AAPL")
                .price(-10.0)
                .shares(50)
                .build();
        assertFalse(TradeValidator.validateTrade(tradeDto));
    }

    @Test
    public void shouldReturnFalseForSharesLessThanOne() {
        TradeDto tradeDto = TradeDto.builder()
                .type("sell")
                .userId(123)
                .symbol("AAPL")
                .price(150.0)
                .shares(0)
                .build();

        assertFalse(TradeValidator.validateTrade(tradeDto));
    }

    @Test
    public void shouldReturnFalseForSharesGreaterThanHundred() {
        TradeDto tradeDto = TradeDto.builder()
                .type("buy")
                .userId(123)
                .symbol("AAPL")
                .price(150.0)
                .shares(150)
                .build();

        assertFalse(TradeValidator.validateTrade(tradeDto));
    }

}