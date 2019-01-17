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
        assertThat(((AppRateLimitedEvent)event).getMinuteRateLimited()).isEqualTo(1518467820);
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
        assertThat(((EventCallback)event).getEventId()).isEqualTo("Ev9UQ52YNA");
        assertThat(((EventCallback)event).getEventTime()).isEqualTo(1234567890);
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
        assertThat(((AppMentionEvent)event.getEvent()).getText()).isEqualTo("What ever happened to <@U0LAN0Z89>?");
        assertThat(event.getEvent().getTs()).isEqualTo("1515449438.000011");
        assertThat(((AppMentionEvent)event.getEvent()).getChannel()).isEqualTo("C0LAN2Q65");
        assertThat(event.getEvent().getEventTs()).isEqualTo("1515449438000011");
    }

    @Test
    public void shouldParseReactionAddedEvent () {
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
        assertThat(((ReactionAddedEvent)event.getEvent()).getItem().getType()).isEqualTo("message");
        assertThat(((ReactionAddedEvent)event.getEvent()).getItem().getChannel()).isEqualTo("C061EG9SL");
        assertThat(((ReactionAddedEvent)event.getEvent()).getItem().getTs()).isEqualTo("1464196127.000002");
        assertThat(((ReactionAddedEvent)event.getEvent()).getReaction()).isEqualTo("slightly_smiling_face");
        assertThat(((ReactionAddedEvent)event.getEvent()).getItemUser()).isEqualTo("U0M4RL1NY");
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
        assertThat(((ChannelArchiveEvent)event.getEvent()).getChannel()).isEqualTo("C024BE91L");
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
        assertThat(((ChannelCreatedEvent)event.getEvent()).getChannel().getId()).isEqualTo("C024BE91L");
        assertThat(((ChannelCreatedEvent)event.getEvent()).getChannel().getName()).isEqualTo("fun");
        assertThat(((ChannelCreatedEvent)event.getEvent()).getChannel().getCreated()).isEqualTo(1360782804L);
        assertThat(((ChannelCreatedEvent)event.getEvent()).getChannel().getCreator()).isEqualTo("U024BE7LH");
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
        assertThat(((ChannelDeletedEvent)event.getEvent()).getChannel()).isEqualTo("C024BE91L");
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
        assertThat(((ChannelHistoryChangedEvent)event.getEvent()).getLatest()).isEqualTo("1358877455.000010");
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
        assertThat(((ChannelLeftEvent)event.getEvent()).getChannel()).isEqualTo("C024BE91L");
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
        assertThat(((ChannelRenameEvent)event.getEvent()).getChannel().getId()).isEqualTo("C024BE91L");
        assertThat(((ChannelRenameEvent)event.getEvent()).getChannel().getName()).isEqualTo("fun");
        assertThat(((ChannelRenameEvent)event.getEvent()).getChannel().getCreated()).isEqualTo(1360782804L);
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
        assertThat(((ChannelUnarchiveEvent)event.getEvent()).getChannel()).isEqualTo("C024BE91L");
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
        assertThat(((DndUpdatedEvent)event.getEvent()).getDndStatus().getDndEnabled()).isEqualTo(true);
        assertThat(((DndUpdatedEvent)event.getEvent()).getDndStatus().getNextDndStartTs()).isEqualTo(1450387800L);
        assertThat(((DndUpdatedEvent)event.getEvent()).getDndStatus().getNextDndEndTs()).isEqualTo(1450423800L);
        assertThat(((DndUpdatedEvent)event.getEvent()).getDndStatus().getSnoozeEnabled()).isEqualTo(true);
        assertThat(((DndUpdatedEvent)event.getEvent()).getDndStatus().getSnoozeEndtime()).isEqualTo(1450373897L);
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
        assertThat(((DndUpdatedUserEvent)event.getEvent()).getDndStatus().getDndEnabled()).isEqualTo(true);
        assertThat(((DndUpdatedUserEvent)event.getEvent()).getDndStatus().getNextDndStartTs()).isEqualTo(1450387800L);
        assertThat(((DndUpdatedUserEvent)event.getEvent()).getDndStatus().getNextDndEndTs()).isEqualTo(1450423800L);
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
        assertThat(((EmailDomainChangedEvent)event.getEvent()).getEmailDomain()).isEqualTo("example.com");
    }
}
