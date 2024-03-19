package br.com.makersweb.mscustomer.infrastructure.usecases;

import br.com.makersweb.mscustomer.application.customer.create.CreateCustomerUseCase;
import br.com.makersweb.mscustomer.application.customer.create.DefaultCreateCustomerUseCase;
import br.com.makersweb.mscustomer.application.customer.delete.DefaultDeleteCustomerUseCase;
import br.com.makersweb.mscustomer.application.customer.delete.DeleteCustomerCase;
import br.com.makersweb.mscustomer.application.customer.retrieve.get.DefaultGetCustomerByIdUseCase;
import br.com.makersweb.mscustomer.application.customer.retrieve.get.GetCustomerByIdUseCase;
import br.com.makersweb.mscustomer.application.customer.retrieve.list.DefaultListCustomerUseCase;
import br.com.makersweb.mscustomer.application.customer.retrieve.list.ListCustomerUseCase;
import br.com.makersweb.mscustomer.application.customer.update.DefaultUpdateUserUseCase;
import br.com.makersweb.mscustomer.application.customer.update.UpdateCustomerUseCase;
import br.com.makersweb.mscustomer.domain.address.AddressGateway;
import br.com.makersweb.mscustomer.domain.customer.CustomerGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author aaristides
 */
@Configuration
public class CustomerUseCaseConfig {

    private final CustomerGateway customerGateway;
    private final AddressGateway addressGateway;

    public CustomerUseCaseConfig(CustomerGateway customerGateway, AddressGateway addressGateway) {
        this.customerGateway = customerGateway;
        this.addressGateway = addressGateway;
    }

    @Bean
    public CreateCustomerUseCase createCustomerUseCase() {
        return new DefaultCreateCustomerUseCase(customerGateway);
    }

    @Bean
    public UpdateCustomerUseCase updateCustomerUseCase() {
        return new DefaultUpdateUserUseCase(customerGateway, addressGateway);
    }

    @Bean
    public GetCustomerByIdUseCase getCustomerByIdUseCase() {
        return new DefaultGetCustomerByIdUseCase(customerGateway);
    }

    @Bean
    public ListCustomerUseCase listCustomerUseCase() {
        return new DefaultListCustomerUseCase(customerGateway);
    }

    @Bean
    public DeleteCustomerCase deleteCustomerCase() {
        return new DefaultDeleteCustomerUseCase(customerGateway);
    }

}
