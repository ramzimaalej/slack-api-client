package io.slack.api.client.invoker;

import io.slack.api.client.model.*;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * API tests for JSON
 */
public class JSONUnitTest {

    private JSON json = new JSON();

    @Test
    public void shouldParseAppRateLimitedEvent() {
        // should parse app_rate_limited event
        //given: "receive the following payload"
        String payload = "{\n" +
                "    \"token\": \"Jhj5dZrVaK7ZwHHjRyZWjbDl\",\n" +
                "    \"type\": \"app_rate_limited\",\n" +
                "    \"team_id\": \"T123456\",\n" +
                "    \"minute_rate_limited\": 1518467820,\n" +
                "    \"api_app_id\": \"A123456\"\n" +
                "}";
        //when: "parse the payload"
        EventPayload event = json.deserialize(payload, EventPayload.class);
        //then: "an event object should be created"
        assertThat(event).isInstanceOf(AppRateLimitedEvent.class);
        assertThat(event.getToken()).isEqualTo("Jhj5dZrVaK7ZwHHjRyZWjbDl");
        assertThat(event.getTeamId()).isEqualTo("T123456");
        assertThat(((AppRateLimitedEvent) event).getMinuteRateLimited()).isEqualTo(1518467820);
        assertThat(event.getApiAppId()).isEqualTo("A123456");
    }

    @Test
    public void shouldParseBaseAttributesEventCallback() {
        // "should parse base attributes for event_callback object"
        //given: "receive the following payload"
        String payload = "{\n" +
                "        \"token\": \"z26uFbvR1xHJEdHE1OQiO6t8\",\n" +
                "        \"team_id\": \"T061EG9RZ\",\n" +
                "        \"api_app_id\": \"A0FFV41KK\",\n" +
                "        \"event\": null,\n" +
                "        \"type\": \"event_callback\",\n" +
                "        \"authed_users\": [\n" +
                "                \"U061F7AUR\"\n" +
                "        ],\n" +
                "        \"event_id\": \"Ev9UQ52YNA\",\n" +
                "        \"event_time\": 1234567890\n" +
                "}";
        //when: "parse the payload"
        EventPayload event = json.deserialize(payload, EventPayload.class);
        //then: "an event object should be created"
        assertThat(event).isInstanceOf(EventCallback.class);
        assertThat(event.getToken()).isEqualTo("z26uFbvR1xHJEdHE1OQiO6t8");
        assertThat(event.getTeamId()).isEqualTo("T061EG9RZ");
        assertThat(event.getApiAppId()).isEqualTo("A0FFV41KK");
        assertThat(((EventCallback) event).getEventId()).isEqualTo("Ev9UQ52YNA");
        assertThat(((EventCallback) event).getEventTime()).isEqualTo(1234567890);
    }

    @Test
    public void shouldParseAppMentionEvent() {
        // should parse app_mention event
        // given: "receive the following payload"
        String payload = "{\n" +
                "    \"token\": \"ZZZZZZWSxiZZZ2yIvs3peJ\",\n" +
                "    \"team_id\": \"T061EG9R6\",\n" +
                "    \"api_app_id\": \"A0MDYCDME\",\n" +
                "    \"event\": {\n" +
                "        \"type\": \"app_mention\",\n" +
                "        \"user\": \"U061F7AUR\",\n" +
                "        \"text\": \"What ever happened to <@U0LAN0Z89>?\",\n" +
                "        \"ts\": \"1515449438.000011\",\n" +
                "        \"channel\": \"C0LAN2Q65\",\n" +
                "        \"event_ts\": \"1515449438000011\"\n" +
                "    },\n" +
                "    \"type\": \"event_callback\",\n" +
                "    \"event_id\": \"Ev0MDYGDKJ\",\n" +
                "    \"event_time\": 1515449438000011,\n" +
                "    \"authed_users\": [\n" +
                "        \"U0LAN0Z89\"\n" +
                "    ]\n" +
                "}";
        //when: "parse the payload"

        EventCallback event = json.deserialize(payload, EventCallback.class);

        //then: "an event object should be created"

        assertThat(event.getEvent()).isInstanceOf(AppMentionEvent.class);
        assertThat(event.getEvent().getUser()).isEqualTo("U061F7AUR");
        assertThat(((AppMentionEvent) event.getEvent()).getText()).isEqualTo("What ever happened to <@U0LAN0Z89>?");
        assertThat(event.getEvent().getTs()).isEqualTo("1515449438.000011");
        assertThat(((AppMentionEvent) event.getEvent()).getChannel()).isEqualTo("C0LAN2Q65");
        assertThat(event.getEvent().getEventTs()).isEqualTo("1515449438000011");
    }

