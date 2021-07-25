package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrelloServiceTestSuite {

    @Mock
    private AdminConfig adminConfig;

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Test
    void shouldReturnEmptyListOfTrelloBoards() {

        //GIVEN
        when(trelloClient.getTrelloBoards()).thenReturn(new ArrayList<>());

        //WHEN
        List<TrelloBoardDto> expectedListOfTrelloBoardsDtos = trelloService.fetchTrelloBoards();

        //THEN
        Assertions.assertTrue(expectedListOfTrelloBoardsDtos.isEmpty());

    }

    @Test
    void shouldCreateTrelloCards() {

        //GIVEN
        CreatedTrelloCardDto createdCard = new CreatedTrelloCardDto("1",
                "card",
                "test.com");
        TrelloCardDto card = new TrelloCardDto("test card",
                "test description",
                "test pos",
                "1");

        when(trelloClient.createNewCard(card)).thenReturn(createdCard);
        when(adminConfig.getAdminMail()).thenReturn("test@mail.com");

        //WHEN
        CreatedTrelloCardDto expected = trelloService.createTrelloCard(card);

        //THEN
        Assertions.assertEquals(createdCard.getId(), expected.getId());
        Assertions.assertEquals(createdCard.getName(), expected.getName());
        Assertions.assertEquals(createdCard.getShortUrl(), expected.getShortUrl());
        verify(adminConfig, times(1)).getAdminMail();

    }

}
