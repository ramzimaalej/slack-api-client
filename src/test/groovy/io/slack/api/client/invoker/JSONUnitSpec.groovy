package io.slack.api.client.invoker

import io.slack.api.client.model.AppRateLimited
import io.slack.api.client.model.Event
import spock.lang.Specification

/**
 * API tests for JSON
 */
class JSONUnitSpec extends Specification {

    def json = new JSON()

    def "should parse app_rate_limited event"() {
        given:
            def payload = "{\n" +
                "    \"token\": \"Jhj5dZrVaK7ZwHHjRyZWjbDl\",\n" +
                "    \"type\": \"app_rate_limited\",\n" +
                "    \"team_id\": \"T123456\",\n" +
                "    \"minute_rate_limited\": 1518467820,\n" +
                "    \"api_app_id\": \"A123456\"\n" +
                "}"
        when:
            def event = json.deserialize(payload, Event)
        then:
            event.class == AppRateLimited.class
    }
}
