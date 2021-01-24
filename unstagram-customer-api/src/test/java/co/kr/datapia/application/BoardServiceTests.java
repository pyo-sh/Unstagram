package co.kr.datapia.application;

import co.kr.datapia.domain.Board;
import co.kr.datapia.domain.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    private void mockBoardRepository () {
        List<Board> boards = new ArrayList<>();
        boards.add(Board.builder()
                .ID(1)
                .user("Pyo")
                .content("this is content")
                .build());

        given(boardRepository.findAll()).willReturn(boards);
        // TODO : Interfaces 에 대한 given 및 willReturn 설정

    }

    @Test
    public void getBoards() {
        // Service 에서 레파지토리의 boards 를 받으면 0번 째 순위는?
        List<Board> boards = boardService.getBoards();
        Board board = boards.get(0);

        assertEquals(board.getID(), 1);
    }

    @Test
    public void createWithValidData() {
        // TODO: 제대로된 데이터로 생성되는 것 테스트
        // given addBoard(any()). will(invocation -> { 생성 }
    }
}