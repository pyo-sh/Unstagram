package co.kr.datapia.utils;

import co.kr.datapia.domain.Board;
import co.kr.datapia.domain.BoardPicture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTests {
    private FileHandler fileHandler;

    @BeforeEach
    public void setUp () {
        MockitoAnnotations.initMocks(this);

        fileHandler = new FileHandler();
    }

    private void deleteFile(String path) throws NullPointerException{
        File deleteFolder = new File(path);

        if(deleteFolder.exists()){
            File[] deleteFolderList = deleteFolder.listFiles();

            for (int i = 0; i < deleteFolderList.length; i++) {
                if(deleteFolderList[i].isFile()) {
                    deleteFolderList[i].delete();
                }else {
                    deleteFile(deleteFolderList[i].getPath());
                }
                deleteFolderList[i].delete();
            }
            deleteFolder.delete();
        }
    }

    @Test
    void parseByteFile() throws IOException {
        BoardPicture boardPicture = BoardPicture.builder()
            .idx(11)
            .originalFileName("test.png")
            .storedFilePath("testFile/test.jpeg")
            .fileSize(200L)
            .build();

        byte[] image = fileHandler.parseBoardPictureByteFile(boardPicture);

        assertTrue(image.length > 0);
    }

    @Test
    void parseFileInfo() throws Exception {
        Board board = Board.builder()
            .idx(1)
            .reportedDate("Tue Jan 19 2021 17:06:30 GMT+0900")
            .content("this is content")
            .build();

        BoardPicture boardPicture = BoardPicture.builder()
            .idx(11)
            .board(board)
            .originalFileName("test.png")
            .storedFilePath("testFile/test.jpeg")
            .fileSize(200L)
            .build();

        MultipartFile mockFile = new MockMultipartFile(
            "content",
                boardPicture.getStoredFilePath(),
            "image/jpeg",
            fileHandler.parseBoardPictureByteFile(boardPicture));

        List<MultipartFile> mockFiles = new ArrayList<>();
        mockFiles.add(mockFile);

        fileHandler.parseFileBoardPictureInfo(board, mockFiles);

        File imageDir = new File(System.getProperty("user.dir") + "\\images");
        assertTrue(imageDir.exists());

        deleteFile(System.getProperty("user.dir") + "\\images");
    }
}