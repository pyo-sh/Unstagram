package co.kr.datapia.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTest {
    @Test
    public void creation(){
        Integer idx = 1;
        String userId = "pos_h_un";
        String password = "hi";
        String name = "표석훈";
        String introduce = "안녕하세요";
        String website = "https://github.com/pyo-sh";
        String phone = "010-6766-9300";
        String userImagePath = "images/1";
        String signedDate = new Date().toString();
        List<Board> boards = new ArrayList<>();
        boards.add(Board.builder().idx(1234).build());

        User user = User.builder()
                .idx(idx)
                .userId(userId)
                .password(password)
                .name(name)
                .introduce(introduce)
                .website(website)
                .phone(phone)
                .userImagePath(userImagePath)
                .signedDate(signedDate)
                .boards(boards)
                .level(1)
                .build();

        assertEquals(user.getIdx(), idx);
        assertEquals(user.getUserId(), userId);
        assertEquals(user.getPassword(), password);
        assertEquals(user.getName(), name);
        assertEquals(user.getIntroduce(), introduce);
        assertEquals(user.getWebsite(), website);
        assertEquals(user.getPhone(), phone);
        assertEquals(user.getUserImagePath(), userImagePath);
        assertEquals(user.getSignedDate(), signedDate);
        assertEquals(user.getBoards().get(0).getIdx(), 1234);
        assertTrue(user.isEnable());
    }
}