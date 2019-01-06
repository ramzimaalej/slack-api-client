package io.slack.api.client.api;

import io.slack.api.client.invoker.ApiException;
import io.slack.api.client.model.ApiTestResponse;
import io.slack.api.client.model.EmptyBody;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * API tests for TestApi
 */
public class TestApiTest {

    private TestApi api;

    @Before
    public void setup() {
        this.api = new TestApi();
    }

    @Test
    public void shouldCallApiSuccessfully() throws ApiException {
        // should call and parse response from slack api.test
        // given:

        EmptyBody emptyBody = new EmptyBody();

        //when:

        ApiTestResponse apiTestResponse = api.apiTest(null, null, emptyBody);

        //then:

        assertThat(apiTestResponse.getOk()).isTrue();
    }
}
