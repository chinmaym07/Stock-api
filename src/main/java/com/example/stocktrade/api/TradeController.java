package com.example.stocktrade.api;

import com.example.stocktrade.beans.Trade;
import com.example.stocktrade.dto.TradeDto;
import com.example.stocktrade.service.TradeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@Slf4j
public class TradeController {
    private final TradeService tradeService;

    @PostMapping("/trades")
    public ResponseEntity<?> createNewTrade(@RequestBody TradeDto tradeDto) {
        try {
            Trade trade = tradeService.createTrade(tradeDto);
            if (trade == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(trade);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Something went wrong", "details", e.getMessage()));
        }

    }

    @PutMapping("/trades/{id}")
    public ResponseEntity<Trade> updateTrade(@PathVariable Long id, @RequestBody TradeDto tradeDto) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
    }

    @DeleteMapping("/trades/{id}")
    public ResponseEntity<Boolean> deleteTradeById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(false);
    }

    @GetMapping("/trades")
    public ResponseEntity<List<Trade>> findTrades(@RequestParam(required = false) String type, @RequestParam(required = false) String userId) {
        if (type != null && userId != null) {
            return ResponseEntity.ok(tradeService.findTradesByTypeAndUserId(type, userId));
        }
        return ResponseEntity.ok(tradeService.findAllTrade());
    }

    @GetMapping("/trades/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        Trade trade = tradeService.findTradeById(id);
        if (trade == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(trade);
    }
}
