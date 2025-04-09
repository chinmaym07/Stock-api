package com.example.stocktrade.repo;

import com.example.stocktrade.beans.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepo extends JpaRepository<Trade, Long> {
    @Query("SELECT t FROM Trade t WHERE t.type = :type AND t.userId = :userId ORDER BY t.id")
    List<Trade> findByTypeAndUserIdOrderByIdAsc(String type, String userId);
}
