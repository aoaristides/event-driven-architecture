package br.com.makersweb.mscustomer.domain.pagination;

import java.util.List;
import java.util.function.Function;

/**
 * @author aaristides
 * @param currentPage
 * @param perPage
 * @param total
 * @param items
 * @param <T>
 */
public record Pagination<T>(
        int currentPage,
        int perPage,
        long total,
        List<T> items
) {

    public <R> Pagination<R> map(final Function<T, R> mapper) {
        final List<R> aNewList = this.items.stream().map(mapper).toList();
        return new Pagination<>(currentPage(), perPage(), total(), aNewList);
    }

}
