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
    public void shouldParseReadtionAddedEvent () {
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
}
