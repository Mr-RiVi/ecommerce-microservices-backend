package com.example.inventory_service.service;

import com.example.inventory_service.dto.InventoryResponse;
import com.example.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public List<InventoryResponse> isInStock(List<String> skuCode) {
        try {
            log.info("Wait started");
            Thread.sleep(10); // 10000
            log.info("Wait ended");
        } catch (InterruptedException e) {
            log.warn("InventoryService interrupted: {}", e.getMessage());
            // Restore the interrupted status
            Thread.currentThread().interrupt();
            throw new IllegalStateException("InventoryService was interrupted", e);
        }
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory -> InventoryResponse.builder()
                        .skuCode(inventory.getSkuCode())
                        .isInStock(inventory.getQuantity()>0)
                        .build()
                ).toList();
    }
}
