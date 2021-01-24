package co.kr.datapia.interfaces;

import co.kr.datapia.application.BoardService;
import co.kr.datapia.domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
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
    public ResponseEntity<?> createBoard(
            @Valid @RequestBody Board resource
    ) throws URISyntaxException {
        Board board = boardService.addBoard(Board.builder()
                .ID(resource.getID())
                .user(resource.getUser())
                .content(resource.getContent())
                .pictures(resource.getPictures())
                .build());

        URI uriLocation = new URI("/board/" + board.getID());
        return ResponseEntity.created(uriLocation).body("{}");
    }

    @GetMapping("/board/{boardID}")
    public Board readBoard(
            @PathVariable Integer boardID
    ) {
        return boardService.getBoard(boardID);
    }

    @PatchMapping("/board/{boardID}")
    public String updateBoard(
            @PathVariable Integer boardID,
            @Valid @RequestBody Board resource
    ){
        // TODO : user 가 다르면 업데이트 X (후에는 Authentication)
        boardService.updateBoard(boardID, resource.getContent(), resource.getPictures());
        return "{\"updated\":\"true\"}";
    }
}
