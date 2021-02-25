package co.kr.datapia.application;

import co.kr.datapia.domain.Board;
import co.kr.datapia.exceptions.BoardNotFoundException;
import co.kr.datapia.domain.BoardPicture;
import co.kr.datapia.domain.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class BoardServiceTests {
    private BoardService boardService;
    @Mock
    private BoardRepository boardRepository;

    @BeforeEach
    public void setUp () {
        MockitoAnnotations.initMocks(this);

        mockBoardRepository();

        boardService = new BoardService(boardRepository);
    }
    // 모든 테스트에서 boardRepository 안에 이 mock 객체가 들어가서 Test 될 것이다

    // 테스트 데이터를 this 안에 넣어서 바뀌어도 참조
    private List<Board> mockBoards;

    private void mockBoardRepository () {
        List<BoardPicture> pictures = new ArrayList<>();
        pictures.add(BoardPicture.builder()
                .idx(11)
                .originalFileName("original_name")
                .storedFilePath("image/")
                .fileSize(200L)
                .build());

        this.mockBoards = new ArrayList<>();
        this.mockBoards.add(Board.builder()
                .idx(1)
                //.user("Pyo")
                .reportedDate("Tue Jan 19 2021 17:06:30 GMT+0900")
                .content("this is content")
                .pictures(pictures)
                .build());

        pictures.get(0).setBoard(this.mockBoards.get(0));

        // Interfaces 에 대한 given 및 willReturn 설정
        given(boardRepository.findAll()).willReturn(this.mockBoards);
        given(boardRepository.findBoardByIdx(1)).willReturn(java.util.Optional.of(this.mockBoards.get(0)));
    }

    @Test
    public void getBoards() {
        // Service 에서 레파지토리의 boards 를 받으면 0번 째 순위는?
        List<Board> boards = boardService.getBoards();
        verify(boardRepository).findAll();
        Board board = boards.get(0);

        assertEquals(board.getIdx(), 1);
        //assertEquals(board.getUser(), "Pyo");
        assertEquals(board.getReportedDate(), "Tue Jan 19 2021 17:06:30 GMT+0900");
        assertEquals(board.getContent(), "this is content");

        // Board Picture 와의 연결에 대해서 Test
        List<BoardPicture> boardPictures = board.getPictures();
        BoardPicture boardPicture = boardPictures.get(0);

        assertEquals(boardPicture.getIdx(), 11);
        assertEquals(boardPicture.getOriginalFileName(), "original_name");
        assertEquals(boardPicture.getStoredFilePath(), "image/");
        assertEquals(boardPicture.getFileSize(), 200L);
        // BoardPicture 에서 board idx 를 통해 찾을 수 있을까
        assertEquals(boardPicture.getBoard().getIdx(), 1);
    }

    @Test
    public void addBoard() {
        given(boardRepository.save(any())).will(invocation -> {
            Board board = invocation.getArgument(0);
            return Board.builder()
                    .idx(1)
                    .user(board.getUser())
                    .reportedDate(board.getReportedDate())
                    .content(board.getContent())
                    .build();
        });

        Board board = boardService.addBoard(this.mockBoards.get(0));
        verify(boardRepository).save(this.mockBoards.get(0));

        assertEquals(board.getIdx(), 1);
        //assertEquals(board.getUser(), "Pyo");
        assertEquals(board.getReportedDate(), new Date().toString());
        assertEquals(board.getContent(), "this is content");
    }

    @Test
    public void getBoardWithExistedID() {
        Board board = boardService.getBoard(1);
        verify(boardRepository).findBoardByIdx(board.getIdx());

        Board expectedBoard = this.mockBoards.get(0);

        assertEquals(board.getIdx(), expectedBoard.getIdx());
        //assertEquals(board.getUser(), expectedBoard.getUser());
        assertEquals(board.getReportedDate(), expectedBoard.getReportedDate());
        assertEquals(board.getContent(), expectedBoard.getContent());

        // Board Picture 와의 연결에 대해서 Test
        List<BoardPicture> boardPictures = board.getPictures();
        BoardPicture boardPicture = boardPictures.get(0);

        List<BoardPicture> expectedBoardPictures = expectedBoard.getPictures();
        BoardPicture expectedBoardPicture = expectedBoardPictures.get(0);

        assertEquals(boardPicture.getIdx(), expectedBoardPicture.getIdx());
        assertEquals(boardPicture.getOriginalFileName(), expectedBoardPicture.getOriginalFileName());
        assertEquals(boardPicture.getStoredFilePath(), expectedBoardPicture.getStoredFilePath());
        assertEquals(boardPicture.getFileSize(), expectedBoardPicture.getFileSize());
        // BoardPicture 에서 board idx 를 통해 찾을 수 있을까
        assertEquals(boardPicture.getBoard().getIdx(), expectedBoardPicture.getBoard().getIdx());
    }

    @Test
    public void getBoardWithNotExistedID() {
        assertThrows(BoardNotFoundException.class, () -> boardService.getBoard(242));
    }

    // TODO : update 할 시 picture 이 없다면? X
    @Test
    public void updateBoard() {
        Board board = this.mockBoards.get(0);

        boardService.updateBoard(
                board.getIdx(),
                "My Favorite Food"
        );
        verify(boardRepository).findBoardByIdx(board.getIdx());

        assertEquals(board.getReportedDate(), new Date().toString());
        assertEquals(board.getContent(), "My Favorite Food");
    }

    @Test
    public void deleteBoardWithExistedID() {
        Integer id = this.mockBoards.get(0).getIdx();

        boardService.deleteBoard(id);

        verify(boardRepository).deleteBoardByIdx(id);
    }

    @Test
    public void deleteBoardWithNotExistedID() {
        assertThrows(BoardNotFoundException.class, () -> boardService.deleteBoard(242));
    }
}