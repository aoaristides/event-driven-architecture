package br.com.microservices.orchestrated.orderservice.core.dtos;

import br.com.microservices.orchestrated.orderservice.core.documents.OrderProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author aaristides
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private List<OrderProduct> products;

}