    @Test
    public void shouldParseReactionAddedEvent() {
        // should parse reaction_added event
        //given: "receive the following payload"
        String payload = "{\n" +
                "        \"token\": \"z26uFbvR1xHJEdHE1OQiO6t8\",\n" +
                "        \"team_id\": \"T061EG9RZ\",\n" +
                "        \"api_app_id\": \"A0FFV41KK\",\n" +
                "        \"event\": {\n" +
                "                \"type\": \"reaction_added\",\n" +
                "                \"user\": \"U061F1EUR\",\n" +
                "                \"item\": {\n" +
                "                        \"type\": \"message\",\n" +
                "                        \"channel\": \"C061EG9SL\",\n" +
                "                        \"ts\": \"1464196127.000002\"\n" +
                "                },\n" +
                "                \"reaction\": \"slightly_smiling_face\",\n" +
                "                \"item_user\": \"U0M4RL1NY\",\n" +
                "                \"event_ts\": \"1465244570.336841\"\n" +
                "        },\n" +
                "        \"type\": \"event_callback\",\n" +
                "        \"authed_users\": [\n" +
                "                \"U061F7AUR\"\n" +
                "        ],\n" +
                "        \"event_id\": \"Ev9UQ52YNA\",\n" +
                "        \"event_time\": 1234567890\n" +
                "}";
        //when: "parse the payload"
        EventCallback event = json.deserialize(payload, EventCallback.class);
        //then: "an event object should be created"
        assertThat(event.getEvent()).isInstanceOf(ReactionAddedEvent.class);
        assertThat(event.getEvent().getUser()).isEqualTo("U061F1EUR");
        assertThat(((ReactionAddedEvent) event.getEvent()).getItem().getType()).isEqualTo("message");
        assertThat(((ReactionAddedEvent) event.getEvent()).getItem().getChannel()).isEqualTo("C061EG9SL");
        assertThat(((ReactionAddedEvent) event.getEvent()).getItem().getTs()).isEqualTo("1464196127.000002");
        assertThat(((ReactionAddedEvent) event.getEvent()).getReaction()).isEqualTo("slightly_smiling_face");
        assertThat(((ReactionAddedEvent) event.getEvent()).getItemUser()).isEqualTo("U0M4RL1NY");
        assertThat(event.getEvent().getEventTs()).isEqualTo("1465244570.336841");
    }

    @Test
    public void shouldParseAppUninstalledEvent() {
        // should parse app_uninstalled event
        //given: "receive the following payload"
        String payload = "{\n" +
                "    \"token\": \"XXYYZZ\",\n" +
                "    \"team_id\": \"TXXXXXXXX\",\n" +
                "    \"api_app_id\": \"AXXXXXXXXX\",\n" +
                "    \"event\": {\n" +
                "        \"type\": \"app_uninstalled\"\n" +
                "    },\n" +
                "    \"type\": \"event_callback\",\n" +
                "    \"event_id\": \"EvXXXXXXXX\",\n" +
                "    \"event_time\": 1234567890\n" +
                "}";
        //when: "parse the payload"
        EventCallback event = json.deserialize(payload, EventCallback.class);
        //then: "an event object should be created"
        assertThat(event.getEvent()).isInstanceOf(AppUninstalledEvent.class);
    }

    @Test
    public void shouldParseChannelArchivedEvent() {
        // should parse channel_archive event
        //given: "receive the following payload"
        String payload = "{\n" +
                "    \"token\": \"XXYYZZ\",\n" +
                "    \"team_id\": \"TXXXXXXXX\",\n" +
                "    \"api_app_id\": \"AXXXXXXXXX\",\n" +
                "    \"event\": {\n" +
                "        \"type\": \"channel_archive\",\n" +
                "        \"channel\": \"C024BE91L\",\n" +
                "        \"user\": \"U024BE7LH\"\n" +
                "    },\n" +
                "    \"type\": \"event_callback\",\n" +
                "    \"event_id\": \"EvXXXXXXXX\",\n" +
                "    \"event_time\": 1234567890\n" +
                "}";
        //when: "parse the payload"
        EventCallback event = json.deserialize(payload, EventCallback.class);
        //then: "an event object should be created"
        assertThat(event.getEvent()).isInstanceOf(ChannelArchiveEvent.class);
        assertThat(((ChannelArchiveEvent) event.getEvent()).getChannel()).isEqualTo("C024BE91L");
        assertThat(event.getEvent().getUser()).isEqualTo("U024BE7LH");
    }

