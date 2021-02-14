package co.kr.datapia.application;

import co.kr.datapia.domain.BoardPicture;
import co.kr.datapia.domain.BoardPictureRepository;
import co.kr.datapia.domain.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
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

    public List<BoardPicture> addBoardPictures(
            Integer board_id,
            List<MultipartFile> files
    ) throws Exception {
        // 파일을 저장하고 그 BoardPicture 에 대한 list 를 가지고 있는다
        List<BoardPicture> list = fileHandler.parseFileInfo(board_id, files);

        List<BoardPicture> boardPictures = new ArrayList<>();
        for(BoardPicture boardPicture : list) {
            boardPictures.add(boardPictureRepository.save(boardPicture));
        }

        return boardPictures;
    }

    public List<BoardPicture> getBoardPictures(Integer board_id) throws IOException {
        List<BoardPicture> list = boardPictureRepository.findAllByBoardIdx(board_id);

        //List<File> images = fileHandler.parseMultipartFile(list);

        String absolutePath = new File("").getAbsolutePath() + "\\";

        for(BoardPicture boardPicture : list){
            BufferedImage originalImage = ImageIO.read(new File(absolutePath + boardPicture.getStoredFilePath()));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "jpeg", baos);
            baos.flush();

            boardPicture.setImages(baos.toByteArray());
        }

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
