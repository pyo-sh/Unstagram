package co.kr.datapia.interfaces;

import co.kr.datapia.application.BoardService;
import co.kr.datapia.domain.Board;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BoardController.class)
class BoardControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private BoardService boardService;

    @Test
    public void list() throws Exception {
        List<Board> boards = new ArrayList<>();
        boards.add(Board.builder()
                .ID(1)
                .user("Pyo")
                .content("this is content")
                .build());

        given(boardService.getBoards()).willReturn(boards);

        mvc.perform(get("/boards"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("\"id\":1")))
            .andExpect(content().string(containsString("\"user\":\"Pyo\"")));
    }

    @Test
    public void create() {
        // TODO: 생성 테스트 ...
        // given .createBoard(id) .willReturn ?
    }
}