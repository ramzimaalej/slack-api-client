package io.slack.api.client.handler;

import io.slack.api.client.invoker.JSON;
import io.slack.api.client.model.EventPayload;

public final class EventHandler {

    private EventVisitor eventVisitor;
    private JSON jsonParser;

    public EventHandler(EventVisitor eventVisitor, JSON jsonParser) {
        this.eventVisitor = eventVisitor;
        this.jsonParser = jsonParser;
    }

    public void handleEvent(String payload) {
        try {
            EventPayload eventObject = jsonParser.deserialize(payload, EventPayload.class);
            EventDecorator eventDecorator = new EventDecorator(eventObject);
            eventDecorator.accept(eventVisitor);
        } catch (Exception e) {
            throw new RuntimeException("An error has occurred while handling slack event", e);
        }
    }
}
