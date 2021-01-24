package co.kr.datapia.application;

import co.kr.datapia.domain.Board;
import co.kr.datapia.domain.BoardNotFoundException;
import co.kr.datapia.domain.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

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
        List<String> pictures = new ArrayList<>();
        pictures.add("food");
        pictures.add("background");

        this.mockBoards = new ArrayList<>();
        this.mockBoards.add(Board.builder()
                .ID(1)
                .user("Pyo")
                .content("this is content")
                .pictures(pictures)
                .build());

        // Interfaces 에 대한 given 및 willReturn 설정
        given(boardRepository.findAll()).willReturn(this.mockBoards);
        given(boardRepository.findBoardByID(1)).willReturn(java.util.Optional.of(this.mockBoards.get(0)));
    }

    @Test
    public void getBoards() {
        // Service 에서 레파지토리의 boards 를 받으면 0번 째 순위는?
        List<Board> boards = boardService.getBoards();
        Board board = boards.get(0);

        assertEquals(board.getID(), 1);
    }

    @Test
    public void addBoard() {
        given(boardRepository.save(any())).will(invocation -> {
            Board board = invocation.getArgument(0);
            return Board.builder()
                    .ID(1)
                    .user(board.getUser())
                    .content(board.getContent())
                    .pictures(board.getPictures())
                    .build();
        });

        List<String> pictures = new ArrayList<>();
        pictures.add("food");
        pictures.add("background");

        Board board = Board.builder()
                .user("Pyo")
                .content("this is content")
                .pictures(pictures)
                .build();

        Board created = boardService.addBoard(board);

        assertEquals(created.getID(), 1);
        assertEquals(created.getUser(), "Pyo");
        assertEquals(created.getContent(), "this is content");
        assertTrue(created.getPictures().containsAll(pictures));
    }

    @Test
    public void getBoardWithExistedID() {
        Board board = boardService.getBoard(1);

        Board expectedBoard = this.mockBoards.get(0);

        assertEquals(board.getID(), expectedBoard.getID());
        assertEquals(board.getUser(), expectedBoard.getUser());
        assertEquals(board.getContent(), expectedBoard.getContent());
        assertTrue(board.getPictures().containsAll(expectedBoard.getPictures()));
    }

    @Test
    public void getBoardWithNotExistedID() {
        assertThrows(BoardNotFoundException.class, () -> boardService.getBoard(242));
    }

    // TODO : update 할 시 picture 이 없다면? X
    @Test
    public void updateBoard() {
        Board board = this.mockBoards.get(0);
        List<String> pictures = new ArrayList<>();
        pictures.add("food");

        given(boardRepository.findBoardByID(board.getID())).willReturn(Optional.of(board));

        boardService.updateBoard(board.getID(), "My Favorite Food", pictures);

        assertEquals(board.getContent(), "My Favorite Food");
        assertTrue(board.getPictures().containsAll(pictures));
    }
}