    @Test
    public void shouldParseChannelCreatedEvent() {
        // should parse channel_created event
        //given: "receive the following payload"
        String payload = "{\n" +
                "    \"token\": \"XXYYZZ\",\n" +
                "    \"team_id\": \"TXXXXXXXX\",\n" +
                "    \"api_app_id\": \"AXXXXXXXXX\",\n" +
                "    \"event\": {\n" +
                "        \"type\": \"channel_created\",\n" +
                "        \"channel\": {\n" +
                "            \"id\": \"C024BE91L\",\n" +
                "            \"name\": \"fun\",\n" +
                "            \"created\": 1360782804,\n" +
                "            \"creator\": \"U024BE7LH\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"type\": \"event_callback\",\n" +
                "    \"authed_users\": [\n" +
                "    \"UXXXXXXX1\",\n" +
                "    \"UXXXXXXX2\"\n" +
                "    ],\n" +
                "    \"event_id\": \"Ev08MFMKH6\",\n" +
                "    \"event_time\": 1234567890\n" +
                "}";
        //when: "parse the payload"
        EventCallback event = json.deserialize(payload, EventCallback.class);
        //then: "an event object should be created"
        assertThat(event.getEvent()).isInstanceOf(ChannelCreatedEvent.class);
        assertThat(((ChannelCreatedEvent) event.getEvent()).getChannel().getId()).isEqualTo("C024BE91L");
        assertThat(((ChannelCreatedEvent) event.getEvent()).getChannel().getName()).isEqualTo("fun");
        assertThat(((ChannelCreatedEvent) event.getEvent()).getChannel().getCreated()).isEqualTo(1360782804L);
        assertThat(((ChannelCreatedEvent) event.getEvent()).getChannel().getCreator()).isEqualTo("U024BE7LH");
    }

    @Test
    public void shouldParseChannelDeletedEvent() {
        // should parse channel_deleted event
        //given: "receive the following payload"
        String payload = "{\n" +
                "    \"token\": \"XXYYZZ\",\n" +
                "    \"team_id\": \"TXXXXXXXX\",\n" +
                "    \"api_app_id\": \"AXXXXXXXXX\",\n" +
                "    \"event\": {\n" +
                "        \"type\": \"channel_deleted\",\n" +
                "        \"channel\": \"C024BE91L\"\n" +
                "    },\n" +
                "    \"type\": \"event_callback\",\n" +
                "    \"event_id\": \"EvXXXXXXXX\",\n" +
                "    \"event_time\": 1234567890\n" +
                "}";
        //when: "parse the payload"
        EventCallback event = json.deserialize(payload, EventCallback.class);
        //then: "an event object should be created"
        assertThat(event.getEvent()).isInstanceOf(ChannelDeletedEvent.class);
        assertThat(((ChannelDeletedEvent) event.getEvent()).getChannel()).isEqualTo("C024BE91L");
    }

    @Test
    public void shouldParseChannelHistoryChangedEvent() {
        // should parse channel_history_changed event
        //given: "receive the following payload"
        String payload = "{\n" +
                "    \"token\": \"XXYYZZ\",\n" +
                "    \"team_id\": \"TXXXXXXXX\",\n" +
                "    \"api_app_id\": \"AXXXXXXXXX\",\n" +
                "    \"event\": {\n" +
                "        \"type\": \"channel_history_changed\",\n" +
                "        \"latest\": \"1358877455.000010\",\n" +
                "        \"ts\": \"1361482916.000003\",\n" +
                "        \"event_ts\": \"1361482916.000004\"\n" +
                "    },\n" +
                "    \"type\": \"event_callback\",\n" +
                "    \"event_id\": \"EvXXXXXXXX\",\n" +
                "    \"event_time\": 1234567890\n" +
                "}";
        //when: "parse the payload"
        EventCallback event = json.deserialize(payload, EventCallback.class);
        //then: "an event object should be created"
        assertThat(event.getEvent()).isInstanceOf(ChannelHistoryChangedEvent.class);
        assertThat(((ChannelHistoryChangedEvent) event.getEvent()).getLatest()).isEqualTo("1358877455.000010");
        assertThat(event.getEvent().getTs()).isEqualTo("1361482916.000003");
        assertThat(event.getEvent().getEventTs()).isEqualTo("1361482916.000004");
    }

