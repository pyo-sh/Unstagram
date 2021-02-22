package co.kr.datapia.interfaces;

import co.kr.datapia.application.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class UserController {
    @Autowired
    private UserService userService;


}
