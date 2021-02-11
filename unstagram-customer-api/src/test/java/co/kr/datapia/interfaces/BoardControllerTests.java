package co.kr.datapia.interfaces;

import co.kr.datapia.application.BoardService;
import co.kr.datapia.domain.Board;
import co.kr.datapia.domain.BoardNotFoundException;
import co.kr.datapia.domain.BoardPicture;
import co.kr.datapia.domain.FileHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
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

    @Test
    public void list() throws Exception {
        Integer board_id = 1;
        String user = "Pyo";
        String content = "this is content";
        String reportedDate = new Date().toString();

        List<BoardPicture> pictures = new ArrayList<>();
        pictures.add(BoardPicture.builder()
                .idx(11)
                .boardIdx(board_id)
                .originalFileName("original_name")
                .storedFilePath("image/")
                .fileSize(200L)
                .build());

        List<Board> boards = new ArrayList<>();
        boards.add(Board.builder()
                .idx(board_id)
                .user(user)
                .content(content)
                .reportedDate(reportedDate)
                .pictures(pictures)
                .build());

        given(boardService.getBoards()).willReturn(boards);

        mvc.perform(get("/boards"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"idx\":" + board_id.toString())))
                .andExpect(content().string(containsString("\"user\":\"" + user + "\"")))
                .andExpect(content().string(containsString("\"reportedDate\":\"" + reportedDate + "\"")))
                .andExpect(content().string(containsString("\"content\":\"" + content + "\"")))
                .andExpect(content().string(containsString("\"pictures\":")));
        // TODO : pictures 를 확인 ?
    }

    @Test
    public void createBoardWithValidData() throws Exception {
        given(boardService.addBoard(any(), any())).will(invocation -> {
            Board board = invocation.getArgument(0);
//            List<MultipartFile> files = invocation.getArgument(1);
//            List<BoardPicture> pictures = new FileHandler().parseFileInfo(1, files);
            return Board.builder()
                    .idx(1)
                    .user(board.getUser())
                    .reportedDate(board.getReportedDate())
                    .content(board.getContent())
                    //.pictures(pictures)
                    .build();
        });

        // TODO : pictures

        mvc.perform(post("/board")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("user", "Pyo")
                .param("content", "this is content")
                .content("{}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/board/1"))
                .andExpect(content().string("{}"));

        verify(boardService).addBoard(any(), any());
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
        List<BoardPicture> pictures = new ArrayList<>();
        pictures.add(BoardPicture.builder()
                .idx(11)
                .boardIdx(1)
                .originalFileName("original_name")
                .storedFilePath("image/")
                .fileSize(200L)
                .build());

        String reportedDate = new Date().toString();

        Board board = Board.builder()
                .idx(1)
                .user("Pyo")
                .reportedDate(reportedDate)
                .content("this is content")
                //.pictures(pictures)
                .build();

        given(boardService.getBoard(board.getIdx())).willReturn(board);

        mvc.perform(get("/board/" + board.getIdx().toString()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"idx\":1")))
                .andExpect(content().string(containsString("\"user\":\"Pyo\"")))
                .andExpect(content().string(containsString("\"reportedDate\":\"" + reportedDate + "\"")))
                .andExpect(content().string(containsString("\"content\":\"this is content\"")));
                //.andExpect(content().string(containsString("\"pictures\":[]")));
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

        mvc.perform(patch("/board/" + id.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"user\":\"" + user
                        + "\", \"content\":\"" + content
                        + "\", \"pictures\":[]}"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"updated\":\"true\"}")));

        verify(boardService).updateBoard(any(), any());
    }

    @Test
    public void updateBoardWithInvalidData() throws Exception {
        Integer id = 1;
        mvc.perform(patch("/board/" + id.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1, \"user\":\"Pyo\", \"content\":\"\", \"pictures\":}}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteBoardWithExistedID() throws Exception {
        List<BoardPicture> pictures = new ArrayList<>();
        pictures.add(BoardPicture.builder()
                .idx(11)
                .boardIdx(1)
                .originalFileName("original_name")
                .storedFilePath("image/")
                .fileSize(200L)
                .build());

        Board board = Board.builder()
                .idx(1)
                .user("Pyo")
                .reportedDate("Tue Jan 19 2021 17:06:30 GMT+0900")
                .content("this is content")
                .pictures(pictures)
                .build();

        given(boardService.deleteBoard(board.getIdx())).willReturn(board);

        mvc.perform(delete("/board/" + board.getIdx().toString()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("redirect:/")));

        verify(boardService).deleteBoard(board.getIdx());
    }

    @Test
    public void deleteBoardWithNotExistedID() throws Exception {
        Integer id = 1;

        given(boardService.deleteBoard(id)).willThrow(new BoardNotFoundException(id));

        mvc.perform(delete("/board/" + id.toString()))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("{}")));

        verify(boardService).deleteBoard(id);
    }
}