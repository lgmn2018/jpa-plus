package com.yc.jpaplus.example.repository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yc.jpaplus.example.dto.between.AddressJoinUserBetweenDto;
import com.yc.jpaplus.example.dto.between.UserBetweenDto;
import com.yc.jpaplus.example.dto.end.AddressEndDto;
import com.yc.jpaplus.example.dto.end.AddressJoinUserEndDto;
import com.yc.jpaplus.example.dto.end_in.AddressEndingInDto;
import com.yc.jpaplus.example.dto.end_in.AddressJoinUserEndingInDto;
import com.yc.jpaplus.example.dto.equare.UserEqualsDto;
import com.yc.jpaplus.example.dto.equare.AddressEqualsDto;
import com.yc.jpaplus.example.dto.gt.AddressJoinUserGtDto;
import com.yc.jpaplus.example.dto.gt.UserGtDto;
import com.yc.jpaplus.example.dto.gt_or_equare.AddressJoinUserGtOrEquareDto;
import com.yc.jpaplus.example.dto.gt_or_equare.UserGtOrEquareDto;
import com.yc.jpaplus.example.dto.in.AddressJoinUserInDto;
import com.yc.jpaplus.example.dto.in.UserInDto;
import com.yc.jpaplus.example.dto.like.AddressJoinUserLikeDto;
import com.yc.jpaplus.example.dto.equare.AddressJoinUserEquareDto;
import com.yc.jpaplus.example.dto.like.AddressLikeDto;
import com.yc.jpaplus.example.dto.like_in.AddressJoinUserLikeInDto;
import com.yc.jpaplus.example.dto.like_in.AddressLikeInDto;
import com.yc.jpaplus.example.dto.lt.AddressJoinUserLtDto;
import com.yc.jpaplus.example.dto.lt.UserLtDto;
import com.yc.jpaplus.example.dto.lt_or_equare.AddressJoinUserLtOrEquareDto;
import com.yc.jpaplus.example.dto.lt_or_equare.UserLtOrEquareDto;
import com.yc.jpaplus.example.dto.not_ending.AddressJoinUserNotEndingDto;
import com.yc.jpaplus.example.dto.not_ending.AddressNotEndingDto;
import com.yc.jpaplus.example.dto.not_ending_in.AddressJoinUserNotEndingInDto;
import com.yc.jpaplus.example.dto.not_ending_in.AddressNotEndingInDto;
import com.yc.jpaplus.example.dto.not_equare.AddressJoinUserNotEquareDto;
import com.yc.jpaplus.example.dto.not_equare.AddressNotEqualsDto;
import com.yc.jpaplus.example.dto.not_equare.UserNotEqualsDto;
import com.yc.jpaplus.example.dto.not_in.AddressJoinUserNotInDto;
import com.yc.jpaplus.example.dto.not_in.UserNotInDto;
import com.yc.jpaplus.example.dto.not_like.AddressJoinUserNotLikeDto;
import com.yc.jpaplus.example.dto.not_like.AddressNotLikeDto;
import com.yc.jpaplus.example.dto.not_like_in.AddressJoinUserNotLikeInDto;
import com.yc.jpaplus.example.dto.not_like_in.AddressNotLikeInDto;
import com.yc.jpaplus.example.dto.not_starting.AddressJoinUserNotStartingDto;
import com.yc.jpaplus.example.dto.not_starting.AddressNotStartingDto;
import com.yc.jpaplus.example.dto.not_starting_in.AddressJoinUserNotStartingInDto;
import com.yc.jpaplus.example.dto.not_starting_in.AddressNotStartingInDto;
import com.yc.jpaplus.example.dto.starting.AddressJoinUserStartingDto;
import com.yc.jpaplus.example.dto.starting.AddressStartingDto;
import com.yc.jpaplus.example.dto.starting_in.AddressJoinUserStartingInDto;
import com.yc.jpaplus.example.dto.starting_in.AddressStartingInDto;
import com.yc.jpaplus.example.entity.Address;
import com.yc.jpaplus.example.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class QueryByDtoTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Test
    public void equals() throws JsonProcessingException {
        UserEqualsDto dto = new UserEqualsDto();
        dto.setName("jimmy");
        List<User> users = userRepository.findAll(dto);
        Assertions.assertNotNull(users);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(users);
        System.out.println(json);
    }

    @Test
    public void equals2() throws JsonProcessingException {
        AddressEqualsDto dto = new AddressEqualsDto();
        dto.setAddress("佛山市祖庙路1号");
        List<Address> addresses = addressRepository.findAll(dto);
        Assertions.assertNotNull(addresses);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addresses);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void equalsWithJoin() throws JsonProcessingException {
        AddressJoinUserEquareDto dto = new AddressJoinUserEquareDto();
        dto.setName("jimmy");
        List<Address> addresses = addressRepository.findAll(dto);
        Assertions.assertNotNull(addresses);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addresses);
        System.out.println(json);
    }
    @Test
    public void notEquals() throws JsonProcessingException {
        UserNotEqualsDto dto = new UserNotEqualsDto();
        dto.setName("jimmy");
        List<User> users = userRepository.findAll(dto);
        Assertions.assertNotNull(users);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(users);
        System.out.println(json);
    }

    @Test
    public void notEquals2() throws JsonProcessingException {
        AddressNotEqualsDto dto = new AddressNotEqualsDto();
        dto.setAddress("佛山市祖庙路1号");
        List<Address> addresses = addressRepository.findAll(dto);
        Assertions.assertNotNull(addresses);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addresses);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void notEqualsWithJoin() throws JsonProcessingException {
        AddressJoinUserNotEquareDto dto = new AddressJoinUserNotEquareDto();
        dto.setName("jimmy");
        List<Address> addresses = addressRepository.findAll(dto);
        Assertions.assertNotNull(addresses);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addresses);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void like() throws JsonProcessingException {
        AddressLikeDto dto = new AddressLikeDto();
        dto.setAddress("祖庙");
        List<Address> addresses = addressRepository.findAll(dto);
        Assertions.assertNotNull(addresses);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addresses);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void likeWithJoin() throws JsonProcessingException {
        AddressJoinUserLikeDto dto = new AddressJoinUserLikeDto();
        dto.setName("j");
        List<Address> addresses = addressRepository.findAll(dto);
        Assertions.assertNotNull(addresses);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addresses);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void notLike() throws JsonProcessingException {
        AddressNotLikeDto dto = new AddressNotLikeDto();
        dto.setAddress("祖庙");
        List<Address> addresses = addressRepository.findAll(dto);
        Assertions.assertNotNull(addresses);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addresses);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void notLikeWithJoin() throws JsonProcessingException {
        AddressJoinUserNotLikeDto dto = new AddressJoinUserNotLikeDto();
        dto.setName("j");
        List<Address> addresses = addressRepository.findAll(dto);
        Assertions.assertNotNull(addresses);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addresses);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void starting() throws JsonProcessingException {
        AddressStartingDto dto = new AddressStartingDto();
        dto.setAddress("佛山");
        List<Address> addresses = addressRepository.findAll(dto);
        Assertions.assertNotNull(addresses);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addresses);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void startingWithJoin() throws JsonProcessingException {
        AddressJoinUserStartingDto dto = new AddressJoinUserStartingDto();
        dto.setName("ji");
        List<Address> addresses = addressRepository.findAll(dto);
        Assertions.assertNotNull(addresses);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addresses);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void notStarting() throws JsonProcessingException {
        AddressNotStartingDto dto = new AddressNotStartingDto();
        dto.setAddress("佛山");
        List<Address> addresses = addressRepository.findAll(dto);
        Assertions.assertNotNull(addresses);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addresses);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void notStartingWithJoin() throws JsonProcessingException {
        AddressJoinUserNotStartingDto dto = new AddressJoinUserNotStartingDto();
        dto.setName("ji");
        List<Address> addresses = addressRepository.findAll(dto);
        Assertions.assertNotNull(addresses);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addresses);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void end() throws JsonProcessingException {
        AddressEndDto dto = new AddressEndDto();
        dto.setAddress("1号");
        List<Address> addresses = addressRepository.findAll(dto);
        Assertions.assertNotNull(addresses);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addresses);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void endWithJoin() throws JsonProcessingException {
        AddressJoinUserEndDto dto = new AddressJoinUserEndDto();
        dto.setName("my");
        List<Address> addresses = addressRepository.findAll(dto);
        Assertions.assertNotNull(addresses);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addresses);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void notEnd() throws JsonProcessingException {
        AddressNotEndingDto dto = new AddressNotEndingDto();
        dto.setAddress("1号");
        List<Address> addresses = addressRepository.findAll(dto);
        Assertions.assertNotNull(addresses);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addresses);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void notEndWithJoin() throws JsonProcessingException {
        AddressJoinUserNotEndingDto dto = new AddressJoinUserNotEndingDto();
        dto.setName("my");
        List<Address> addresses = addressRepository.findAll(dto);
        Assertions.assertNotNull(addresses);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addresses);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void in() throws JsonProcessingException {
        UserInDto dto = new UserInDto();
        dto.setName(new ArrayList<String>(){{add("jimmy"); add("Mary");}});
        List<User> users = userRepository.findAll(dto);
        Assertions.assertNotNull(users);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(users);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void inWithJoin() throws JsonProcessingException {
        AddressJoinUserInDto dto = new AddressJoinUserInDto();
        dto.setName(new ArrayList<String>(){{add("jimmy"); add("Mary");}});
        List<Address> addresses = addressRepository.findAll(dto);
        Assertions.assertNotNull(addresses);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addresses);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void notIn() throws JsonProcessingException {
        UserNotInDto dto = new UserNotInDto();
        dto.setName(new ArrayList<String>(){{add("jimmy"); add("Mary");}});
        List<User> users = userRepository.findAll(dto);
        Assertions.assertNotNull(users);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(users);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void notInWithJoin() throws JsonProcessingException {
        AddressJoinUserNotInDto dto = new AddressJoinUserNotInDto();
        dto.setName(new ArrayList<String>(){{add("jimmy"); add("Mary");}});
        List<Address> addresses = addressRepository.findAll(dto);
        Assertions.assertNotNull(addresses);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addresses);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void betweenAnd() throws JsonProcessingException {
        UserBetweenDto dto = new UserBetweenDto();
        dto.setAge(new ArrayList<Integer>(){{add(11); add(18);}});
        List<User> users = userRepository.findAll(dto);
        Assertions.assertNotNull(users);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(users);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void betweenAndWithJoin() throws JsonProcessingException {
        AddressJoinUserBetweenDto dto = new AddressJoinUserBetweenDto();
        dto.setAge(new ArrayList<Integer>(){{add(12); add(18);}});
        List<Address> addresses = addressRepository.findAll(dto);
        Assertions.assertNotNull(addresses);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addresses);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void gt() throws JsonProcessingException {
        UserGtDto dto = new UserGtDto();
        dto.setAge(18);
        List<User> users = userRepository.findAll(dto);
        Assertions.assertNotNull(users);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(users);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void gtWithJoin() throws JsonProcessingException {
        AddressJoinUserGtDto dto = new AddressJoinUserGtDto();
        dto.setAge(18);
        List<Address> addresses = addressRepository.findAll(dto);
        Assertions.assertNotNull(addresses);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addresses);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void gtOrEquare() throws JsonProcessingException {
        UserGtOrEquareDto dto = new UserGtOrEquareDto();
        dto.setAge(18);
        List<User> users = userRepository.findAll(dto);
        Assertions.assertNotNull(users);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(users);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void gtOrEquareWithJoin() throws JsonProcessingException {
        AddressJoinUserGtOrEquareDto dto = new AddressJoinUserGtOrEquareDto();
        dto.setAge(18);
        List<Address> addresses = addressRepository.findAll(dto);
        Assertions.assertNotNull(addresses);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addresses);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void lt() throws JsonProcessingException {
        UserLtDto dto = new UserLtDto();
        dto.setAge(18);
        List<User> users = userRepository.findAll(dto);
        Assertions.assertNotNull(users);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(users);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void ltWithJoin() throws JsonProcessingException {
        AddressJoinUserLtDto dto = new AddressJoinUserLtDto();
        dto.setAge(18);
        List<Address> addresses = addressRepository.findAll(dto);
        Assertions.assertNotNull(addresses);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addresses);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void ltOrEquare() throws JsonProcessingException {
        UserLtOrEquareDto dto = new UserLtOrEquareDto();
        dto.setAge(18);
        List<User> users = userRepository.findAll(dto);
        Assertions.assertNotNull(users);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(users);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void ltOrEquareWithJoin() throws JsonProcessingException {
        AddressJoinUserLtOrEquareDto dto = new AddressJoinUserLtOrEquareDto();
        dto.setAge(18);
        List<Address> addresses = addressRepository.findAll(dto);
        Assertions.assertNotNull(addresses);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addresses);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void likeIn() throws JsonProcessingException {
        AddressLikeInDto dto = new AddressLikeInDto();
        dto.setAddress(new ArrayList<String>(){{add("3号"); add("1号"); }});
        List<Address> addresses = addressRepository.findAll(dto);
        Assertions.assertNotNull(addresses);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addresses);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void likeInWithJoin() throws JsonProcessingException {
        AddressJoinUserLikeInDto dto = new AddressJoinUserLikeInDto();
        dto.setName(new ArrayList<String>(){{add("my"); add("ry"); }});
        List<Address> addresses = addressRepository.findAll(dto);
        Assertions.assertNotNull(addresses);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addresses);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void notLikeIn() throws JsonProcessingException {
        AddressNotLikeInDto dto = new AddressNotLikeInDto();
        dto.setAddress(new ArrayList<String>(){{add("3号"); add("1号"); }});
        List<Address> addresses = addressRepository.findAll(dto);
        Assertions.assertNotNull(addresses);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addresses);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void notLikeInWithJoin() throws JsonProcessingException {
        AddressJoinUserNotLikeInDto dto = new AddressJoinUserNotLikeInDto();
        dto.setName(new ArrayList<String>(){{add("my"); add("ry"); }});
        List<Address> addresses = addressRepository.findAll(dto);
        Assertions.assertNotNull(addresses);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addresses);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void startingIn() throws JsonProcessingException {
        AddressStartingInDto dto = new AddressStartingInDto();
        dto.setAddress(new ArrayList<String>(){{add("佛山"); add("广州"); }});
        List<Address> addresses = addressRepository.findAll(dto);
        Assertions.assertNotNull(addresses);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addresses);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void startingInWithJoin() throws JsonProcessingException {
        AddressJoinUserStartingInDto dto = new AddressJoinUserStartingInDto();
        dto.setName(new ArrayList<String>(){{add("j"); add("t"); }});
        List<Address> addresses = addressRepository.findAll(dto);
        Assertions.assertNotNull(addresses);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addresses);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void notStartingIn() throws JsonProcessingException {
        AddressNotStartingInDto dto = new AddressNotStartingInDto();
        dto.setAddress(new ArrayList<String>(){{add("佛山"); add("广州"); }});
        List<Address> addresses = addressRepository.findAll(dto);
        Assertions.assertNotNull(addresses);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addresses);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void notStartingInWithJoin() throws JsonProcessingException {
        AddressJoinUserNotStartingInDto dto = new AddressJoinUserNotStartingInDto();
        dto.setName(new ArrayList<String>(){{add("j"); add("t"); }});
        List<Address> addresses = addressRepository.findAll(dto);
        Assertions.assertNotNull(addresses);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addresses);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void endingIn() throws JsonProcessingException {
        AddressEndingInDto dto = new AddressEndingInDto();
        dto.setAddress(new ArrayList<String>(){{add("2号"); add("4号"); }});
        List<Address> addresses = addressRepository.findAll(dto);
        Assertions.assertNotNull(addresses);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addresses);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void endingInWithJoin() throws JsonProcessingException {
        AddressJoinUserEndingInDto dto = new AddressJoinUserEndingInDto();
        dto.setName(new ArrayList<String>(){{add("y"); add("n"); }});
        List<Address> addresses = addressRepository.findAll(dto);
        Assertions.assertNotNull(addresses);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addresses);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void notEndingIn() throws JsonProcessingException {
        AddressNotEndingInDto dto = new AddressNotEndingInDto();
        dto.setAddress(new ArrayList<String>(){{add("2号"); add("4号"); }});
        List<Address> addresses = addressRepository.findAll(dto);
        Assertions.assertNotNull(addresses);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addresses);
        System.out.println(json);
    }

    @Test
    @Transactional
    public void notEndingInWithJoin() throws JsonProcessingException {
        AddressJoinUserNotEndingInDto dto = new AddressJoinUserNotEndingInDto();
        dto.setName(new ArrayList<String>(){{add("y"); add("n"); }});
        List<Address> addresses = addressRepository.findAll(dto);
        Assertions.assertNotNull(addresses);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addresses);
        System.out.println(json);
    }
}
