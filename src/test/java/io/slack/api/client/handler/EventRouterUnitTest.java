package io.slack.api.client.handler;

import io.slack.api.client.exception.UnknownTypeException;
import io.slack.api.client.model.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static io.slack.api.client.handler.EventRouter.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * API tests for EventRouter
 */
public class EventRouterUnitTest {

    private EventRouter eventRouter;

    @Mock
    private EventProcessor eventProcessorMock;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        eventRouter = new EventRouter(eventProcessorMock);
    }

    @Test
    public void shouldParseAppRateLimitedEvent() {
        // given
        AppRateLimitedEvent appRateLimitedEvent = new AppRateLimitedEvent();
        appRateLimitedEvent.setType(APP_RATE_LIMITED_TYPE);
        // when
        this.eventRouter.route(appRateLimitedEvent);
        // then
        verify(eventProcessorMock, times(1)).process(any(AppRateLimitedEvent.class));
    }

    @Test(expected = UnknownTypeException.class)
    public void shouldThrowAnExceptionWhenUnknowType() {
        // given
        AppRateLimitedEvent appRateLimitedEvent = new AppRateLimitedEvent();
        appRateLimitedEvent.setType("app_rate");
        // when
        this.eventRouter.route(appRateLimitedEvent);
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
        this.eventRouter.route(eventCallback);
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
        this.eventRouter.route(eventCallback);
        // then
        verify(eventProcessorMock, times(1)).process(any(AppMentionEvent.class));
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
        this.eventRouter.route(eventCallback);
        // then
        verify(eventProcessorMock, times(1)).process(any(ReactionAddedEvent.class));
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
        this.eventRouter.route(eventCallback);
        // then
        verify(eventProcessorMock, times(1)).process(any(AppUninstalledEvent.class));
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
        this.eventRouter.route(eventCallback);
        // then
        verify(eventProcessorMock, times(1)).process(any(ChannelArchiveEvent.class));
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
        this.eventRouter.route(eventCallback);
        // then
        verify(eventProcessorMock, times(1)).process(any(ChannelCreatedEvent.class));
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
        this.eventRouter.route(eventCallback);
        // then
        verify(eventProcessorMock, times(1)).process(any(ChannelDeletedEvent.class));
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
        this.eventRouter.route(eventCallback);
        // then
        verify(eventProcessorMock, times(1)).process(any(ChannelHistoryChangedEvent.class));
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
        this.eventRouter.route(eventCallback);
        // then
        verify(eventProcessorMock, times(1)).process(any(ChannelLeftEvent.class));
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
        this.eventRouter.route(eventCallback);
        // then
        verify(eventProcessorMock, times(1)).process(any(ChannelRenameEvent.class));
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
        this.eventRouter.route(eventCallback);
        // then
        verify(eventProcessorMock, times(1)).process(any(ChannelUnarchiveEvent.class));
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
        this.eventRouter.route(eventCallback);
        // then
        verify(eventProcessorMock, times(1)).process(any(DndUpdatedEvent.class));
    }

    @Test
    public void shouldParseDndUpdatedUserEvent() {
        // given
        DndUpdatedUserEvent dndUpdatedUserEvent = new DndUpdatedUserEvent();
        dndUpdatedUserEvent.setType(DND_UPDATED_USER_TYPE);
        EventCallback eventCallback = new EventCallback();
        eventCallback.setEvent(dndUpdatedUserEvent);
        eventCallback.setType(EVENT_CALLBACK_TYPE);
        // when
        this.eventRouter.route(eventCallback);
        // then
        verify(eventProcessorMock, times(1)).process(any(DndUpdatedUserEvent.class));
    }

    @Test
    public void shouldParseEmailDomainChangedEvent() {
        // given
        EmailDomainChangedEvent emailDomainChangedEvent = new EmailDomainChangedEvent();
        emailDomainChangedEvent.setType(EMAIL_DOMAIN_CHANGED_TYPE);
        EventCallback eventCallback = new EventCallback();
        eventCallback.setEvent(emailDomainChangedEvent);
        eventCallback.setType(EVENT_CALLBACK_TYPE);
        // when
        this.eventRouter.route(eventCallback);
        // then
        verify(eventProcessorMock, times(1)).process(any(EmailDomainChangedEvent.class));
    }

    @Test
    public void shouldParseEmojiAddedEvent() {
        // given
        EmojiChangedEvent emojiChangedEvent = new EmojiChangedEvent();
        emojiChangedEvent.setSubtype("add");
        emojiChangedEvent.setType(EMOJI_CHANGED_TYPE);

        EventCallback eventCallback = new EventCallback();
        eventCallback.setEvent(emojiChangedEvent);
        eventCallback.setType(EVENT_CALLBACK_TYPE);
        // when
        this.eventRouter.route(eventCallback);
        // then
        verify(eventProcessorMock, times(1)).process(any(EmojiAddedEvent.class));
    }

    @Test
    public void shouldParseEmojiRemovedEvent() {
        // given
        EmojiChangedEvent emojiChangedEvent = new EmojiChangedEvent();
        emojiChangedEvent.setSubtype("remove");
        emojiChangedEvent.setType(EMOJI_CHANGED_TYPE);

        EventCallback eventCallback = new EventCallback();
        eventCallback.setEvent(emojiChangedEvent);
        eventCallback.setType(EVENT_CALLBACK_TYPE);
        // when
        this.eventRouter.route(eventCallback);
        // then
        verify(eventProcessorMock, times(1)).process(any(EmojiRemovedEvent.class));
    }
}
