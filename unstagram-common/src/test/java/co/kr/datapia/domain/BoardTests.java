package co.kr.datapia.domain;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTests {
    @Test
    public void creation () {
        Integer id = 1;
        User user = User.builder().userId("Pyo").build();
        String content = "this is the content";
        String reportedDate = new Date().toString();

        Board board = Board.builder()
                .idx(id)
                .user(user)
                .reportedDate(reportedDate)
                .content(content)
                .build();

        assertEquals(board.getIdx(), id);
        assertEquals(board.getUser().getUserId(), user.getUserId());
        assertEquals(board.getReportedDate(), reportedDate);
        assertEquals(board.getContent(), content);
    }
}