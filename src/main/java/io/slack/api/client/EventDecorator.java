package io.slack.api.client;

import io.slack.api.client.exception.UnknownTypeException;
import io.slack.api.client.model.*;

public final class EventDecorator {

    private EventPayload event;

    public EventDecorator(EventPayload event) {
        this.event = event;
    }

    void accept(EventVisitor visitor) {
        switch (event.getType()) {
            case "app_rate_limited": {
                visitor.visit((AppRateLimitedEvent) event);
                break;
            }
            case "event_callback": {
                BaseEvent subEvent = ((EventCallback)event).getEvent();
                switch (subEvent.getType()) {
                    case "app_mention": {
                        visitor.visit((AppMentionEvent) subEvent);
                        break;
                    }
                    default:
                        throw new UnknownTypeException("Unknown type: " + event.getType());
                }
                break;
            }
            default:
                throw new UnknownTypeException("Unknown type: " + event.getType());
        }
    }
}
