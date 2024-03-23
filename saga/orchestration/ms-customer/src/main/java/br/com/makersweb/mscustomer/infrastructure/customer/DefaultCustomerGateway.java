package br.com.makersweb.mscustomer.infrastructure.customer;

import br.com.makersweb.mscustomer.domain.customer.Customer;
import br.com.makersweb.mscustomer.domain.customer.CustomerGateway;
import br.com.makersweb.mscustomer.domain.customer.CustomerID;
import br.com.makersweb.mscustomer.domain.exceptions.DomainException;
import br.com.makersweb.mscustomer.domain.pagination.Pagination;
import br.com.makersweb.mscustomer.domain.pagination.SearchQuery;
import br.com.makersweb.mscustomer.domain.validation.Error;
import br.com.makersweb.mscustomer.infrastructure.customer.persistence.CustomerJpaEntity;
import br.com.makersweb.mscustomer.infrastructure.customer.persistence.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static br.com.makersweb.mscustomer.infrastructure.utils.SpecificationUtils.like;

/**
 * @author aaristides
 */
@Component
@Slf4j
public class DefaultCustomerGateway implements CustomerGateway {

    private final CustomerRepository repository;

    public DefaultCustomerGateway(final CustomerRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public Customer create(final Customer aUser) {
        log.info("Init method create by user - {}", aUser.getId());
        return save(aUser);
    }

    @Override
    public void deleteById(final CustomerID anId) {
        log.info("Init method deleteById - {}", anId.getValue());
        final String anIdValue = anId.getValue();
        if (this.repository.existsById(anIdValue)) {
            this.repository.deleteById(anIdValue);
        }
    }

    @Override
    public Optional<Customer> findById(final CustomerID anId) {
        log.info("Init method findById - {}", anId.getValue());
        return this.repository.findById(anId.getValue()).map(CustomerJpaEntity::toAggregate);
    }

    @Override
    public Customer update(final Customer aUser) {
        log.info("Init method update by id - {}", aUser.getId().getValue());
        return save(aUser);
    }

    @Override
    public Pagination<Customer> findAll(final SearchQuery aQuery) {
        log.info("Init method findAll.");
        // Paginação
        final var page = PageRequest.of(
                aQuery.page(),
                aQuery.perPage(),
                Sort.by(Sort.Direction.fromString(aQuery.direction()), aQuery.sort())
        );

        // Busca dinamica pelo criterio terms (name ou description)
        final var specifications = Optional.ofNullable(aQuery.terms())
                .filter(str -> !str.isBlank())
                .map(this::assembleSpecification)
                .orElse(null);

        final var pageResult = this.repository.findAll(Specification.where(specifications), page);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(CustomerJpaEntity::toAggregate).toList()
        );
    }

    @Override
    public List<CustomerID> existsByIds(final Iterable<CustomerID> userIDS) {
        final var ids = StreamSupport.stream(userIDS.spliterator(), false)
                .map(CustomerID::getValue)
                .toList();
        return this.repository.existsByIds(ids).stream()
                .map(CustomerID::from)
                .toList();
    }

    private Customer save(final Customer aUser) {
        try {
            return this.repository.save(CustomerJpaEntity.from(aUser)).toAggregate();
        } catch (DataIntegrityViolationException e) {
            throw DomainException.with(new Error("Already exist Customer by document - %s".formatted(aUser.getDocument())));
        }
    }

    private Specification<CustomerJpaEntity> assembleSpecification(final String str) {
        final Specification<CustomerJpaEntity> nameLike = like("name", str);
        final Specification<CustomerJpaEntity> descriptionLike = like("document", str);
        return nameLike.or(descriptionLike);
    }
}
