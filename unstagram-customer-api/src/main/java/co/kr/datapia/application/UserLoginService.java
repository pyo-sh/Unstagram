package co.kr.datapia.application;

import co.kr.datapia.domain.User;
import co.kr.datapia.domain.UserRepository;
import co.kr.datapia.exceptions.UserIdNotExistedException;
import co.kr.datapia.security.PasswordWrongException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserLoginService {
    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    @Autowired
    public UserLoginService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User authenticate(Integer user_idx, String userId, String password) {
        User user = userRepository.findByIdxAndUserId(user_idx, userId)
                .orElseThrow(() -> new UserIdNotExistedException(userId));

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new PasswordWrongException();
        }

        return user;
    }
}
