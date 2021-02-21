package co.kr.datapia.application;

import co.kr.datapia.domain.Board;
import co.kr.datapia.domain.BoardPicture;
import co.kr.datapia.domain.BoardPictureRepository;
import co.kr.datapia.domain.FileHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class BoardPictureServiceTests {
    private BoardPictureService boardPictureService;

    @Mock
    private BoardPictureRepository boardPictureRepository;

    private FileHandler fileHandler;

    @BeforeEach
    public void setUp () {
        MockitoAnnotations.initMocks(this);

        mockBoardPictureRepository();

        boardPictureService = new BoardPictureService(boardPictureRepository);
        fileHandler = new FileHandler();
    }

    // 테스트 데이터를 this 안에 넣어서 바뀌어도 참조
    private List<BoardPicture> mockBoardPictures;

    private void mockBoardPictureRepository () {
        Board board = Board.builder()
                .idx(1)
                .user("Pyo")
                .reportedDate("Tue Jan 19 2021 17:06:30 GMT+0900")
                .content("this is content")
                .build();

        this.mockBoardPictures = new ArrayList<>();
        this.mockBoardPictures.add(BoardPicture.builder()
                .idx(11)
                .originalFileName("test.png")
                .storedFilePath("testFile/test.jpeg")
                .fileSize(200L)
                .build());

        board.setPictures(this.mockBoardPictures);

        // Interfaces 에 대한 given 및 willReturn 설정
        given(boardPictureRepository.findByIdx(11)).willReturn(java.util.Optional.of(this.mockBoardPictures.get(0)));
        given(boardPictureRepository.findAllByBoardIdx(1)).willReturn(this.mockBoardPictures);
    }

    // 테스트 후 파일을 삭제하는 함수
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
    void getBytePicture() throws IOException {
        BoardPicture mockBoardPicture = this.mockBoardPictures.get(0);

        byte[] mockImage = fileHandler.parseByteFile(mockBoardPicture);

        byte[] image = boardPictureService.getBytePicture(11);
        verify(boardPictureRepository).findByIdx(11);

        assertTrue(Arrays.equals(image, mockImage));
    }

    @Test
    void addBoardPictures() throws Exception {
        MultipartFile mockFile = new MockMultipartFile(
                "content",
                this.mockBoardPictures.get(0).getStoredFilePath(),
                "image/jpeg",
                fileHandler.parseByteFile(this.mockBoardPictures.get(0)));

        List<MultipartFile> mockFiles = new ArrayList<>();
        mockFiles.add(mockFile);

        given(boardPictureRepository.save(any())).will(invocation -> {
            BoardPicture boardPicture = invocation.getArgument(0);
            return BoardPicture.builder()
                    .idx(11)
                    .originalFileName(boardPicture.getOriginalFileName())
                    .storedFilePath(boardPicture.getStoredFilePath())
                    .fileSize(boardPicture.getFileSize())
                    .build();
        });

        List<BoardPicture> boardPictures = boardPictureService.addBoardPictures(this.mockBoardPictures.get(0).getBoard(), mockFiles);
        verify(boardPictureRepository).save(any());

        BoardPicture boardPicture = boardPictures.get(0);
        assertEquals(boardPicture.getIdx(), 11);
        assertEquals(boardPicture.getOriginalFileName().getClass().getName(), "java.lang.String");
        assertEquals(boardPicture.getStoredFilePath().getClass().getName(), "java.lang.String");
        assertEquals(boardPicture.getFileSize(), mockFile.getSize());

        // fileHandler 가 저장한 파일을 삭제
        deleteFile(System.getProperty("user.dir") + "\\images");
    }

    @Test
    void getBoardPictures() {
        List<BoardPicture> boardPictures = boardPictureService.getBoardPictures(1);
        verify(boardPictureRepository).findAllByBoardIdx(1);

        BoardPicture boardPicture = boardPictures.get(0);
        assertEquals(boardPicture.getIdx(), 11);
        assertEquals(boardPicture.getOriginalFileName(), "test.png");
        assertEquals(boardPicture.getStoredFilePath(), "testFile/test.jpeg");
        assertEquals(boardPicture.getFileSize(), 200L);
    }

    @Test
    void updateBoardPictures() {

    }

    @Test
    void deleteBoardPictures() {

    }
}