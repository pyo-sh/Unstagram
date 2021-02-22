package co.kr.datapia.interfaces;

import co.kr.datapia.application.BoardPictureService;
import co.kr.datapia.exceptions.BoardNotFoundException;
import co.kr.datapia.domain.BoardPicture;
import co.kr.datapia.utils.FileHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BoardPictureController.class)
class BoardPictureControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private BoardPictureService boardPictureService;

    @Test
    public void readBoardWithExistedID() throws Exception {
        Integer board_idx = 1;

        byte[] mockImage = new FileHandler().parseByteFile(BoardPicture.builder()
                .idx(11)
                .originalFileName("test.png")
                .storedFilePath("testFile/test.jpeg")
                .fileSize(200L)
                .build());

        given(boardPictureService.getBytePicture(board_idx)).willReturn(mockImage);

        mvc.perform(get("/boardpicture/" + board_idx.toString()))
                .andExpect(status().isOk())
                .andExpect(content().bytes(mockImage));

        verify(boardPictureService).getBytePicture(board_idx);
    }

    @Test
    public void readBoardWithNotExistedID() throws Exception {
        Integer testID = 242;

        given(boardPictureService.getBytePicture(testID)).willThrow(new BoardNotFoundException(testID));

        mvc.perform(get("/board/" + testID.toString()))
                .andExpect(status().isNotFound());
    }
}