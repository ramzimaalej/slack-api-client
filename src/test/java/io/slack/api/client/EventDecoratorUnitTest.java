package io.slack.api.client;

import io.slack.api.client.model.AppRateLimitedEvent;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * API tests for EventDecorator
 */
public class EventDecoratorUnitTest {

    private EventDecorator eventDecorator;

    @Mock
    private EventVisitor eventVisitorMock;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldParseAppRateLimitedEvent() {
        // given
        AppRateLimitedEvent appRateLimitedEvent = new AppRateLimitedEvent();
        appRateLimitedEvent.setType("app_rate_limited");
        // when
        this.eventDecorator = new EventDecorator(appRateLimitedEvent);
        this.eventDecorator.accept(eventVisitorMock);
        // then
        verify(eventVisitorMock, times(1)).visit(any(AppRateLimitedEvent.class));
    }
}