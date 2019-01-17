package io.slack.api.client;

import io.slack.api.client.exception.UnknownTypeException;
import io.slack.api.client.model.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static io.slack.api.client.EventDecorator.*;
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
        appRateLimitedEvent.setType(APP_RATE_LIMITED_TYPE);
        // when
        this.eventDecorator = new EventDecorator(appRateLimitedEvent);
        this.eventDecorator.accept(eventVisitorMock);
        // then
        verify(eventVisitorMock, times(1)).visit(any(AppRateLimitedEvent.class));
    }

    @Test(expected = UnknownTypeException.class)
    public void shouldThrowAnExceptionWhenUnknowType() {
        // given
        AppRateLimitedEvent appRateLimitedEvent = new AppRateLimitedEvent();
        appRateLimitedEvent.setType("app_rate");
        // when
        this.eventDecorator = new EventDecorator(appRateLimitedEvent);
        this.eventDecorator.accept(eventVisitorMock);
    }

    @Test(expected = UnknownTypeException.class)
    public void shouldThrowAnExceptionWhenUnknowTypeForSubEvent() {
        // given
        AppMentionEvent appMentionEvent = new AppMentionEvent();
        appMentionEvent.setType("app");
        EventCallback eventCallback = new EventCallback();
        eventCallback.setEvent(appMentionEvent);
        eventCallback.setType(EVENT_CALLBACK_TYPE);
        // when
        this.eventDecorator = new EventDecorator(eventCallback);
        this.eventDecorator.accept(eventVisitorMock);
    }

    @Test
    public void shouldParseAppMentionEvent() {
        // given
        AppMentionEvent appMentionEvent = new AppMentionEvent();
        appMentionEvent.setType("app_mention");
        EventCallback eventCallback = new EventCallback();
        eventCallback.setEvent(appMentionEvent);
        eventCallback.setType(EVENT_CALLBACK_TYPE);
        // when
        this.eventDecorator = new EventDecorator(eventCallback);
        this.eventDecorator.accept(eventVisitorMock);
        // then
        verify(eventVisitorMock, times(1)).visit(any(AppMentionEvent.class));
    }

    @Test
    public void shouldParseReactionAddedEvent() {
        // given
        ReactionAddedEvent reactionAddedEvent = new ReactionAddedEvent();
        reactionAddedEvent.setType(REACTION_ADDED_TYPE);
        EventCallback eventCallback = new EventCallback();
        eventCallback.setEvent(reactionAddedEvent);
        eventCallback.setType(EVENT_CALLBACK_TYPE);
        // when
        this.eventDecorator = new EventDecorator(eventCallback);
        this.eventDecorator.accept(eventVisitorMock);
        // then
        verify(eventVisitorMock, times(1)).visit(any(ReactionAddedEvent.class));
    }

    @Test
    public void shouldParseAppUninstalledEvent() {
        // given
        AppUninstalledEvent appUninstalledEvent = new AppUninstalledEvent();
        appUninstalledEvent.setType(APP_UNINSTALLED_TYPE);
        EventCallback eventCallback = new EventCallback();
        eventCallback.setEvent(appUninstalledEvent);
        eventCallback.setType(EVENT_CALLBACK_TYPE);
        // when
        this.eventDecorator = new EventDecorator(eventCallback);
        this.eventDecorator.accept(eventVisitorMock);
        // then
        verify(eventVisitorMock, times(1)).visit(any(AppUninstalledEvent.class));
    }

    @Test
    public void shouldParseChannelArchivedEvent() {
        // given
        ChannelArchiveEvent channelArchiveEvent = new ChannelArchiveEvent();
        channelArchiveEvent.setType(CHANNEL_ARCHIVE_TYPE);
        EventCallback eventCallback = new EventCallback();
        eventCallback.setEvent(channelArchiveEvent);
        eventCallback.setType(EVENT_CALLBACK_TYPE);
        // when
        this.eventDecorator = new EventDecorator(eventCallback);
        this.eventDecorator.accept(eventVisitorMock);
        // then
        verify(eventVisitorMock, times(1)).visit(any(ChannelArchiveEvent.class));
    }

    @Test
    public void shouldParseChannelCreatedEvent() {
        // given
        ChannelCreatedEvent channelCreatedEvent = new ChannelCreatedEvent();
        channelCreatedEvent.setType(CHANNEL_CREATED_TYPE);
        EventCallback eventCallback = new EventCallback();
        eventCallback.setEvent(channelCreatedEvent);
        eventCallback.setType(EVENT_CALLBACK_TYPE);
        // when
        this.eventDecorator = new EventDecorator(eventCallback);
        this.eventDecorator.accept(eventVisitorMock);
        // then
        verify(eventVisitorMock, times(1)).visit(any(ChannelCreatedEvent.class));
    }

    @Test
    public void shouldParseChannelDeletedEvent() {
        // given
        ChannelDeletedEvent channelDeletedEvent = new ChannelDeletedEvent();
        channelDeletedEvent.setType(CHANNEL_DELETED_TYPE);
        EventCallback eventCallback = new EventCallback();
        eventCallback.setEvent(channelDeletedEvent);
        eventCallback.setType(EVENT_CALLBACK_TYPE);
        // when
        this.eventDecorator = new EventDecorator(eventCallback);
        this.eventDecorator.accept(eventVisitorMock);
        // then
        verify(eventVisitorMock, times(1)).visit(any(ChannelDeletedEvent.class));
    }

    @Test
    public void shouldParseChannelHistoryChangedEvent() {
        // given
        ChannelHistoryChangedEvent channelHistoryChangedEvent = new ChannelHistoryChangedEvent();
        channelHistoryChangedEvent.setType(CHANNEL_HISTORY_CHANGED_TYPE);
        EventCallback eventCallback = new EventCallback();
        eventCallback.setEvent(channelHistoryChangedEvent);
        eventCallback.setType(EVENT_CALLBACK_TYPE);
        // when
        this.eventDecorator = new EventDecorator(eventCallback);
        this.eventDecorator.accept(eventVisitorMock);
        // then
        verify(eventVisitorMock, times(1)).visit(any(ChannelHistoryChangedEvent.class));
    }

    @Test
    public void shouldParseChannelLeftEvent() {
        // given
        ChannelLeftEvent channelLeftEvent = new ChannelLeftEvent();
        channelLeftEvent.setType(CHANNEL_LEFT_TYPE);
        EventCallback eventCallback = new EventCallback();
        eventCallback.setEvent(channelLeftEvent);
        eventCallback.setType(EVENT_CALLBACK_TYPE);
        // when
        this.eventDecorator = new EventDecorator(eventCallback);
        this.eventDecorator.accept(eventVisitorMock);
        // then
        verify(eventVisitorMock, times(1)).visit(any(ChannelLeftEvent.class));
    }

    @Test
    public void shouldParseChannelRenameEvent() {
        // given
        ChannelRenameEvent channelRenameEvent = new ChannelRenameEvent();
        channelRenameEvent.setType(CHANNEL_RENAME_TYPE);
        EventCallback eventCallback = new EventCallback();
        eventCallback.setEvent(channelRenameEvent);
        eventCallback.setType(EVENT_CALLBACK_TYPE);
        // when
        this.eventDecorator = new EventDecorator(eventCallback);
        this.eventDecorator.accept(eventVisitorMock);
        // then
        verify(eventVisitorMock, times(1)).visit(any(ChannelRenameEvent.class));
    }

    @Test
    public void shouldParseChannelUnarchiveEvent() {
        // given
        ChannelUnarchiveEvent channelUnarchiveEvent = new ChannelUnarchiveEvent();
        channelUnarchiveEvent.setType(CHANNEL_UNARCHIVE_TYPE);
        EventCallback eventCallback = new EventCallback();
        eventCallback.setEvent(channelUnarchiveEvent);
        eventCallback.setType(EVENT_CALLBACK_TYPE);
        // when
        this.eventDecorator = new EventDecorator(eventCallback);
        this.eventDecorator.accept(eventVisitorMock);
        // then
        verify(eventVisitorMock, times(1)).visit(any(ChannelUnarchiveEvent.class));
    }

    @Test
    public void shouldParseDndUpdatedEvent() {
        // given
        DndUpdatedEvent dndUpdatedEvent = new DndUpdatedEvent();
        dndUpdatedEvent.setType(DND_UPDATED_TYPE);
        EventCallback eventCallback = new EventCallback();
        eventCallback.setEvent(dndUpdatedEvent);
        eventCallback.setType(EVENT_CALLBACK_TYPE);
        // when
        this.eventDecorator = new EventDecorator(eventCallback);
        this.eventDecorator.accept(eventVisitorMock);
        // then
        verify(eventVisitorMock, times(1)).visit(any(DndUpdatedEvent.class));
    }
}
