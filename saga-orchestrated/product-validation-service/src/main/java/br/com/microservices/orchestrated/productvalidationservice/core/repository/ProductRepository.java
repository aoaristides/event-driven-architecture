package br.com.microservices.orchestrated.productvalidationservice.core.repository;

import br.com.microservices.orchestrated.productvalidationservice.core.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author aaristides
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {

    boolean existsByCode(final String code);

}
