package com.yc.jpaplus.example.repository;

import com.yc.jpaplus.example.config.JpaPlusAuditorAware;
import com.yc.jpaplus.example.entity.Address;
import com.yc.jpaplus.example.entity.User;
import com.yc.jpaplus.example.enums.Gender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class AddressRepositoryTest {
    @Autowired
    private AddressRepository addressRepository;

    @MockBean
    JpaPlusAuditorAware jpaPlusAuditorAware;

    @Test
    @Rollback(value = false)
    @Transactional
    public void testAuditing() {
        //由于测试用例模拟web context环境不是我们的重点，我们这里利用@MockBean，mock掉我们的方法，期待返回13这个用户ID
        Mockito.when(jpaPlusAuditorAware.getCurrentAuditor()).thenReturn(Optional.of(13L));
        //我们没有显式的指定更新时间、创建时间、更新人、创建人
        User user = User.builder()
                .name("Tim")
                .gender(Gender.MAIL)
                .age(18)
                .build();

        Address address = Address.builder()
                .address("佛山市祖庙路5号")
                .user(user)
                .build();

        addressRepository.saveAndFlush(address);
        //验证是否有创建时间、更新时间，UserID是否正确；
        List<Address> addresses = addressRepository.findAll();
        Assertions.assertEquals(13,addresses.get(addresses.size()-1).getCreateBy());
        Assertions.assertNotNull(addresses.get(addresses.size()-1).getUpdateTime());
        System.out.println(addresses.get(addresses.size()-1).getUser());
    }

    @Test
    @Transactional
    public void testFind() {
//        User user = User.builder().name("jimmy").build();
//        Address address = Address.builder().user(user).build();
//
////        addressRepository.saveAndFlush(address);
//        //验证是否有创建时间、更新时间，UserID是否正确；
//        List<Address> addresses = addressRepository.findAll(address);
//        Assertions.assertEquals(13,addresses.get(0).getCreateBy());
//        Assertions.assertNotNull(addresses.get(0).getUpdateTime());
//        System.out.println(addresses.get(0).getUser());
    }
}