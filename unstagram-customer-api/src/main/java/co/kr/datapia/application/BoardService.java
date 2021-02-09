package co.kr.datapia.application;

import co.kr.datapia.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BoardService {
    private BoardRepository boardRepository;

    private BoardPictureRepository boardPictureRepository;

    private FileHandler fileHandler;

    @Autowired
    public BoardService(BoardRepository boardRepository, BoardPictureRepository boardPictureRepository) {
        this.boardRepository = boardRepository;
        this.boardPictureRepository = boardPictureRepository;
        this.fileHandler = new FileHandler();
    }

    public List<Board> getBoards() {
        return boardRepository.findAll();
    }

    public Board addBoard(
            Board board,
            List<MultipartFile> files
    ) throws Exception {
        // ID 를 생성한 것을 사용하기 위해 미리 저장하고 그것을 수정
        board.setReported_date(new Date().toString());
        Board returnBoard = boardRepository.save(board);

        // 파일을 저장하고 그 BoardPicture 에 대한 list 를 가지고 있는다
        List<BoardPicture> list = fileHandler.parseFileInfo(returnBoard.getID(), files);

        if(list.isEmpty()){
            // TODO : 파일이 없을 땐 어떻게 해야할까.. 고민을 해보아야 할 것
        }
        // 파일에 대해 DB에 저장하고 가지고 있을 것
        else{
            List<BoardPicture> pictureBeans = new ArrayList<>();
            for(BoardPicture boardPicture : list) {
                pictureBeans.add(boardPictureRepository.save(boardPicture));
            }
            returnBoard.setPictures(pictureBeans);
        }

        return returnBoard;
    }

    public Board getBoard(Integer id){
        Board board = boardRepository.findBoardByID(id)
                .orElseThrow(() -> new BoardNotFoundException(id));

//        List<BoardPicture> pictures = boardPictureRepository.findAllByBoardIdx(id);
//        board.setPictures(pictures);

        return board;
    }

    // DB 에서 update 는 Transactional 하게 처리하여야 한다 (동시 수정 X)
    @Transactional
    public Board updateBoard(Integer id, String content){
        // null 처리 했지만 사실 Runtime Exception 발생 될 것이다...
        Board board = boardRepository.findBoardByID(id).orElse(null);
        // if 처리는 안전함을 위해 적어두었다
        if (board != null){
            board.setContent(content);
            board.setReported_date(new Date().toString());
        }

        return board;
    }

    // DB 에서 delete 도 Transactional 하게 처리하여야 한다
    @Transactional
    public Board deleteBoard(Integer boardID) {
        Board board = boardRepository.findBoardByID(boardID)
                .orElseThrow(() -> new BoardNotFoundException(boardID));

        boardRepository.deleteBoardByID(board.getID());

        return board;
    }
}
