package co.kr.datapia.domain;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTests {
    @Test
    public void creation () {
        Integer id = 1;
        String user = "Pyo";
        String content = "this is the content";

        Date thisTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        String reportedDate = thisTime.toString() + "T" + dateFormat.format(thisTime);

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