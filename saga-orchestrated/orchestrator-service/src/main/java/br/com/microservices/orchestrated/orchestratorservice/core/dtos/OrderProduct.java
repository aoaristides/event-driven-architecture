package br.com.microservices.orchestrated.orchestratorservice.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author aaristides
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct {

    private Product product;
    private int quantity;

}
