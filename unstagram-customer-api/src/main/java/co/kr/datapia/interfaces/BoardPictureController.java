package co.kr.datapia.interfaces;

import co.kr.datapia.application.BoardPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardPictureController {
    @Autowired
    private BoardPictureService boardPictureService;

    // TODO : 사진 다운로드에 대한 Mapping
}
