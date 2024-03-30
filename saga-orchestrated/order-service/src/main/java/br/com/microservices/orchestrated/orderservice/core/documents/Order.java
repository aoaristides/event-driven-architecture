package br.com.microservices.orchestrated.orderservice.core.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author aaristides
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collation = "orders")
public class Order {

    @Id
    private String id;
    private List<OrderProduct> products;
    private LocalDateTime createdAt;
    private String transactionId;
    private double totalAmount;
    private int totalItems;

}