    @Test
    public void shouldParseChannelLeftEvent() {
        // should parse channel_left event
        //given: "receive the following payload"
        String payload = "{\n" +
                "    \"token\": \"XXYYZZ\",\n" +
                "    \"team_id\": \"TXXXXXXXX\",\n" +
                "    \"api_app_id\": \"AXXXXXXXXX\",\n" +
                "    \"event\": {\n" +
                "        \"type\": \"channel_left\",\n" +
                "        \"channel\": \"C024BE91L\"\n" +
                "    },\n" +
                "    \"type\": \"event_callback\",\n" +
                "    \"event_id\": \"EvXXXXXXXX\",\n" +
                "    \"event_time\": 1234567890\n" +
                "}";
        //when: "parse the payload"
        EventCallback event = json.deserialize(payload, EventCallback.class);
        //then: "an event object should be created"
        assertThat(event.getEvent()).isInstanceOf(ChannelLeftEvent.class);
        assertThat(((ChannelLeftEvent) event.getEvent()).getChannel()).isEqualTo("C024BE91L");
    }

    @Test
    public void shouldParseChannelRenameEvent() {
        // should parse channel_rename event
        //given: "receive the following payload"
        String payload = "{\n" +
                "    \"token\": \"XXYYZZ\",\n" +
                "    \"team_id\": \"TXXXXXXXX\",\n" +
                "    \"api_app_id\": \"AXXXXXXXXX\",\n" +
                "    \"event\": {\n" +
                "        \"type\": \"channel_rename\",\n" +
                "        \"channel\": {\n" +
                "            \"id\": \"C024BE91L\",\n" +
                "            \"name\": \"fun\",\n" +
                "            \"created\": 1360782804\n" +
                "        }\n" +
                "    },\n" +
                "    \"type\": \"event_callback\",\n" +
                "    \"event_id\": \"EvXXXXXXXX\",\n" +
                "    \"event_time\": 1234567890\n" +
                "}";
        //when: "parse the payload"
        EventCallback event = json.deserialize(payload, EventCallback.class);
        //then: "an event object should be created"
        assertThat(event.getEvent()).isInstanceOf(ChannelRenameEvent.class);
        assertThat(((ChannelRenameEvent) event.getEvent()).getChannel().getId()).isEqualTo("C024BE91L");
        assertThat(((ChannelRenameEvent) event.getEvent()).getChannel().getName()).isEqualTo("fun");
        assertThat(((ChannelRenameEvent) event.getEvent()).getChannel().getCreated()).isEqualTo(1360782804L);
    }

    @Test
    public void shouldParseChannelUnarchiveEvent() {
        // should parse channel_unarchive event
        //given: "receive the following payload"
        String payload = "{\n" +
                "    \"token\": \"XXYYZZ\",\n" +
                "    \"team_id\": \"TXXXXXXXX\",\n" +
                "    \"api_app_id\": \"AXXXXXXXXX\",\n" +
                "    \"event\": {\n" +
                "        \"type\": \"channel_unarchive\",\n" +
                "        \"channel\": \"C024BE91L\",\n" +
                "        \"user\": \"U024BE7LH\"\n" +
                "    },\n" +
                "    \"type\": \"event_callback\",\n" +
                "    \"event_id\": \"EvXXXXXXXX\",\n" +
                "    \"event_time\": 1234567890\n" +
                "}";
        //when: "parse the payload"
        EventCallback event = json.deserialize(payload, EventCallback.class);
        //then: "an event object should be created"
        assertThat(event.getEvent()).isInstanceOf(ChannelUnarchiveEvent.class);
        assertThat(((ChannelUnarchiveEvent) event.getEvent()).getChannel()).isEqualTo("C024BE91L");
        assertThat(event.getEvent().getUser()).isEqualTo("U024BE7LH");
    }

