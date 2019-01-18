package io.slack.api.client.handler;

import io.slack.api.client.handler.EventHandler;
import io.slack.api.client.handler.EventVisitor;
import io.slack.api.client.invoker.JSON;
import io.slack.api.client.model.AppRateLimitedEvent;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * API tests for EventHandler
 */
public class EventHandlerUnitTest {

    private EventHandler eventHandler;

    @Mock
    private EventVisitor eventVisitorMock;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        eventHandler = new EventHandler(eventVisitorMock, new JSON());
    }

    @Test
    public void shouldParseEventAndInvokeVisitor() {
        //given: receive the following payload
        String payload = "{\n" +
                "    \"token\": \"Jhj5dZrVaK7ZwHHjRyZWjbDl\",\n" +
                "    \"type\": \"app_rate_limited\",\n" +
                "    \"team_id\": \"T123456\",\n" +
                "    \"minute_rate_limited\": 1518467820,\n" +
                "    \"api_app_id\": \"A123456\"\n" +
                "}";
        doNothing().when(eventVisitorMock).visit(any(AppRateLimitedEvent.class));
        //when: parse the payload
        eventHandler.handleEvent(payload);
        //then: an event object should be created
        verify(eventVisitorMock, times(1)).visit(any(AppRateLimitedEvent.class));
    }

    @Test(expected = RuntimeException.class)
    public void shouldPropagateExceptionFromVisitor() {
        //given: receive the following payload
        String payload = "{\n" +
                "    \"token\": \"Jhj5dZrVaK7ZwHHjRyZWjbDl\",\n" +
                "    \"type\": \"app_rate_limited\",\n" +
                "    \"team_id\": \"T123456\",\n" +
                "    \"minute_rate_limited\": 1518467820,\n" +
                "    \"api_app_id\": \"A123456\"\n" +
                "}";
        doThrow(new IOException()).when(eventVisitorMock).visit(any(AppRateLimitedEvent.class));
        //when: parse the payload
        eventHandler.handleEvent(payload);
    }
}