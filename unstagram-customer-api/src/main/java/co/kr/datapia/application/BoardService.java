package co.kr.datapia.application;

import co.kr.datapia.domain.Board;
import co.kr.datapia.domain.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Board addBoard() {
        // TODO : Repository 에 save 후 돌려주기
        return null;
    }

    public Board getBoard(Integer id){
        // TODO : Repository 에 반환하는 interface 받기 (아마 findById(id))
        return null;
    }
}
