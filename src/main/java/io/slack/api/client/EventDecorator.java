package io.slack.api.client;

import io.slack.api.client.exception.UnknownTypeException;
import io.slack.api.client.model.AppRateLimited;
import io.slack.api.client.model.EventPayload;

public final class EventDecorator {

    private EventPayload event;

    public EventDecorator(EventPayload event) {
        this.event = event;
    }

    void accept(EventVisitor visitor) {
        switch (event.getType()) {
            case "app_rate_limited": {
                visitor.visit((AppRateLimited) event);
                break;
            }
            default:
                throw new UnknownTypeException("Unknown type: " + event.getType());
        }
    }
}