    @Test
    public void shouldParseDndUpdatedEvent() {
        // should parse dnd_updated event
        //given: "receive the following payload"
        String payload = "{\n" +
                "    \"token\": \"XXYYZZ\",\n" +
                "    \"team_id\": \"TXXXXXXXX\",\n" +
                "    \"api_app_id\": \"AXXXXXXXXX\",\n" +
                "    \"event\": {\n" +
                "        \"type\": \"dnd_updated\",\n" +
                "        \"user\": \"U1234\",\n" +
                "        \"dnd_status\": {\n" +
                "            \"dnd_enabled\": true,\n" +
                "            \"next_dnd_start_ts\": 1450387800,\n" +
                "            \"next_dnd_end_ts\": 1450423800,\n" +
                "            \"snooze_enabled\": true,\n" +
                "            \"snooze_endtime\": 1450373897\n" +
                "        }\n" +
                "    },\n" +
                "    \"type\": \"event_callback\",\n" +
                "    \"event_id\": \"EvXXXXXXXX\",\n" +
                "    \"event_time\": 1234567890\n" +
                "}";
        //when: "parse the payload"
        EventCallback event = json.deserialize(payload, EventCallback.class);
        //then: "an event object should be created"
        assertThat(event.getEvent()).isInstanceOf(DndUpdatedEvent.class);
        assertThat(event.getEvent().getUser()).isEqualTo("U1234");
        assertThat(((DndUpdatedEvent) event.getEvent()).getDndStatus().getDndEnabled()).isEqualTo(true);
        assertThat(((DndUpdatedEvent) event.getEvent()).getDndStatus().getNextDndStartTs()).isEqualTo(1450387800L);
        assertThat(((DndUpdatedEvent) event.getEvent()).getDndStatus().getNextDndEndTs()).isEqualTo(1450423800L);
        assertThat(((DndUpdatedEvent) event.getEvent()).getDndStatus().getSnoozeEnabled()).isEqualTo(true);
        assertThat(((DndUpdatedEvent) event.getEvent()).getDndStatus().getSnoozeEndtime()).isEqualTo(1450373897L);
    }

    @Test
    public void shouldParseDndUpdatedUserEvent() {
        // should parse dnd_updated event
        //given: "receive the following payload"
        String payload = "{\n" +
                "    \"token\": \"XXYYZZ\",\n" +
                "    \"team_id\": \"TXXXXXXXX\",\n" +
                "    \"api_app_id\": \"AXXXXXXXXX\",\n" +
                "    \"event\": {\n" +
                "        \"type\": \"dnd_updated_user\",\n" +
                "        \"user\": \"U1234\",\n" +
                "        \"dnd_status\": {\n" +
                "            \"dnd_enabled\": true,\n" +
                "            \"next_dnd_start_ts\": 1450387800,\n" +
                "            \"next_dnd_end_ts\": 1450423800\n" +
                "        }\n" +
                "    },\n" +
                "    \"type\": \"event_callback\",\n" +
                "    \"event_id\": \"EvXXXXXXXX\",\n" +
                "    \"event_time\": 1234567890\n" +
                "}";
        //when: "parse the payload"
        EventCallback event = json.deserialize(payload, EventCallback.class);
        //then: "an event object should be created"
        assertThat(event.getEvent()).isInstanceOf(DndUpdatedUserEvent.class);
        assertThat(event.getEvent().getUser()).isEqualTo("U1234");
        assertThat(((DndUpdatedUserEvent) event.getEvent()).getDndStatus().getDndEnabled()).isEqualTo(true);
        assertThat(((DndUpdatedUserEvent) event.getEvent()).getDndStatus().getNextDndStartTs()).isEqualTo(1450387800L);
        assertThat(((DndUpdatedUserEvent) event.getEvent()).getDndStatus().getNextDndEndTs()).isEqualTo(1450423800L);
    }

    @Test
    public void shouldParseEmailDomainChangedEvent() {
        // should parse dnd_updated event
        //given: "receive the following payload"
        String payload = "{\n" +
                "    \"token\": \"XXYYZZ\",\n" +
                "    \"team_id\": \"TXXXXXXXX\",\n" +
                "    \"api_app_id\": \"AXXXXXXXXX\",\n" +
                "    \"event\": {\n" +
                "        \"type\": \"email_domain_changed\",\n" +
                "        \"email_domain\": \"example.com\"\n" +
                "    },\n" +
                "    \"type\": \"event_callback\",\n" +
                "    \"event_id\": \"EvXXXXXXXX\",\n" +
                "    \"event_time\": 1234567890\n" +
                "}";
        //when: "parse the payload"
        EventCallback event = json.deserialize(payload, EventCallback.class);
        //then: "an event object should be created"
        assertThat(event.getEvent()).isInstanceOf(EmailDomainChangedEvent.class);
        assertThat(((EmailDomainChangedEvent) event.getEvent()).getEmailDomain()).isEqualTo("example.com");
    }

