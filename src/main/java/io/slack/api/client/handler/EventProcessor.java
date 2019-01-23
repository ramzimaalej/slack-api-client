package io.slack.api.client.handler;

import io.slack.api.client.model.*;

public interface EventProcessor {
    void process(AppRateLimitedEvent event);
    void process(AppMentionEvent event);
    void process(ReactionAddedEvent event);
    void process(AppUninstalledEvent event);
    void process(ChannelArchiveEvent event);
    void process(ChannelCreatedEvent event);
    void process(ChannelDeletedEvent event);
    void process(ChannelHistoryChangedEvent event);
    void process(ChannelLeftEvent event);
    void process(ChannelRenameEvent event);
    void process(ChannelUnarchiveEvent event);
    void process(DndUpdatedEvent event);
    void process(DndUpdatedUserEvent event);
    void process(EmailDomainChangedEvent event);
    void process(EmojiRemovedEvent event);
    void process(EmojiAddedEvent event);
}
