package io.slack.api.client.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class EmojiRemovedEvent {

    private final EmojiChangedEvent event;

    public EmojiRemovedEvent(EmojiChangedEvent event) {
        this.event = event;
    }

    public String getSubtype() {
        return event.getSubtype();
    }

    public void setSubtype(String subtype) {
        event.setSubtype(subtype);
    }

    public List getNames() {
        return event.getNames();
    }

    public void setNames(List names) {
        event.setNames(names);
    }

    public String getItems() {
        return event.getItems();
    }

    public void setItems(String items) {
        event.setItems(items);
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
