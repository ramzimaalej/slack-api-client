package io.slack.api.client.handler;

import io.slack.api.client.exception.UnknownTypeException;
import io.slack.api.client.model.*;

import java.io.Serializable;

public class EventRouter implements Serializable {
    public static final String APP_RATE_LIMITED_TYPE = "app_rate_limited";
    public static final String EVENT_CALLBACK_TYPE = "event_callback";
    public static final String APP_MENTION_TYPE = "app_mention";
    public static final String REACTION_ADDED_TYPE = "reaction_added";
    public static final String APP_UNINSTALLED_TYPE = "app_uninstalled";
    public static final String CHANNEL_ARCHIVE_TYPE = "channel_archive";
    public static final String CHANNEL_CREATED_TYPE = "channel_created";
    public static final String CHANNEL_DELETED_TYPE = "channel_deleted";
    public static final String CHANNEL_HISTORY_CHANGED_TYPE = "channel_history_changed";
    public static final String CHANNEL_LEFT_TYPE = "channel_left";
    public static final String CHANNEL_RENAME_TYPE = "channel_rename";
    public static final String CHANNEL_UNARCHIVE_TYPE = "channel_unarchive";
    public static final String DND_UPDATED_TYPE = "dnd_updated";
    public static final String DND_UPDATED_USER_TYPE = "dnd_updated_user";
    public static final String EMAIL_DOMAIN_CHANGED_TYPE = "email_domain_changed";
    public static final String EMOJI_CHANGED_TYPE = "emoji_changed";
    public static final String EMOJI_CHANGED_ADD_TYPE = "add";
    public static final String EMOJI_CHANGED_REMOVE_TYPE = "remove";
    public static final String MESSAGE_TYPE = "message";
    public static final String BOT_MESSAGE_TYPE = "bot_message";
    public static final String THREAD_BROADCAST_TYPE = "thread_broadcast";
    public static final String MESSAGE_EDITED_TYPE = "message_changed";
    public static final String MESSAGE_DELETED_TYPE = "message_deleted";

    private final EventProcessor eventProcessor;

    public EventRouter(EventProcessor eventProcessor) {
        this.eventProcessor = eventProcessor;
    }

    void route(EventPayload event) {
        switch (event.getType()) {
            case APP_RATE_LIMITED_TYPE: {
                eventProcessor.process((AppRateLimitedEvent) event);
                break;
            }
            case EVENT_CALLBACK_TYPE: {
                BaseEvent subEvent = ((EventCallback) event).getEvent();
                switch (subEvent.getType()) {
                    case APP_MENTION_TYPE: {
                        eventProcessor.process((AppMentionEvent) subEvent);
                        break;
                    }
                    case REACTION_ADDED_TYPE: {
                        eventProcessor.process((ReactionAddedEvent) subEvent);
                        break;
                    }
                    case APP_UNINSTALLED_TYPE: {
                        eventProcessor.process((AppUninstalledEvent) subEvent);
                        break;
                    }
                    case CHANNEL_ARCHIVE_TYPE: {
                        eventProcessor.process((ChannelArchiveEvent) subEvent);
                        break;
                    }
                    case CHANNEL_CREATED_TYPE: {
                        eventProcessor.process((ChannelCreatedEvent) subEvent);
                        break;
                    }
                    case CHANNEL_DELETED_TYPE: {
                        eventProcessor.process((ChannelDeletedEvent) subEvent);
                        break;
                    }
                    case CHANNEL_HISTORY_CHANGED_TYPE: {
                        eventProcessor.process((ChannelHistoryChangedEvent) subEvent);
                        break;
                    }
                    case CHANNEL_LEFT_TYPE: {
                        eventProcessor.process((ChannelLeftEvent) subEvent);
                        break;
                    }
                    case CHANNEL_RENAME_TYPE: {
                        eventProcessor.process((ChannelRenameEvent) subEvent);
                        break;
                    }
                    case CHANNEL_UNARCHIVE_TYPE: {
                        eventProcessor.process((ChannelUnarchiveEvent) subEvent);
                        break;
                    }
                    case DND_UPDATED_TYPE: {
                        eventProcessor.process((DndUpdatedEvent) subEvent);
                        break;
                    }
                    case DND_UPDATED_USER_TYPE: {
                        eventProcessor.process((DndUpdatedUserEvent) subEvent);
                        break;
                    }
                    case EMAIL_DOMAIN_CHANGED_TYPE: {
                        eventProcessor.process((EmailDomainChangedEvent) subEvent);
                        break;
                    }
                    case EMOJI_CHANGED_TYPE: {
                        processEmojiChangedEvent(subEvent);
                        break;
                    }
                    case MESSAGE_TYPE: {
                        processMessageEvent(subEvent);
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

    private void processEmojiChangedEvent(BaseEvent subEvent) {
        switch (((EmojiChangedEvent) subEvent).getSubtype()) {
            case EMOJI_CHANGED_ADD_TYPE: {
                eventProcessor.process(new EmojiAddedEvent((EmojiChangedEvent) subEvent));
                break;
            }
            case EMOJI_CHANGED_REMOVE_TYPE: {
                eventProcessor.process(new EmojiRemovedEvent((EmojiChangedEvent) subEvent));
                break;
            }
            default:
                throw new UnknownTypeException(String.format("Unknown type: %s", subEvent.getType()));
        }
    }

    private void processMessageEvent(BaseEvent subEvent) {
        String subtype = ((MessageEvent) subEvent).getSubtype();
        if (null == subtype) {
            eventProcessor.process((MessageEvent) subEvent);
        } else {
            switch (subtype) {
                case BOT_MESSAGE_TYPE: {
                    eventProcessor.process(new BotMessageEvent((MessageEvent) subEvent));
                    break;
                }
                case THREAD_BROADCAST_TYPE: {
                    eventProcessor.process(new ThreadBroadCastMessageEvent((MessageEvent) subEvent));
                    break;
                }
                case MESSAGE_EDITED_TYPE: {
                    eventProcessor.process(new MessageEditedEvent((MessageEvent) subEvent));
                    break;
                }
                case MESSAGE_DELETED_TYPE: {
                    eventProcessor.process(new MessageDeletedEvent((MessageEvent) subEvent));
                    break;
                }
                default:
                    throw new UnknownTypeException(String.format("Unknown type: %s", subEvent.getType()));
            }
        }
    }
}
