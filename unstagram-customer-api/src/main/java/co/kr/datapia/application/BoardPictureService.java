package co.kr.datapia.application;

import co.kr.datapia.domain.*;
import co.kr.datapia.exceptions.BoardNotFoundException;
import co.kr.datapia.utils.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardPictureService {
    private BoardPictureRepository boardPictureRepository;

    private FileHandler fileHandler;

    @Autowired
    public BoardPictureService(
            BoardPictureRepository boardPictureRepository
    ){
        this.boardPictureRepository = boardPictureRepository;
        this.fileHandler = new FileHandler();
    }

    public byte[] getBytePicture(Integer boardPictureID) throws IOException {
        BoardPicture boardPicture = boardPictureRepository.findByIdx(boardPictureID)
                .orElseThrow(() -> new BoardNotFoundException(boardPictureID));

        return fileHandler.parseByteFile(boardPicture);
    }

    public List<BoardPicture> addBoardPictures(
            Board board,
            List<MultipartFile> files
    ) throws Exception {
        // 파일을 저장하고 그 BoardPicture 에 대한 list 를 가지고 있는다
        List<BoardPicture> list = fileHandler.parseFileInfo(board, files);

        List<BoardPicture> boardPictures = new ArrayList<>();
        for(BoardPicture boardPicture : list) {
            boardPictures.add(boardPictureRepository.save(boardPicture));
        }

        return boardPictures;
    }

    public List<BoardPicture> getBoardPictures(Integer board_id) {
        List<BoardPicture> list = boardPictureRepository.findAllByBoardIdx(board_id);

        return list;
    }

    public List<BoardPicture> updateBoardPictures(

    ){
        return null;
    }

    public void deleteBoardPictures(
            Integer board_id
    ){

    }
}
