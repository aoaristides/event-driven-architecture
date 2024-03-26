package br.com.microservices.orchestrated.orchestratorservice.core.utils;

import br.com.microservices.orchestrated.orchestratorservice.core.dtos.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author aaristides
 */
@Component
@AllArgsConstructor
public class JsonUtil {

    private final ObjectMapper objectMapper;

    public String toJson(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            return "";
        }
    }

    public Event toEvent(final String json) {
        try {
            return objectMapper.readValue(json, Event.class);
        } catch (Exception e) {
            return null;
        }
    }

}
