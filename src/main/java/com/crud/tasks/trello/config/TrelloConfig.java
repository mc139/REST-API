package com.crud.tasks.trello.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class TrelloConfig {
    @Value("${trello.api.username}")
    private  String trelloApiUsername;
    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;
    @Value("${trello.app.key}")
    private String trelloApiKey;
    @Value("${trello.app.token}")
    private String trelloToken;
}
