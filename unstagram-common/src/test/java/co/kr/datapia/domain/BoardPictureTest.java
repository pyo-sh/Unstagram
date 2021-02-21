package co.kr.datapia.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardPictureTest {
    @Test
    public void creation () {
        Integer idx = 11;
        String originalFileName = "image";
        String storedFilePath = "/images/image";
        long fileSize = 100L;

        BoardPicture boardPicture = BoardPicture.builder()
                .idx(idx)
                .originalFileName(originalFileName)
                .storedFilePath(storedFilePath)
                .fileSize(fileSize)
                .build();

        assertEquals(boardPicture.getIdx(), idx);
        assertEquals(boardPicture.getOriginalFileName(), originalFileName);
        assertEquals(boardPicture.getStoredFilePath(), storedFilePath);
        assertEquals(boardPicture.getFileSize(), fileSize);
    }
}