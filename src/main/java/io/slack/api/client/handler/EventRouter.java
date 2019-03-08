package io.slack.api.client.handler;

import io.slack.api.client.exception.UnknownTypeException;
import io.slack.api.client.model.*;

import java.io.Serializable;

public class EventRouter<T> implements Serializable {
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
    public static final String MESSAGE_REPLIED_TYPE = "message_replied";
    public static final String FILE_SHARE_TYPE = "file_share";

    private final EventProcessor eventProcessor;

    public EventRouter(EventProcessor eventProcessor) {
        this.eventProcessor = eventProcessor;
    }

    T execute(EventPayload event) {
        switch (event.getType()) {
            case APP_RATE_LIMITED_TYPE: {
                return (T) eventProcessor.process((AppRateLimitedEvent) event);
            }
            case EVENT_CALLBACK_TYPE: {
                BaseEvent subEvent = ((EventCallback) event).getEvent();
                switch (subEvent.getType()) {
                    case APP_MENTION_TYPE: {
                        return (T) eventProcessor.process((AppMentionEvent) subEvent);
                    }
                    case REACTION_ADDED_TYPE: {
                        return (T) eventProcessor.process((ReactionAddedEvent) subEvent);
                    }
                    case APP_UNINSTALLED_TYPE: {
                        return (T) eventProcessor.process((AppUninstalledEvent) subEvent);
                    }
                    case CHANNEL_ARCHIVE_TYPE: {
                        return (T) eventProcessor.process((ChannelArchiveEvent) subEvent);
                    }
                    case CHANNEL_CREATED_TYPE: {
                        return (T) eventProcessor.process((ChannelCreatedEvent) subEvent);
                    }
                    case CHANNEL_DELETED_TYPE: {
                        return (T) eventProcessor.process((ChannelDeletedEvent) subEvent);
                    }
                    case CHANNEL_HISTORY_CHANGED_TYPE: {
                        return (T) eventProcessor.process((ChannelHistoryChangedEvent) subEvent);
                    }
                    case CHANNEL_LEFT_TYPE: {
                        return (T) eventProcessor.process((ChannelLeftEvent) subEvent);
                    }
                    case CHANNEL_RENAME_TYPE: {
                        return (T) eventProcessor.process((ChannelRenameEvent) subEvent);
                    }
                    case CHANNEL_UNARCHIVE_TYPE: {
                        return (T) eventProcessor.process((ChannelUnarchiveEvent) subEvent);
                    }
                    case DND_UPDATED_TYPE: {
                        return (T) eventProcessor.process((DndUpdatedEvent) subEvent);
                    }
                    case DND_UPDATED_USER_TYPE: {
                        return (T) eventProcessor.process((DndUpdatedUserEvent) subEvent);
                    }
                    case EMAIL_DOMAIN_CHANGED_TYPE: {
                        return (T) eventProcessor.process((EmailDomainChangedEvent) subEvent);
                    }
                    case EMOJI_CHANGED_TYPE: {
                        return processEmojiChangedEvent(subEvent);
                    }
                    case MESSAGE_TYPE: {
                        return processMessageEvent(subEvent);
                    }
                    default:
                        throw new UnknownTypeException("Unknown type: " + event.getType());
                }
            }
            default:
                throw new UnknownTypeException("Unknown type: " + event.getType());
        }
    }

    private T processEmojiChangedEvent(BaseEvent subEvent) {
        switch (((EmojiChangedEvent) subEvent).getSubtype()) {
            case EMOJI_CHANGED_ADD_TYPE: {
                return (T) eventProcessor.process(new EmojiAddedEvent((EmojiChangedEvent) subEvent));
            }
            case EMOJI_CHANGED_REMOVE_TYPE: {
                return (T) eventProcessor.process(new EmojiRemovedEvent((EmojiChangedEvent) subEvent));
            }
            default:
                throw new UnknownTypeException(String.format("Unknown type: %s", subEvent.getType()));
        }
    }

    private T processMessageEvent(BaseEvent subEvent) {
        String subtype = ((MessageEvent) subEvent).getSubtype();
        if (null == subtype) {
            return (T) eventProcessor.process((MessageEvent) subEvent);
        } else {
            switch (subtype) {
                case BOT_MESSAGE_TYPE: {
                    return (T) eventProcessor.process(new BotMessageEvent((MessageEvent) subEvent));
                }
                case THREAD_BROADCAST_TYPE: {
                    return (T) eventProcessor.process(new ThreadBroadCastMessageEvent((MessageEvent) subEvent));
                }
                case MESSAGE_EDITED_TYPE: {
                    return (T) eventProcessor.process(new MessageEditedEvent((MessageEvent) subEvent));
                }
                case MESSAGE_DELETED_TYPE: {
                    return (T) eventProcessor.process(new MessageDeletedEvent((MessageEvent) subEvent));
                }
                case MESSAGE_REPLIED_TYPE: {
                    return (T) eventProcessor.process(new MessageRepliedEvent((MessageEvent) subEvent));
                }
                case FILE_SHARE_TYPE: {
                    return (T) eventProcessor.process(new FileShareEvent((MessageEvent) subEvent));
                }
                default:
                    throw new UnknownTypeException(String.format("Unknown type: %s", subEvent.getType()));
            }
        }
    }
}