    @Test
    public void shouldParseEmojiRemovedEvent() {
        // should parse emoji_changed event
        //given: "receive the following payload"
        String payload = "{\n" +
                "    \"token\": \"XXYYZZ\",\n" +
                "    \"team_id\": \"TXXXXXXXX\",\n" +
                "    \"api_app_id\": \"AXXXXXXXXX\",\n" +
                "    \"event\": {\n" +
                "        \"type\": \"emoji_changed\",\n" +
                "        \"subtype\": \"remove\",\n" +
                "        \"names\": [\"remove\"],\n" +
                "        \"event_ts\": \"1361482916.000004\"\n" +
                "    },\n" +
                "    \"type\": \"event_callback\",\n" +
                "    \"event_id\": \"EvXXXXXXXX\",\n" +
                "    \"event_time\": 1234567890\n" +
                "}";
        //when: "parse the payload"
        EventCallback event = json.deserialize(payload, EventCallback.class);
        //then: "an event object should be created"
        assertThat(event.getEvent()).isInstanceOf(EmojiChangedEvent.class);
        assertThat(((EmojiChangedEvent) event.getEvent()).getNames().size()).isEqualTo(1);
        assertThat(((EmojiChangedEvent) event.getEvent()).getNames().get(0)).isEqualTo("remove");
    }

    @Test
    public void shouldParseEmojiAddedEvent() {
        // should parse emoji_changed event
        //given: "receive the following payload"
        String payload = "{\n" +
                "    \"token\": \"XXYYZZ\",\n" +
                "    \"team_id\": \"TXXXXXXXX\",\n" +
                "    \"api_app_id\": \"AXXXXXXXXX\",\n" +
                "    \"event\": {\n" +
                "        \"type\": \"emoji_changed\",\n" +
                "        \"subtype\": \"add\",\n" +
                "        \"name\": \"picard_facepalm\",\n" +
                "        \"value\": \"https://my.slack.com/emoji/picard_facepalm/db8e287430eaa459.gif\",\n" +
                "        \"event_ts\": \"1361482916.000004\"\n" +
                "    },\n" +
                "    \"type\": \"event_callback\",\n" +
                "    \"event_id\": \"EvXXXXXXXX\",\n" +
                "    \"event_time\": 1234567890\n" +
                "}";
        //when: "parse the payload"
        EventCallback event = json.deserialize(payload, EventCallback.class);
        //then: "an event object should be created"
        assertThat(event.getEvent()).isInstanceOf(EmojiChangedEvent.class);
        assertThat(((EmojiChangedEvent) event.getEvent()).getName()).isEqualTo("picard_facepalm");
        assertThat(((EmojiChangedEvent) event.getEvent()).getValue()).isEqualTo("https://my.slack.com/emoji/picard_facepalm/db8e287430eaa459.gif");
    }

    @Test
    public void shouldParseMessageEvent() {
        // should parse message event
        //given: "receive the following payload"
        String payload = "{\n" +
                "    \"token\": \"XXYYZZ\",\n" +
                "    \"team_id\": \"TXXXXXXXX\",\n" +
                "    \"api_app_id\": \"AXXXXXXXXX\",\n" +
                "    \"event\":" +
                "{\n" +
                "    \"type\": \"message\",\n" +
                "    \"channel\": \"C2147483705\",\n" +
                "    \"user\": \"U2147483697\",\n" +
                "    \"text\": \"Hello world\",\n" +
                "    \"ts\": \"1355517523.000005\"\n" +
                "},"
                +
                "    \"type\": \"event_callback\",\n" +
                "    \"event_id\": \"EvXXXXXXXX\",\n" +
                "    \"event_time\": 1234567890\n" +
                "}";
        //when: "parse the payload";
        //when: "parse the payload"
        EventCallback event = json.deserialize(payload, EventCallback.class);
        //then: "an event object should be created"
        assertThat(event.getEvent()).isInstanceOf(MessageEvent.class);
        assertThat(((MessageEvent) event.getEvent()).getChannel()).isEqualTo("C2147483705");
        assertThat((event.getEvent()).getUser()).isEqualTo("U2147483697");
        assertThat(((MessageEvent) event.getEvent()).getText()).isEqualTo("Hello world");
        assertThat(((MessageEvent) event.getEvent()).getTs()).isEqualTo("1355517523.000005");
    }

    @Test
    public void shouldParseBotMessageEvent() {
        // should parse bot_message event
        //given: "receive the following payload"
        String payload = "{\n" +
                "    \"token\": \"XXYYZZ\",\n" +
                "    \"team_id\": \"TXXXXXXXX\",\n" +
                "    \"api_app_id\": \"AXXXXXXXXX\",\n" +
                "    \"event\": {\n" +
                "       \"type\": \"message\",\n" +
                "       \"subtype\": \"bot_message\",\n" +
                "       \"ts\": \"1358877455.000010\",\n" +
                "       \"text\": \"Pushing is the answer\",\n" +
                "       \"bot_id\": \"BB12033\",\n" +
                "       \"username\": \"github\",\n" +
                "       \"icons\": {}\n" +
                "    }," +
                "    \"type\": \"event_callback\",\n" +
                "    \"event_id\": \"EvXXXXXXXX\",\n" +
                "    \"event_time\": 1234567890\n" +
                "}";
        //when: "parse the payload";
        //when: "parse the payload"
        EventCallback event = json.deserialize(payload, EventCallback.class);
        //then: "an event object should be created"
        assertThat(event.getEvent()).isInstanceOf(MessageEvent.class);
        assertThat(((MessageEvent) event.getEvent()).getBotId()).isEqualTo("BB12033");
        assertThat(((MessageEvent) event.getEvent()).getUsername()).isEqualTo("github");
        assertThat(((MessageEvent) event.getEvent()).getIcons()).isEmpty();
    }

