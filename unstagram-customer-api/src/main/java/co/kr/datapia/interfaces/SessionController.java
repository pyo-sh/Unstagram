package co.kr.datapia.interfaces;

import co.kr.datapia.application.UserLoginService;
import co.kr.datapia.domain.User;
import co.kr.datapia.model.request.SessionRequestDto;
import co.kr.datapia.model.response.SessionResponseDto;
import co.kr.datapia.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@CrossOrigin
@RestController
public class SessionController {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserLoginService userLoginService;

    @PostMapping("/login")
    public ResponseEntity<SessionResponseDto> create(
            @RequestBody SessionRequestDto resource
    ) throws URISyntaxException {
        String userId = resource.getUserId();
        String password = resource.getPassword();

        User user = userLoginService.authenticate(userId, password);

        String accessToken = jwtUtil.createToken(
                user.getUserId()
        );

        SessionResponseDto sessionResponseDto = SessionResponseDto.builder()
                .accessToken(accessToken)
                .build();

        String url = "/session";
        return ResponseEntity.created(new URI(url)).body(sessionResponseDto);
    }
}
