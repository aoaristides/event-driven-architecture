package br.com.microservices.orchestrated.productvalidationservice.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

/**
 * @author aaristides
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private String id;
    private List<OrderProduct> products;
    private Instant createdAt;
    private String transactionId;
    private double totalAmount;
    private int totalItems;

}
