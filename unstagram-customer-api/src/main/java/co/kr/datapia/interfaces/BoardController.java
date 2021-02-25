package co.kr.datapia.interfaces;

import co.kr.datapia.application.BoardPictureService;
import co.kr.datapia.application.BoardService;
import co.kr.datapia.domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
public class BoardController {
    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardPictureService boardPictureService;

    @GetMapping("/boards")
    public List<Board> list () {
        List<Board> boards = boardService.getBoards();
        return boards;
    }

    @PostMapping("/board")
    public ResponseEntity<?> createBoard(
            //@Valid @RequestParam("user") String userId,
            @Valid @RequestParam("content") String content,
            @Valid @RequestParam("files") List<MultipartFile> files
            //MultipartHttpServletRequest multipartHttpServletRequest
    ) throws Exception {
        Board board = boardService.addBoard(Board.builder()
                //.user(user)
                .content(content)
                .build());

        boardPictureService.addBoardPictures(board, files);

        URI uriLocation = new URI("/board/" + board.getIdx());
        return ResponseEntity.created(uriLocation).body("{}");
    }

    @GetMapping("/board/{boardID}")
    public Board readBoard(
            @PathVariable Integer boardID
    ){
        Board board = boardService.getBoard(boardID);
        return board;
    }

    @PatchMapping("/board/{boardID}")
    public String updateBoard(
            @PathVariable Integer boardID,
            @Valid @RequestBody Board resource
    ){
        // TODO : user 가 다르면 업데이트 X (후에는 Authentication)
        boardService.updateBoard(
                boardID,
                resource.getContent()
        );

        return "{\"updated\":\"true\"}";
    }

    @DeleteMapping("/board/{boardID}")
    public String deleteBoard(
            @PathVariable Integer boardID
    ){
        // TODO : 권한을 가진 자가 이를 처리하게 해야할 것
        boardService.deleteBoard(boardID);
        return "redirect:/";
    }
}
