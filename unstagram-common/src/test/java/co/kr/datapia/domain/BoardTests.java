package co.kr.datapia.domain;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BoardTests {
    @Test
    public void creation () {
        // TODO: 사진 테스트
        Integer id = 1;
        String user = "Pyo";
        String content = "this is the content";
        String reportedDate = new Date().toString();

        Board board = Board.builder()
                .idx(id)
                .user(user)
                .reportedDate(reportedDate)
                .content(content)
                .build();

        assertEquals(board.getIdx(), id);
        assertEquals(board.getUser(), user);
        assertEquals(board.getReportedDate(), reportedDate);
        assertEquals(board.getContent(), content);
    }
}