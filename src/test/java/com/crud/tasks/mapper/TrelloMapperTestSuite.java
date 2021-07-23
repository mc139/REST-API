package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TrelloMapperTestSuite {

    @Test
    void shouldMapToBoard() {

        //GIVEN
        TrelloMapper trelloMapper = new TrelloMapper();

        TrelloListDto trelloList1Dto = new TrelloListDto("1", "test list 1 ", true);
        TrelloListDto trelloList2Dto = new TrelloListDto("2", "test list 2 ", true);
        TrelloListDto trelloList3Dto = new TrelloListDto("3", "test list 3 ", true);
        TrelloListDto trelloList4Dto = new TrelloListDto("4", "test list 4 ", true);
        TrelloListDto trelloList5Dto = new TrelloListDto("5", "test list 5 ", true);

        ArrayList<TrelloListDto> trelloListsList = new ArrayList<>();
        trelloListsList.add(trelloList1Dto);
        trelloListsList.add(trelloList2Dto);
        trelloListsList.add(trelloList3Dto);
        trelloListsList.add(trelloList4Dto);
        trelloListsList.add(trelloList5Dto);

        TrelloBoardDto testBoard = new TrelloBoardDto("test board", "10", trelloListsList);

        //WHEN
        TrelloBoard testTrelloBoard = trelloMapper.mapToBoard(testBoard);

        //THEN

        assertEquals("10", testTrelloBoard.getId());
        assertEquals("test board", testTrelloBoard.getName());
        assertEquals(5, testTrelloBoard.getLists().size());

    }

    @Test
    void shouldMapToBoards() {
        //GIVEN
        TrelloMapper trelloMapper = new TrelloMapper();
        TrelloListDto trelloList1Dto = new TrelloListDto("1", "test list 1 ", true);
        TrelloListDto trelloList2Dto = new TrelloListDto("2", "test list 2 ", true);
        TrelloListDto trelloList3Dto = new TrelloListDto("3", "test list 3 ", true);
        TrelloListDto trelloList4Dto = new TrelloListDto("4", "test list 4 ", true);
        TrelloListDto trelloList5Dto = new TrelloListDto("5", "test list 5 ", true);

        ArrayList<TrelloListDto> trelloListsList = new ArrayList<>();
        trelloListsList.add(trelloList1Dto);
        trelloListsList.add(trelloList2Dto);
        trelloListsList.add(trelloList3Dto);
        trelloListsList.add(trelloList4Dto);
        trelloListsList.add(trelloList5Dto);

        TrelloBoardDto testBoard1 = new TrelloBoardDto("test board1", "101", trelloListsList);
        TrelloBoardDto testBoard2 = new TrelloBoardDto("test board2", "102", trelloListsList);
        TrelloBoardDto testBoard3 = new TrelloBoardDto("test board3", "103", trelloListsList);
        TrelloBoardDto testBoard4 = new TrelloBoardDto("test board4", "104", trelloListsList);

        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(testBoard1);
        trelloBoardDtos.add(testBoard2);
        trelloBoardDtos.add(testBoard3);
        trelloBoardDtos.add(testBoard4);

        //WHEN
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);

        //THEN
        assertEquals(4, trelloBoards.size());

    }

    @Test
    void shouldMapToBoardsDto() {
        //GIVEN
        TrelloMapper trelloMapper = new TrelloMapper();
        TrelloList trelloList1 = new TrelloList("1", "test list 1 ", true);
        TrelloList trelloList2 = new TrelloList("2", "test list 2 ", true);
        TrelloList trelloList3 = new TrelloList("3", "test list 3 ", true);
        TrelloList trelloList4 = new TrelloList("4", "test list 4 ", true);
        TrelloList trelloList5 = new TrelloList("5", "test list 5 ", true);

        ArrayList<TrelloList> trelloListsList = new ArrayList<>();
        trelloListsList.add(trelloList1);
        trelloListsList.add(trelloList2);
        trelloListsList.add(trelloList3);
        trelloListsList.add(trelloList4);
        trelloListsList.add(trelloList5);

        TrelloBoard testBoard1 = new TrelloBoard("test board1", "101", trelloListsList);
        TrelloBoard testBoard2 = new TrelloBoard("test board2", "102", trelloListsList);
        TrelloBoard testBoard3 = new TrelloBoard("test board3", "103", trelloListsList);
        TrelloBoard testBoard4 = new TrelloBoard("test board4", "104", trelloListsList);

        List<TrelloBoard> trelloBoard = new ArrayList<>();
        trelloBoard.add(testBoard1);
        trelloBoard.add(testBoard2);
        trelloBoard.add(testBoard3);
        trelloBoard.add(testBoard4);

        //WHEN
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoard);

        //THEN
        assertEquals(4, trelloBoardDtos.size());

    }

    @Test
    void shouldMapToList() {
        //GIVEN
        TrelloMapper trelloMapper = new TrelloMapper();
        TrelloListDto trelloList1Dto = new TrelloListDto("1", "test list 1 ", true);
        TrelloListDto trelloList2Dto = new TrelloListDto("2", "test list 2 ", true);
        TrelloListDto trelloList3Dto = new TrelloListDto("3", "test list 3 ", true);

        ArrayList<TrelloListDto> trelloListsDtos = new ArrayList<>();
        trelloListsDtos.add(trelloList1Dto);
        trelloListsDtos.add(trelloList3Dto);
        trelloListsDtos.add(trelloList2Dto);

        //WHEN

        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListsDtos);

        //THEN

        assertEquals(3, trelloLists.size());

    }

    @Test
    void shouldMapToListDto() {
        //GIVEN

        TrelloMapper trelloMapper = new TrelloMapper();
        TrelloList trelloList1 = new TrelloList("1", "test list 1 ", true);
        TrelloList trelloList2 = new TrelloList("2", "test list 2 ", true);
        TrelloList trelloList3 = new TrelloList("3", "test list 3 ", true);

        ArrayList<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList1);
        trelloLists.add(trelloList3);
        trelloLists.add(trelloList2);


        //WHEN
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);

        //THEN

        assertEquals(3, trelloListDtos.size());
    }

    @Test
    void shouldMapToCardDto() {
        // GIVEN
        TrelloMapper trelloMapper = new TrelloMapper();
        TrelloCard trelloCard1 = new TrelloCard("test name 1",
                "test description",
                "test pos 1", "1");
        TrelloCard trelloCard2 = new TrelloCard("test name 2",
                "test description",
                "test pos 2", "2");
        TrelloCard trelloCard3 = new TrelloCard("test name 3",
                "test description",
                "test pos 3", "3");

        // WHEN
        TrelloCardDto trelloCardDto1 = trelloMapper.mapToCardDto(trelloCard1);
        TrelloCardDto trelloCardDto2 = trelloMapper.mapToCardDto(trelloCard2);
        TrelloCardDto trelloCardDto3 = trelloMapper.mapToCardDto(trelloCard3);


        // THEN

        assertEquals("test name 1", trelloCardDto1.getName());
        assertEquals("test name 2", trelloCardDto2.getName());
        assertEquals("test name 3", trelloCardDto3.getName());
        assertEquals("3", trelloCardDto3.getListId());
        assertEquals("2", trelloCardDto2.getListId());
        assertEquals("1", trelloCardDto1.getListId());

    }

    @Test
    void shouldMapToCard() {

        // GIVEN
        TrelloMapper trelloMapper = new TrelloMapper();
        TrelloCardDto trelloCard1Dto = new TrelloCardDto("test name 1",
                "test description",
                "test pos 1", "1");
        TrelloCardDto trelloCard2Dto = new TrelloCardDto("test name 2",
                "test description",
                "test pos 2", "2");
        TrelloCardDto trelloCard3Dto = new TrelloCardDto("test name 3",
                "test description",
                "test pos 3", "3");
        //WHEN
        TrelloCard trelloCard1 = trelloMapper.mapToCard(trelloCard1Dto);
        TrelloCard trelloCard2 = trelloMapper.mapToCard(trelloCard2Dto);
        TrelloCard trelloCard3 = trelloMapper.mapToCard(trelloCard3Dto);

        //THEN

        assertEquals("test name 1", trelloCard1.getName());
        assertEquals("test name 2", trelloCard2.getName());
        assertEquals("test name 3", trelloCard3.getName());
        assertEquals("3", trelloCard3.getListId());
        assertEquals("2", trelloCard2.getListId());
        assertEquals("1", trelloCard1.getListId());

    }

}
