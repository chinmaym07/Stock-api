package com.example.stocktrade.service;

import com.example.stocktrade.beans.Trade;
import com.example.stocktrade.dto.TradeDto;
import com.example.stocktrade.repo.TradeRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TradeServiceTest {
    @InjectMocks
    private TradeServiceImpl tradeService;
    @Mock
    private TradeRepo tradeRepo;

    private Trade validTrade;
    private TradeDto validTradeDto;

    @Before
    public void setUp() {
        validTradeDto = TradeDto.builder().type("buy").userId(123).symbol("AAPL")
                .price(150.0).shares(50).build();
        validTrade = Trade.builder().type("buy")
                .userId(123)
                .symbol("AAPL")
                .price(150.0)
                .shares(150)
                .build();
    }


    @Test
    public void shouldCreateTradeWhenValid() {
        TradeDto tradeDto = TradeDto.builder()
                .type("buy")
                .userId(123)
                .symbol("AAPL")
                .price(150.0)
                .shares(10)
                .build();

        when(tradeRepo.save(any())).thenReturn(validTrade);

        Trade created = tradeService.createTrade(tradeDto);

        assertNotNull(created);
        assertEquals("buy", created.getType());
        verify(tradeRepo, times(1)).save(any(Trade.class));
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotCreateTradeWhenInvalid() {
        TradeDto invalidTradeDto = TradeDto.builder()
                .type(" ")
                .userId(123)
                .symbol("AAPL")
                .price(150.0)
                .shares(150)
                .build();
        TradeServiceImpl realService = new TradeServiceImpl(tradeRepo);

        Trade result = realService.createTrade(invalidTradeDto);
        assertNull(result);
        verify(tradeRepo, never()).save(any());
    }

    @Test
    public void shouldFindTradeById() {
        when(tradeRepo.findById(1L)).thenReturn(Optional.of(validTrade));

        Trade found = tradeService.findTradeById(1L);

        assertNotNull(found);
        assertEquals("AAPL", found.getSymbol());
        verify(tradeRepo, times(1)).findById(1L);
    }

    @Test
    public void shouldFindAllTradesOrderedById() {
        List<Trade> trades = Arrays.asList(validTrade);
        when(tradeRepo.findAll(Sort.by(Sort.Direction.ASC, "id"))).thenReturn(trades);

        List<Trade> result = tradeService.findAllTrade();

        assertEquals(1, result.size());
        verify(tradeRepo).findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Test
    public void shouldFindTradesByTypeAndUserId() {
        List<Trade> trades = Arrays.asList(validTrade);
        when(tradeRepo.findByTypeAndUserIdOrderByIdAsc("buy", "user1")).thenReturn(trades);

        List<Trade> result = tradeService.findTradesByTypeAndUserId("buy", "user1");

        assertEquals(1, result.size());
        assertEquals("buy", result.get(0).getType());
        verify(tradeRepo).findByTypeAndUserIdOrderByIdAsc("buy", "user1");
    }

}
