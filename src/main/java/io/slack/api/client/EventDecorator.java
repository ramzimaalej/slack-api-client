package io.slack.api.client;

import io.slack.api.client.exception.UnknownTypeException;
import io.slack.api.client.model.*;

public final class EventDecorator {

    public static final String APP_RATE_LIMITED_TYPE = "app_rate_limited";
    public static final String EVENT_CALLBACK_TYPE = "event_callback";
    public static final String APP_MENTION_TYPE = "app_mention";
    public static final String REACTION_ADDED_TYPE = "reaction_added";

    private EventPayload event;

    public EventDecorator(EventPayload event) {
        this.event = event;
    }

    void accept(EventVisitor visitor) {
        switch (event.getType()) {
            case APP_RATE_LIMITED_TYPE: {
                visitor.visit((AppRateLimitedEvent) event);
                break;
            }
            case EVENT_CALLBACK_TYPE: {
                BaseEvent subEvent = ((EventCallback)event).getEvent();
                switch (subEvent.getType()) {
                    case APP_MENTION_TYPE: {
                        visitor.visit((AppMentionEvent) subEvent);
                        break;
                    }
                    case REACTION_ADDED_TYPE: {
                        visitor.visit((ReactionAddedEvent) subEvent);
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
