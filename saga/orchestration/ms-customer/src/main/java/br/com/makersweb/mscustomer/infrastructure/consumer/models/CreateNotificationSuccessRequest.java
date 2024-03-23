package br.com.makersweb.mscustomer.infrastructure.consumer.models;

import br.com.makersweb.mscustomer.domain.utils.InstantUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

/**
 * @author aaristides
 * @param id
 * @param status
 * @param message
 * @param createdAt
 */
public record CreateNotificationSuccessRequest(
        @JsonProperty("customer_id") String id,
        @JsonProperty("status") NotificationStatus status,
        @JsonProperty("message") String message,
        @JsonProperty("created_at") Instant createdAt
) {

    public static CreateNotificationSuccessRequest with(
            final String id,
            final NotificationStatus status,
            final String message
    ) {
        return new CreateNotificationSuccessRequest(id, status, message, InstantUtils.now());
    }

}