    @Test
    public void shouldParseThreadBroadcastEvent() {
        // should parse thread broadcast event
        //given: "receive the following payload"
        String payload = "{\n" +
                "      \"token\":\"kFIkPEunKHo76kY0ro6lcMuQ\",\n" +
                "      \"team_id\":\"T78L3G5GS\",\n" +
                "      \"api_app_id\":\"A8NTWH4KD\",\n" +
                "      \"event\":{\n" +
                "         \"type\":\"message\",\n" +
                "         \"subtype\":\"thread_broadcast\",\n" +
                "         \"text\":\"well something happened that screwed up my experience\",\n" +
                "         \"user\":\"U78100SN4\",\n" +
                "         \"ts\":\"1551929970.009500\",\n" +
                "         \"thread_ts\":\"1551929925.008700\",\n" +
                "         \"root\":{\n" +
                "            \"client_msg_id\":\"ef9f112b-5906-4d6c-93ae-c335c721cc1f\",\n" +
                "            \"type\":\"message\",\n" +
                "            \"text\":\"normally, we are not doing autorefresh, just pushing new conversations to the top. <@U7LBRED37> can you confirm this\",\n" +
                "            \"user\":\"U78E87R4Y\",\n" +
                "            \"ts\":\"1551929925.008700\",\n" +
                "            \"thread_ts\":\"1551929925.008700\",\n" +
                "            \"reply_count\":1,\n" +
                "            \"reply_users_count\":1,\n" +
                "            \"latest_reply\":\"1551929970.009500\",\n" +
                "            \"reply_users\":[\n" +
                "               \"U78100SN4\"\n" +
                "            ],\n" +
                "            \"replies\":[\n" +
                "               {\n" +
                "                  \"user\":\"U78100SN4\",\n" +
                "                  \"ts\":\"1551929970.009500\"\n" +
                "               }\n" +
                "            ]\n" +
                "         },\n" +
                "         \"client_msg_id\":\"fc6d3ea6-c28a-4638-83f7-9d817c181712\",\n" +
                "         \"channel\":\"C8LFF92Q2\",\n" +
                "         \"event_ts\":\"1551929970.009500\",\n" +
                "         \"channel_type\":\"channel\"\n" +
                "      },\n" +
                "      \"type\":\"event_callback\",\n" +
                "      \"event_id\":\"EvGRA7MYN7\",\n" +
                "      \"event_time\":1551929970,\n" +
                "      \"authed_users\":[\n" +
                "         \"U8PT5GKV3\"\n" +
                "      ]\n" +
                "   }";

        //when: "parse the payload";
        //when: "parse the payload"
        EventCallback event = json.deserialize(payload, EventCallback.class);
        //then: "an event object should be created"
        assertThat(event.getEvent()).isInstanceOf(MessageEvent.class);
        assertThat(event.getEvent().getType()).isEqualTo("message");
        assertThat(((MessageEvent) event.getEvent()).getSubtype()).isEqualTo("thread_broadcast");
        assertThat(((MessageEvent) event.getEvent()).getText()).isEqualTo("well something happened that screwed up my experience");
        assertThat(event.getEvent().getUser()).isEqualTo("U78100SN4");
        assertThat(event.getEvent().getTs()).isEqualTo("1551929970.009500");
        assertThat(((MessageEvent) event.getEvent()).getThreadTs()).isEqualTo("1551929925.008700");
        assertThat(((MessageEvent) event.getEvent()).getChannel()).isEqualTo("C8LFF92Q2");
        assertThat(((MessageEvent) event.getEvent()).getClientMsgId()).isEqualTo("fc6d3ea6-c28a-4638-83f7-9d817c181712");
        assertThat(event.getEvent().getEventTs()).isEqualTo("1551929970.009500");
        assertThat(((MessageEvent) event.getEvent()).getChannelType()).isEqualTo("channel");
        assertThat(((MessageEvent) event.getEvent()).getRoot()).isNotNull();
        assertThat(((MessageEvent) event.getEvent()).getRoot().getClientMsgId()).isEqualTo("ef9f112b-5906-4d6c-93ae-c335c721cc1f");
        assertThat(((MessageEvent) event.getEvent()).getRoot().getType()).isEqualTo("message");
        assertThat(((MessageEvent) event.getEvent()).getRoot().getText()).isEqualTo("normally, we are not doing autorefresh, just pushing new conversations to the top. <@U7LBRED37> can you confirm this");
        assertThat(((MessageEvent) event.getEvent()).getRoot().getUser()).isEqualTo("U78E87R4Y");
        assertThat(((MessageEvent) event.getEvent()).getRoot().getTs()).isEqualTo("1551929925.008700");
        assertThat(((MessageEvent) event.getEvent()).getRoot().getThreadTs()).isEqualTo("1551929925.008700");
        assertThat(((MessageEvent) event.getEvent()).getRoot().getReplyCount()).isEqualTo(1);
        assertThat(((MessageEvent) event.getEvent()).getRoot().getReplyUsersCount()).isEqualTo(1);
        assertThat(((MessageEvent) event.getEvent()).getRoot().getLatestReply()).isEqualTo("1551929970.009500");
        assertThat(((MessageEvent) event.getEvent()).getRoot().getReplyUsers().size()).isEqualTo(1);
        assertThat(((MessageEvent) event.getEvent()).getRoot().getReplyUsers()).contains("U78100SN4");
        assertThat(((MessageEvent) event.getEvent()).getRoot().getReplies().size()).isEqualTo(1);
    }

