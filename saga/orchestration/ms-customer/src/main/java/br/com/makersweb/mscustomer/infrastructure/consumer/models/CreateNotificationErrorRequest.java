package br.com.makersweb.mscustomer.infrastructure.consumer.models;

import br.com.makersweb.mscustomer.domain.utils.InstantUtils;
import br.com.makersweb.mscustomer.domain.validation.Error;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.List;

/**
 * @author aaristides
 * @param status
 * @param errors
 * @param createdAt
 */
public record CreateNotificationErrorRequest(
        @JsonProperty("status") NotificationStatus status,
        @JsonProperty("errors") List<Error> errors,
        @JsonProperty("created_at") Instant createdAt
) {

    public static CreateNotificationErrorRequest with(
            final NotificationStatus status,
            final List<Error> errors
    ) {
        return new CreateNotificationErrorRequest(status, errors, InstantUtils.now());
    }

}
