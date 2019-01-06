package io.slack.api.client;

import io.slack.api.client.model.AppMention;
import io.slack.api.client.model.AppRateLimited;
import io.slack.api.client.model.ReactionAdded;

public interface EventVisitor {
    void visit(AppRateLimited event);
    void visit(AppMention event);
    void visit(ReactionAdded event);
}
