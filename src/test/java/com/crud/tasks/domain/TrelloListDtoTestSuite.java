package com.crud.tasks.domain;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TrelloListDtoTestSuite {

    void shouldReturnEmptyDto(){

        TrelloListDto trelloListDto = new TrelloListDto(null,null,true);



    }

}
