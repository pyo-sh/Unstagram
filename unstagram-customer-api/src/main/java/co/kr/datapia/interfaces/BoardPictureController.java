package co.kr.datapia.interfaces;

import co.kr.datapia.application.BoardPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class BoardPictureController {
    @Autowired
    private BoardPictureService boardPictureService;

    // 사진을 byte Array 로 반환하여 src="/boardpicture/{boardPictureID}" 로 접근하면 사진이 보인다
    @GetMapping("/boardpicture/{boardPictureID}")
    public ResponseEntity<byte[]> readBoard(
            @PathVariable Integer boardPictureID
    ) throws IOException {
        byte[] imageByteArray = boardPictureService.getBytePicture(boardPictureID);
        return new ResponseEntity<>(imageByteArray, HttpStatus.OK);
    }
}
