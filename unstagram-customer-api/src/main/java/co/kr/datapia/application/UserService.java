package co.kr.datapia.application;

import co.kr.datapia.domain.User;
import co.kr.datapia.domain.UserRepository;
import co.kr.datapia.exceptions.UserIdNotExistedException;
import co.kr.datapia.utils.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    FileHandler fileHandler;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.fileHandler = new FileHandler();
    }

    public User registerUser(String userId, String name, String phone, String password) {
        Optional<User> existed = userRepository.findByUserId(userId);
        if(existed.isPresent()){
            throw new UserIdNotExistedException(userId);
        }

        String encodedPassword = passwordEncoder.encode(password);

        User user = User.builder()
                .userId(userId)
                .name(name)
                .password(encodedPassword)
                .phone(phone)
                .level(1)
                .build();

        return userRepository.save(user);
    }

    public User getUser(String userId){
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserIdNotExistedException(userId));
    }

    public User setImage(String userId, MultipartFile file) throws Exception {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserIdNotExistedException(userId));

        if (user.getUserImagePath() != null){
            fileHandler.deleteImage(user.getUserImagePath());
        }
        user.setUserImagePath(fileHandler.parseFilePath(file));

        return user;
    }

    public byte[] getByteImage(String userId) throws IOException {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserIdNotExistedException(userId));

        if (user.getUserImagePath() != null){
            return null;
        }

        return fileHandler.parsePathByteFile(user.getUserImagePath());
    }
}
