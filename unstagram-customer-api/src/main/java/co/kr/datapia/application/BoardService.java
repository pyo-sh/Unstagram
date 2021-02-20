package co.kr.datapia.application;

import co.kr.datapia.domain.Board;
import co.kr.datapia.domain.BoardNotFoundException;
import co.kr.datapia.domain.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BoardService {
    private BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> getBoards() {
        return boardRepository.findAll();
    }

    public Board addBoard(Board board){
        Date thisTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        board.setReportedDate(thisTime.toString() + "T" + dateFormat.format(thisTime));

        return boardRepository.save(board);
    }

    public Board getBoard(Integer id){
        Board board = boardRepository.findBoardByIdx(id)
                .orElseThrow(() -> new BoardNotFoundException(id));

        return board;
    }

    // DB 에서 update 는 Transactional 하게 처리하여야 한다 (동시 수정 X)
    @Transactional
    public Board updateBoard(Integer id, String content){
        // null 처리 했지만 사실 Runtime Exception 발생 될 것이다...
        Board board = boardRepository.findBoardByIdx(id).orElse(null);
        // if 처리는 안전함을 위해 적어두었다
        if (board != null){
            board.setContent(content);

            Date thisTime = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

            board.setReportedDate(thisTime.toString() + "T" + dateFormat.format(thisTime));
        }

        return board;
    }

    // DB 에서 delete 도 Transactional 하게 처리하여야 한다
    @Transactional
    public Board deleteBoard(Integer boardID) {
        Board board = boardRepository.findBoardByIdx(boardID)
                .orElseThrow(() -> new BoardNotFoundException(boardID));

        boardRepository.deleteBoardByIdx(board.getIdx());

        return board;
    }
}
