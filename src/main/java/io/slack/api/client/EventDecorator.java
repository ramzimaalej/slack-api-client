package io.slack.api.client;

import io.slack.api.client.exception.UnknownTypeException;
import io.slack.api.client.model.*;

public final class EventDecorator {

    public static final String APP_RATE_LIMITED_TYPE = "app_rate_limited";
    public static final String EVENT_CALLBACK_TYPE = "event_callback";
    public static final String APP_MENTION_TYPE = "app_mention";
    public static final String REACTION_ADDED_TYPE = "reaction_added";
    public static final String APP_UNINSTALLED_TYPE = "app_uninstalled";
    public static final String CHANNEL_ARCHIVE_TYPE = "channel_archive";
    public static final String CHANNEL_CREATED_TYPE = "channel_created";
    public static final String CHANNEL_DELETED_TYPE = "channel_deleted";
    public static final String CHANNEL_HISTORY_CHANGED_TYPE = "channel_history_changed";

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
                    case APP_UNINSTALLED_TYPE: {
                        visitor.visit((AppUninstalledEvent) subEvent);
                        break;
                    }
                    case CHANNEL_ARCHIVE_TYPE: {
                        visitor.visit((ChannelArchiveEvent) subEvent);
                        break;
                    }
                    case CHANNEL_CREATED_TYPE: {
                        visitor.visit((ChannelCreatedEvent) subEvent);
                        break;
                    }
                    case CHANNEL_DELETED_TYPE: {
                        visitor.visit((ChannelDeletedEvent) subEvent);
                        break;
                    }
                    case CHANNEL_HISTORY_CHANGED_TYPE: {
                        visitor.visit((ChannelHistoryChangedEvent) subEvent);
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
