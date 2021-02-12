package co.kr.datapia.application;

import co.kr.datapia.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class BoardServiceTests {
    private BoardService boardService;
    @Mock
    private BoardRepository boardRepository;
    @Mock
    private BoardPictureRepository boardPictureRepository;

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
                .boardIdx(1)
                .originalFileName("original_name")
                .storedFilePath("image/")
                .fileSize(200L)
                .build());

        this.mockBoards = new ArrayList<>();
        this.mockBoards.add(Board.builder()
                .idx(1)
                .user("Pyo")
                .reportedDate("Tue Jan 19 2021 17:06:30 GMT+0900")
                .content("this is content")
                .pictures(pictures)
                .build());

        // Interfaces 에 대한 given 및 willReturn 설정
        given(boardRepository.findAll()).willReturn(this.mockBoards);
        given(boardRepository.findBoardByIdx(1)).willReturn(java.util.Optional.of(this.mockBoards.get(0)));
    }

    @Test
    public void getBoards() {
        // Service 에서 레파지토리의 boards 를 받으면 0번 째 순위는?
        List<Board> boards = boardService.getBoards();
        Board board = boards.get(0);

        assertEquals(board.getIdx(), 1);
    }

    @Test
    public void addBoard() throws Exception {
        given(boardRepository.save(any())).will(invocation -> {
            Board board = invocation.getArgument(0);
            return Board.builder()
                    .idx(1)
                    .user(board.getUser())
                    .reportedDate(board.getReportedDate())
                    .content(board.getContent())
                    .pictures(board.getPictures())
                    .build();
        });

        String user = "Pyo";
        String content = "this is content";
        String reportedDate = new Date().toString();

        Board board = Board.builder()
                .user(user)
                .content(content)
                .reportedDate(reportedDate)
                .build();

        List<MultipartFile> files = new ArrayList<>();

        Board created = boardService.addBoard(board);

        assertEquals(created.getIdx(), 1);
        assertEquals(created.getUser(), user);
        assertEquals(created.getContent(), content);
        assertEquals(created.getReportedDate(), reportedDate);
    }

    @Test
    public void getBoardWithExistedID() {
        Board board = boardService.getBoard(1);

        Board expectedBoard = this.mockBoards.get(0);

        assertEquals(board.getIdx(), expectedBoard.getIdx());
        assertEquals(board.getUser(), expectedBoard.getUser());
        assertEquals(board.getReportedDate(), expectedBoard.getReportedDate());
        assertEquals(board.getContent(), expectedBoard.getContent());
        assertTrue(board.getPictures().containsAll(expectedBoard.getPictures()));
    }

    @Test
    public void getBoardWithNotExistedID() {
        assertThrows(BoardNotFoundException.class, () -> boardService.getBoard(242));
    }

    // TODO : update 할 시 picture 이 없다면? X
    @Test
    public void updateBoard() throws ParseException {
        Board board = this.mockBoards.get(0);

        given(boardRepository.findBoardByIdx(board.getIdx())).willReturn(Optional.of(board));

        boardService.updateBoard(
                board.getIdx(),
                "My Favorite Food"
        );

        Date nowDate = new Date();

        assertEquals(board.getReportedDate(), nowDate.toString());
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