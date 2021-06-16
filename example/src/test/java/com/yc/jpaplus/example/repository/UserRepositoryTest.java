package com.yc.jpaplus.example.repository;

import com.yc.jpaplus.example.dto.equare.UserEqualsDto;
import com.yc.jpaplus.example.entity.User;
import com.yc.jpaplus.example.enums.Gender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    @Rollback(value = false)
    @Transactional
    public void testAuditing() {
        User user1 = User.builder()
                .name("安琪拉")
                .gender(Gender.FMAIL)
                .age(18)
                .build();
        User user2 = User.builder()
                .name("兰陵王")
                .gender(Gender.MAIL)
                .age(28)
                .build();
        User user3 = User.builder()
                .name("亚瑟")
                .gender(Gender.MAIL)
                .age(30)
                .build();
        List<User> users = new ArrayList<User>(){{add(user1);add(user2);add(user3);}};
        userRepository.saveAll(users);

        UserEqualsDto dto = new UserEqualsDto();
        dto.setName("亚瑟");
        List<User> result = userRepository.findAll(dto);
        System.out.println(result);
        Assertions.assertEquals(result.size(),1);
    }
}