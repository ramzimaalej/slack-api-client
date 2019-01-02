package io.slack.api.client.api

import io.slack.api.client.model.EmptyBody
import spock.lang.Specification

/**
 * API tests for TestApi
 */
class TestApiSpec extends Specification {

    private final TestApi api = new TestApi()

    def "should call and parse response from slack api.test"() {
        given:
            def emptyBody = new EmptyBody()
        when:
            def apiTestResponse = api.apiTest(null, null, emptyBody)
        then:
            apiTestResponse.ok == Boolean.TRUE
    }
}
