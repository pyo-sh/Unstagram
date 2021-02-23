package co.kr.datapia.interfaces;

import co.kr.datapia.application.UserService;
import co.kr.datapia.domain.User;
import co.kr.datapia.model.request.UserCreateRequestDto;
import co.kr.datapia.model.response.UserReadResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@CrossOrigin
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<?> create(
            @RequestBody UserCreateRequestDto resource
    ) throws URISyntaxException {
        String phone = resource.getPhone();
        String userId = resource.getUserId();
        String name = resource.getName();
        String password = resource.getPassword();

        User user = userService.registerUser(userId, name, phone, password);

        String url = "/users/" + user.getIdx();
        return ResponseEntity.created(new URI(url)).body("{}");
    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<UserReadResponseDto> read(
            @PathVariable String user_id
    ){
        User user = userService.getUser(user_id);

        UserReadResponseDto userReadResponseDto = UserReadResponseDto.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .phone(user.getPhone())
                .website(user.getWebsite())
                .userImagePath(user.getUserImagePath())
                .build();

        return ResponseEntity.ok().body(userReadResponseDto);
    }

    @PostMapping("/user/image")
    public ResponseEntity<?> updateUserImage(
            @Valid @RequestParam("userId") String userId,
            @Valid @RequestParam("files") MultipartFile file
    ) throws Exception {
        User user = userService.setImage(userId, file);

        URI uriLocation = new URI("/user/image/" + user.getIdx());
        return ResponseEntity.created(uriLocation).body("{}");
    }

    // 사진을 byte Array 로 반환
    @GetMapping("/user/image/{userId}")
    public ResponseEntity<byte[]> readBoard(
            @PathVariable String userId
    ) throws IOException {
        byte[] imageByteArray = userService.getByteImage(userId);
        return new ResponseEntity<>(imageByteArray, HttpStatus.OK);
    }
}
