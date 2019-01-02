package io.slack.api.client.invoker

import io.slack.api.client.model.AppRateLimited
import io.slack.api.client.model.EventCallback
import io.slack.api.client.model.EventPayload
import io.slack.api.client.model.ReactionAdded
import spock.lang.Specification

/**
 * API tests for JSON
 */
class JSONUnitSpec extends Specification {

    def json = new JSON()

    def "should parse app_rate_limited event"() {
        given: "receive the following payload"
        def payload = "{\n" +
                "    \"token\": \"Jhj5dZrVaK7ZwHHjRyZWjbDl\",\n" +
                "    \"type\": \"app_rate_limited\",\n" +
                "    \"team_id\": \"T123456\",\n" +
                "    \"minute_rate_limited\": 1518467820,\n" +
                "    \"api_app_id\": \"A123456\"\n" +
                "}"
        when: "parse the payload"
        def event = json.deserialize(payload, EventPayload)
        then: "an event object should be created"
        with(event) {
            event.class == AppRateLimited.class
            event.token == "Jhj5dZrVaK7ZwHHjRyZWjbDl"
            event.teamId == "T123456"
            event.minuteRateLimited == 1518467820
            event.apiAppId == "A123456"
        }
    }

    def "should parse base attributes for event_callback object"() {
        given: "receive the following payload"
        def payload = "{\n" +
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
                "}"
        when: "parse the payload"
        def event = json.deserialize(payload, EventPayload)
        then: "an event object should be created"
        with(event) {
            event.class == EventCallback.class
            event.token == "z26uFbvR1xHJEdHE1OQiO6t8"
            event.teamId == "T061EG9RZ"
            event.apiAppId == "A0FFV41KK"
            event.eventId == "Ev9UQ52YNA"
            event.eventTime == 1234567890
        }
    }

    def "should parse reaction_added event"() {
        given: "receive the following payload"
        def payload = "{\n" +
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
                "}"
        when: "parse the payload"
        def event = json.deserialize(payload, EventPayload)
        then: "an event object should be created"
        with(event) {
            event.event.class == ReactionAdded.class
            event.event.user == "U061F1EUR"
            event.event.item.type == "message"
            event.event.item.channel == "C061EG9SL"
            event.event.item.ts == "1464196127.000002"
            event.event.reaction == "slightly_smiling_face"
            event.event.itemUser == "U0M4RL1NY"
            event.event.eventTs == "1465244570.336841"
        }
    }
}
