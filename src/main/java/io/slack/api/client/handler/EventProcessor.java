package io.slack.api.client.handler;

import io.slack.api.client.model.*;

import java.io.Serializable;

public interface EventProcessor<T> extends Serializable {
    T process(AppRateLimitedEvent event);
    T process(AppMentionEvent event);
    T process(ReactionAddedEvent event);
    T process(AppUninstalledEvent event);
    T process(ChannelArchiveEvent event);
    T process(ChannelCreatedEvent event);
    T process(ChannelDeletedEvent event);
    T process(ChannelHistoryChangedEvent event);
    T process(ChannelLeftEvent event);
    T process(ChannelRenameEvent event);
    T process(ChannelUnarchiveEvent event);
    T process(DndUpdatedEvent event);
    T process(DndUpdatedUserEvent event);
    T process(EmailDomainChangedEvent event);
    T process(EmojiRemovedEvent event);
    T process(EmojiAddedEvent event);
    T process(MessageEvent event);
    T process(BotMessageEvent event);
    T process(ThreadBroadCastMessageEvent event);
    T process(MessageEditedEvent event);
    T process(MessageDeletedEvent event);
    T process(MessageRepliedEvent event);
    T process(FileShareEvent event);
}
