package io.slack.api.client;

import io.slack.api.client.model.*;

public interface EventVisitor {
    void visit(AppRateLimitedEvent event);
    void visit(AppMentionEvent event);
    void visit(ReactionAddedEvent event);
    void visit(AppUninstalledEvent event);
    void visit(ChannelArchiveEvent event);
    void visit(ChannelCreatedEvent event);
    void visit(ChannelDeletedEvent event);
    void visit(ChannelHistoryChangedEvent event);
    void visit(ChannelLeftEvent event);
}
