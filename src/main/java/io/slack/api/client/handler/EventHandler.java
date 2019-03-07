package io.slack.api.client.handler;

import io.slack.api.client.invoker.JSON;
import io.slack.api.client.model.EventPayload;

import java.io.Serializable;

public final class EventHandler implements Serializable {

    private EventRouter eventRouter;
    private JSON jsonParser;

    public EventHandler(EventRouter eventRouter, JSON jsonParser) {
        this.eventRouter = eventRouter;
        this.jsonParser = jsonParser;
    }

    public void handleEvent(String payload) {
        try {
            EventPayload eventObject = jsonParser.deserialize(payload, EventPayload.class);
            eventRouter.route(eventObject);
        } catch (Exception e) {
            throw new RuntimeException("An error has occurred while handling slack event", e);
        }
    }
}
