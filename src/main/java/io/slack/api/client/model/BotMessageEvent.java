package io.slack.api.client.model;

import java.io.Serializable;
import java.util.Map;

public class BotMessageEvent implements Serializable {

    private final MessageEvent event;

    public BotMessageEvent(MessageEvent event) {
        this.event = event;
    }

    public MessageEvent subtype(String subtype) {
        return event.subtype(subtype);
    }

    public MessageEvent botId(String botId) {
        return event.botId(botId);
    }

    public String getBotId() {
        return event.getBotId();
    }

    public void setBotId(String botId) {
        event.setBotId(botId);
    }

    public MessageEvent username(String username) {
        return event.username(username);
    }

    public String getUsername() {
        return event.getUsername();
    }

    public void setUsername(String username) {
        event.setUsername(username);
    }

    public MessageEvent icons(Map<String, String> icons) {
        return event.icons(icons);
    }

    public MessageEvent putIconsItem(String key, String iconsItem) {
        return event.putIconsItem(key, iconsItem);
    }

    public Map<String, String> getIcons() {
        return event.getIcons();
    }

    public void setIcons(Map<String, String> icons) {
        event.setIcons(icons);
    }

    public BaseEvent eventTs(String eventTs) {
        return event.eventTs(eventTs);
    }

    public String getEventTs() {
        return event.getEventTs();
    }

    public void setEventTs(String eventTs) {
        event.setEventTs(eventTs);
    }

    public BaseEvent user(String user) {
        return event.user(user);
    }

    public String getUser() {
        return event.getUser();
    }

    public void setUser(String user) {
        event.setUser(user);
    }

    public BaseEvent ts(String ts) {
        return event.ts(ts);
    }

    public String getTs() {
        return event.getTs();
    }

    public void setTs(String ts) {
        event.setTs(ts);
    }

    public BaseEvent type(String type) {
        return event.type(type);
    }

    public String getType() {
        return event.getType();
    }

    public void setType(String type) {
        event.setType(type);
    }
}
