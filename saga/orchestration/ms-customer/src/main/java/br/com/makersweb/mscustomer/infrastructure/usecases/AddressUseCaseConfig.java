package br.com.makersweb.mscustomer.infrastructure.usecases;

import br.com.makersweb.mscustomer.application.address.create.CreateAddressUseCase;
import br.com.makersweb.mscustomer.application.address.create.DefaultCreateAddressUseCase;
import br.com.makersweb.mscustomer.domain.address.AddressGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

/**
 * @author aaristides
 */
@Configuration
public class AddressUseCaseConfig {

    private final AddressGateway addressGateway;

    public AddressUseCaseConfig(final AddressGateway addressGateway) {
        this.addressGateway = Objects.requireNonNull(addressGateway);
    }

    @Bean
    public CreateAddressUseCase createAddressUseCase() {
        return new DefaultCreateAddressUseCase(addressGateway);
    }

}
