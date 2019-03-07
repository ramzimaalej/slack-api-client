package io.slack.api.client.model;

import java.io.Serializable;

public class ThreadBroadCastMessageEvent implements Serializable {

    private final MessageEvent event;

    public ThreadBroadCastMessageEvent(MessageEvent event) {
        this.event = event;
    }
}
