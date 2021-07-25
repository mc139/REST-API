package com.crud.tasks.config;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
public class TrelloConfigTestSuite {

    @Mock
    RestTemplate restTemplate;



    @Test
    void shouldGetTrelloConfigWithStubData(){

        //GIVEN
        TrelloConfig trelloConfig = new TrelloConfig();

        //WHEN
        String stub = "TEST";
        trelloConfig.setTrelloToken(stub);
        trelloConfig.setTrelloApiEndpoint(stub);
        trelloConfig.setTrelloApiKey(stub);
        trelloConfig.setTrelloApiUsername(stub);
        //THEN

        Assertions.assertEquals(stub,trelloConfig.getTrelloApiKey());
        Assertions.assertEquals(stub,trelloConfig.getTrelloApiUsername());
        Assertions.assertEquals(stub,trelloConfig.getTrelloApiEndpoint());
        Assertions.assertEquals(stub,trelloConfig.getTrelloToken());

    }

}
