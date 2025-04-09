package com.example.stocktrade.service;

import com.example.stocktrade.beans.Trade;
import com.example.stocktrade.dto.TradeDto;
import com.example.stocktrade.repo.TradeRepo;
import com.example.stocktrade.validator.TradeValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class TradeServiceImpl implements TradeService {
    private final TradeRepo tradeRepo;

    @Override
    public Trade createTrade(TradeDto tradeDto) {
        boolean isValid = TradeValidator.validateTrade(tradeDto);
        if (!isValid) {
            log.error("Trade is invalid");
            throw new RuntimeException("Trade is invalid");
        }
        Trade tradeEntity = tradeDto.toTrade();
        return tradeRepo.save(tradeEntity);
    }

    @Override
    public Trade findTradeById(Long id) {
        Optional<Trade> trade = tradeRepo.findById(id);
        return trade.get();
    }

    @Override
    public List<Trade> findAllTrade() {
        return tradeRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public List<Trade> findTradesByTypeAndUserId(String type, String userId) {
        return tradeRepo.findByTypeAndUserIdOrderByIdAsc(type, userId);
    }
}
