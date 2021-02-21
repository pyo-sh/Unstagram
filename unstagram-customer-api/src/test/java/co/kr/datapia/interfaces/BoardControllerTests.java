package co.kr.datapia.interfaces;

import co.kr.datapia.application.BoardPictureService;
import co.kr.datapia.application.BoardService;
import co.kr.datapia.domain.Board;
import co.kr.datapia.domain.BoardNotFoundException;
import co.kr.datapia.domain.BoardPicture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
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

    @MockBean
    private BoardPictureService boardPictureService;

    @Test
    public void list() throws Exception {
        // boardPicture 생성
        Integer boardPicture_idx = 11;
        String originalFileName = "original_name";
        String storedFilePath = "image/";
        long fileSize = 200L;

        List<BoardPicture> pictures = new ArrayList<>();
        pictures.add(BoardPicture.builder()
                .idx(boardPicture_idx)
                .originalFileName(originalFileName)
                .storedFilePath(storedFilePath)
                .fileSize(fileSize)
                .build());
        // board 생성
        Integer board_idx = 1;
        String user = "Pyo";
        String content = "this is content";
        String reportedDate = new Date().toString();

        List<Board> boards = new ArrayList<>();
        boards.add(Board.builder()
                .idx(board_idx)
                .user(user)
                .content(content)
                .reportedDate(reportedDate)
                .pictures(pictures)
                .build());
        // 의존성 주입
        pictures.get(0).setBoard(boards.get(0));

        given(boardService.getBoards()).willReturn(boards);

        mvc.perform(get("/boards"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"idx\":" + board_idx.toString())))
                .andExpect(content().string(containsString("\"user\":\"" + user + "\"")))
                .andExpect(content().string(containsString("\"reportedDate\":\"" + reportedDate + "\"")))
                .andExpect(content().string(containsString("\"content\":\"" + content + "\"")))
                // pictures 포함하는지?
                .andExpect(content().string(containsString("\"pictures\":")))
                .andExpect(content().string(containsString("\"idx\":" + boardPicture_idx.toString())))
                .andExpect(content().string(containsString("\"originalFileName\":\"" + originalFileName + "\"")))
                .andExpect(content().string(containsString("\"storedFilePath\":\"" + storedFilePath + "\"")))
                .andExpect(content().string(containsString("\"fileSize\":" + fileSize)));
    }

    @Test
    public void createBoardWithValidData() throws Exception {
        given(boardService.addBoard(any())).will(invocation -> {
            Board board = invocation.getArgument(0);
            return Board.builder()
                    .idx(1)
                    .user(board.getUser())
                    .reportedDate(board.getReportedDate())
                    .content(board.getContent())
                    .build();
        });

        given(boardPictureService.addBoardPictures(any(), any())).will(invocation -> {
            Board board = invocation.getArgument(0);
            List<BoardPicture> boardPicture = new ArrayList<>();
            boardPicture.add(BoardPicture.builder()
                    .idx(11)
                    .board(board)
                    .originalFileName("original_name")
                    .storedFilePath("image/")
                    .fileSize(200L)
                    .build());
            return boardPicture;
        });

        String path = "/image";
        String fileName = "test.png";
        MultipartFile mockFile = new MockMultipartFile(path + "/" + fileName, fileName, null, "hi".getBytes());

        mvc.perform(MockMvcRequestBuilders.multipart("/board")
                .file("files", mockFile.getBytes())
                .param("user", "Pyo")
                .param("content", "this is content"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/board/1"))
                .andExpect(content().string("{}"));

        verify(boardService).addBoard(any());
        verify(boardPictureService).addBoardPictures(any(), any());
    }

    @Test
    public void createBoardWithInvalidData() throws Exception {
        mvc.perform(post("/board")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .content("{\"user\":\"\", \"content\":\"\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void readBoardWithExistedID() throws Exception {
        List<BoardPicture> pictures = new ArrayList<>();
        pictures.add(BoardPicture.builder()
                .idx(11)
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
                .pictures(pictures)
                .build();
        // 의존성 주입
        pictures.get(0).setBoard(board);

        given(boardService.getBoard(board.getIdx())).willReturn(board);

        mvc.perform(get("/board/" + board.getIdx().toString()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"idx\":1")))
                .andExpect(content().string(containsString("\"user\":\"Pyo\"")))
                .andExpect(content().string(containsString("\"reportedDate\":\"" + reportedDate + "\"")))
                .andExpect(content().string(containsString("\"content\":\"this is content\"")))
            // pictures 포함하는지?
                .andExpect(content().string(containsString("\"pictures\":")))
                .andExpect(content().string(containsString("\"idx\":11")))
                .andExpect(content().string(containsString("\"originalFileName\":\"original_name\"")))
                .andExpect(content().string(containsString("\"storedFilePath\":\"image/\"")))
                .andExpect(content().string(containsString("\"fileSize\":200")));
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
                .content("{\"user\":\"\", \"content\":\"\", \"pictures\":}}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteBoardWithExistedID() throws Exception {
        List<BoardPicture> pictures = new ArrayList<>();
        pictures.add(BoardPicture.builder()
                .idx(11)
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
        // 의존성 주입
        pictures.get(0).setBoard(board);

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