package io.slack.api.client;

import io.slack.api.client.model.AppMentionEvent;
import io.slack.api.client.model.AppRateLimitedEvent;
import io.slack.api.client.model.ReactionAddedEvent;

public interface EventVisitor {
    void visit(AppRateLimitedEvent event);
    void visit(AppMentionEvent event);
    void visit(ReactionAddedEvent event);
}
