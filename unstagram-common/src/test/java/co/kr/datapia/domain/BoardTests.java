package co.kr.datapia.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTests {
    @Test
    public void creation () {
        // TODO: 사진 테스트
        Integer id = 1;
        String user = "Pyo";
        String content = "this is the content";

        Board board = Board.builder()
                .ID(id)
                .user(user)
                .content(content)
                .build();

        assertEquals(board.getID(), id);
        assertEquals(board.getUser(), user);
        assertEquals(board.getContent(), content);
    }
}