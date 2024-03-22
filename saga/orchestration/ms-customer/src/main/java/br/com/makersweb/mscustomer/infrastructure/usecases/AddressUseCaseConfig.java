package br.com.makersweb.mscustomer.infrastructure.usecases;

import br.com.makersweb.mscustomer.application.address.create.CreateAddressUseCase;
import br.com.makersweb.mscustomer.application.address.create.DefaultCreateAddressUseCase;
import br.com.makersweb.mscustomer.domain.address.AddressGateway;
import br.com.makersweb.mscustomer.domain.customer.CustomerGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author aaristides
 */
@Configuration
public class AddressUseCaseConfig {

    private final AddressGateway addressGateway;
    private final CustomerGateway customerGateway;

    public AddressUseCaseConfig(AddressGateway addressGateway, CustomerGateway customerGateway) {
        this.addressGateway = addressGateway;
        this.customerGateway = customerGateway;
    }

    @Bean
    public CreateAddressUseCase createAddressUseCase() {
        return new DefaultCreateAddressUseCase(addressGateway, customerGateway);
    }

}