    @Test
    public void shouldParseMessageEditedEvent() {
        // should parse bot_message event
        //given: "receive the following payload"
        String payload = "{\n" +
                "    \"token\": \"XXYYZZ\",\n" +
                "    \"team_id\": \"TXXXXXXXX\",\n" +
                "    \"api_app_id\": \"AXXXXXXXXX\",\n" +
                "    \"event\":" +
                "{\n" +
                "    \"type\": \"message\",\n" +
                "    \"subtype\": \"message_changed\",\n" +
                "    \"hidden\": true,\n" +
                "    \"channel\": \"C2147483705\",\n" +
                "    \"ts\": \"1358878755.000001\",\n" +
                "    \"message\": {\n" +
                "        \"type\": \"message\",\n" +
                "        \"user\": \"U2147483697\",\n" +
                "        \"text\": \"Hello, world!\",\n" +
                "        \"ts\": \"1355517523.000005\",\n" +
                "        \"edited\": {\n" +
                "            \"user\": \"U2147483697\",\n" +
                "            \"ts\": \"1358878755.000001\"\n" +
                "        }\n" +
                "    }\n" +
                "},"
                +
                "    \"type\": \"event_callback\",\n" +
                "    \"event_id\": \"EvXXXXXXXX\",\n" +
                "    \"event_time\": 1234567890\n" +
                "}";
        //when: "parse the payload";
        //when: "parse the payload"
        EventCallback event = json.deserialize(payload, EventCallback.class);
        //then: "an event object should be created"
        assertThat(event.getEvent()).isInstanceOf(MessageEvent.class);
        assertThat(event.getEvent().getType()).isEqualTo("message");
        assertThat(((MessageEvent) event.getEvent()).getSubtype()).isEqualTo("message_changed");
        assertThat(((MessageEvent) event.getEvent()).getHidden()).isTrue();
        assertThat(((MessageEvent) event.getEvent()).getChannel()).isEqualTo("C2147483705");
        assertThat(event.getEvent().getTs()).isEqualTo("1358878755.000001");
        assertThat(((MessageEvent) event.getEvent()).getMessage()).isNotNull();
        assertThat(((MessageEvent) event.getEvent()).getMessage().getType()).isEqualTo("message");
        assertThat(((MessageEvent) event.getEvent()).getMessage().getUser()).isEqualTo("U2147483697");
        assertThat(((MessageEvent) event.getEvent()).getMessage().getText()).isEqualTo("Hello, world!");
        assertThat(((MessageEvent) event.getEvent()).getMessage().getTs()).isEqualTo("1355517523.000005");
        assertThat(((MessageEvent) event.getEvent()).getMessage().getEdited().getTs()).isEqualTo("1358878755.000001");
        assertThat(((MessageEvent) event.getEvent()).getMessage().getEdited().getUser()).isEqualTo("U2147483697");
    }
}
