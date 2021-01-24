package co.kr.datapia.interfaces;

import co.kr.datapia.application.BoardService;
import co.kr.datapia.domain.Board;
import co.kr.datapia.domain.BoardNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BoardController.class)
class BoardControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private BoardService boardService;

    // TODO : List<String> 을 class 로

    @Test
    public void list() throws Exception {
        List<String> pictures = new ArrayList<>();
        pictures.add("food");
        pictures.add("background");

        List<Board> boards = new ArrayList<>();
        boards.add(Board.builder()
                .ID(1)
                .user("Pyo")
                .content("this is content")
                .pictures(pictures)
                .build());

        given(boardService.getBoards()).willReturn(boards);

        mvc.perform(get("/boards"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1")))
                .andExpect(content().string(containsString("\"user\":\"Pyo\"")))
                .andExpect(content().string(containsString("\"content\":\"this is content\"")))
                .andExpect(content().string(containsString("\"pictures\":[\"food\",\"background\"]")));
    }

    @Test
    public void createBoardWithValidData() throws Exception {
        given(boardService.addBoard(any())).will(invocation -> {
            Board board = invocation.getArgument(0);
            return Board.builder()
                    .ID(1)
                    .user(board.getUser())
                    .content(board.getContent())
                    .pictures(board.getPictures())
                    .build();
        });

        mvc.perform(post("/board")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1, \"user\":\"Pyo\", \"content\":\"this is content\"" +
                        ", \"pictures\":[\"food\"]}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/board/1"))
                .andExpect(content().string("{}"));

        verify(boardService).addBoard(any());
    }

    @Test
    public void createBoardWithInvalidData() throws Exception {
        mvc.perform(post("/board")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":\"\", \"user\":\"\", \"content\":\"\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void readBoardWithExistedID() throws Exception {
        List<String> pictures = new ArrayList<>();
        pictures.add("food");
        pictures.add("background");

        Board board = Board.builder()
                .ID(1)
                .user("Pyo")
                .content("this is content")
                .pictures(pictures)
                .build();

        given(boardService.getBoard(board.getID())).willReturn(board);

        mvc.perform(get("/board/" + board.getID().toString()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1")))
                .andExpect(content().string(containsString("\"user\":\"Pyo\"")))
                .andExpect(content().string(containsString("\"content\":\"this is content\"")))
                .andExpect(content().string(containsString("\"pictures\":[\"food\",\"background\"]")));
    }

    @Test
    public void readBoardWithNotExistedID() throws Exception {
        Integer testID = 242;

        given(boardService.getBoard(testID)).willThrow(new BoardNotFoundException(testID));

        mvc.perform(get("/board/" + testID.toString()))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("{}")));
    }

    @Test
    public void updateBoardWithValidData() throws Exception {
        Integer id = 1;
        String user = "Pyo";
        String content = "My Favorite Color";
        List<String> pictures = new ArrayList<>();
        pictures.add("food");
        pictures.add("background");

        mvc.perform(patch("/board/" + id.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":" + id.toString()
                        + ", \"user\":\"" + user
                        + "\", \"content\":\"" + content
                        + "\", \"pictures\":[\"food\", \"background\"]}"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"updated\":\"true\"}")));

        verify(boardService).updateBoard(id, content, pictures);
    }

    @Test
    public void updateBoardWithInvalidData() throws Exception {
        Integer id = 1;
        mvc.perform(patch("/board/" + id.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1, \"user\":\"Pyo\", \"content\":\"\", \"pictures\":}}"))
                .andExpect(status().isBadRequest());
    }
}