package br.com.makersweb.mscustomer.domain.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * @author aaristides
 */
public final class InstantUtils {

    private InstantUtils() {
    }

    public static Instant now() {
        return Instant.now().truncatedTo(ChronoUnit.MICROS);
    }
}
