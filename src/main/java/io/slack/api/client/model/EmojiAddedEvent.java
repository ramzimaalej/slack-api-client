package io.slack.api.client.model;

import java.io.Serializable;

public class EmojiAddedEvent implements Serializable {

    private final EmojiChangedEvent event;

    public EmojiAddedEvent(EmojiChangedEvent event) {
        this.event = event;
    }

    public String getSubtype() {
        return event.getSubtype();
    }

    public void setSubtype(String subtype) {
        event.setSubtype(subtype);
    }

    public EmojiChangedEvent items(String items) {
        return event.items(items);
    }

    public String getItems() {
        return event.getItems();
    }

    public void setItems(String items) {
        event.setItems(items);
    }

    public String getName() {
        return event.getName();
    }

    public void setName(String name) {
        event.setName(name);
    }

    public String getValue() {
        return event.getValue();
    }

    public void setValue(String value) {
        event.setValue(value);
    }

    public String getEventTs() {
        return event.getEventTs();
    }

    public void setEventTs(String eventTs) {
        event.setEventTs(eventTs);
    }

    public String getUser() {
        return event.getUser();
    }

    public void setUser(String user) {
        event.setUser(user);
    }

    public String getTs() {
        return event.getTs();
    }

    public void setTs(String ts) {
        event.setTs(ts);
    }

    public String getType() {
        return event.getType();
    }

    public void setType(String type) {
        event.setType(type);
    }
}
