package co.kr.datapia.interfaces;

import co.kr.datapia.application.BoardService;
import co.kr.datapia.domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/boards")
    public List<Board> list () {
        return boardService.getBoards();
    }

    @PostMapping("/board")
    public ResponseEntity<?> create() {
        // TODO : resource 받아서 Service 로 넘기기
        return null;
    }

    @GetMapping("/board/{boardID}")
    public Board readBoard(
            @PathVariable Integer boardID
    ) {
        // TODO: Board ID 로 받기... 아마 getBoard(id)

        return null;
    }
}
