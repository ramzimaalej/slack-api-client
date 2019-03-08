package io.slack.api.client.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Map;

public class BotMessageEvent extends AbstractMessageEvent implements Serializable {

    private final MessageEvent event;

    public BotMessageEvent(MessageEvent event) {
        this.event = event;
    }

    @Override
    public boolean isActionEvent() {
        return false;
    }

    public MessageEvent subtype(String subtype) {
        return event.subtype(subtype);
    }

    @ApiModelProperty("")
    public String getSubtype() {
        return event.getSubtype();
    }

    public void setSubtype(String subtype) {
        event.setSubtype(subtype);
    }

    public MessageEvent channel(String channel) {
        return event.channel(channel);
    }

    @ApiModelProperty("")
    public String getChannel() {
        return event.getChannel();
    }

    public void setChannel(String channel) {
        event.setChannel(channel);
    }

    public MessageEvent text(String text) {
        return event.text(text);
    }

    @ApiModelProperty("")
    public String getText() {
        return event.getText();
    }

    public void setText(String text) {
        event.setText(text);
    }

    public MessageEvent botId(String botId) {
        return event.botId(botId);
    }

    @ApiModelProperty("")
    public String getBotId() {
        return event.getBotId();
    }

    public void setBotId(String botId) {
        event.setBotId(botId);
    }

    public MessageEvent username(String username) {
        return event.username(username);
    }

    @ApiModelProperty("")
    public String getUsername() {
        return event.getUsername();
    }

    public void setUsername(String username) {
        event.setUsername(username);
    }

    public MessageEvent threadTs(String threadTs) {
        return event.threadTs(threadTs);
    }

    @ApiModelProperty("")
    public String getThreadTs() {
        return event.getThreadTs();
    }

    public void setThreadTs(String threadTs) {
        event.setThreadTs(threadTs);
    }

    public MessageEvent clientMsgId(String clientMsgId) {
        return event.clientMsgId(clientMsgId);
    }

    @ApiModelProperty("")
    public String getClientMsgId() {
        return event.getClientMsgId();
    }

    public void setClientMsgId(String clientMsgId) {
        event.setClientMsgId(clientMsgId);
    }

    public MessageEvent channelType(String channelType) {
        return event.channelType(channelType);
    }

    @ApiModelProperty("")
    public String getChannelType() {
        return event.getChannelType();
    }

    public void setChannelType(String channelType) {
        event.setChannelType(channelType);
    }

    public MessageEvent hidden(Boolean hidden) {
        return event.hidden(hidden);
    }

    @ApiModelProperty("")
    public Boolean getHidden() {
        return event.getHidden();
    }

    public void setHidden(Boolean hidden) {
        event.setHidden(hidden);
    }

    public MessageEvent icons(Map<String, String> icons) {
        return event.icons(icons);
    }

    public MessageEvent putIconsItem(String key, String iconsItem) {
        return event.putIconsItem(key, iconsItem);
    }

    @ApiModelProperty("")
    public Map<String, String> getIcons() {
        return event.getIcons();
    }

    public void setIcons(Map<String, String> icons) {
        event.setIcons(icons);
    }

    public MessageEvent root(RootMessageItem root) {
        return event.root(root);
    }

    @ApiModelProperty("")
    public RootMessageItem getRoot() {
        return event.getRoot();
    }

    public void setRoot(RootMessageItem root) {
        event.setRoot(root);
    }

    public MessageEvent edited(MessageEventItem edited) {
        return event.edited(edited);
    }

    @ApiModelProperty("")
    public MessageEventItem getEdited() {
        return event.getEdited();
    }

    public void setEdited(MessageEventItem edited) {
        event.setEdited(edited);
    }

    public MessageEvent message(RootMessageItem message) {
        return event.message(message);
    }

    @ApiModelProperty("")
    public RootMessageItem getMessage() {
        return event.getMessage();
    }

    public void setMessage(RootMessageItem message) {
        event.setMessage(message);
    }

    public BaseEvent eventTs(String eventTs) {
        return event.eventTs(eventTs);
    }

    @ApiModelProperty(required = true, value = "")
    public String getEventTs() {
        return event.getEventTs();
    }

    public void setEventTs(String eventTs) {
        event.setEventTs(eventTs);
    }

    public BaseEvent user(String user) {
        return event.user(user);
    }

    @ApiModelProperty(required = true, value = "")
    public String getUser() {
        return event.getUser();
    }

    public void setUser(String user) {
        event.setUser(user);
    }

    public BaseEvent ts(String ts) {
        return event.ts(ts);
    }

    @ApiModelProperty(required = true, value = "")
    public String getTs() {
        return event.getTs();
    }

    public void setTs(String ts) {
        event.setTs(ts);
    }

    public BaseEvent type(String type) {
        return event.type(type);
    }

    @ApiModelProperty(required = true, value = "")
    public String getType() {
        return event.getType();
    }

    public void setType(String type) {
        event.setType(type);
    }
}
