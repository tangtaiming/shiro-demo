package com.application.ttm.shiro.test;

import com.application.ttm.shiro.entity.User;
import com.application.ttm.shiro.service.UserService;
import com.application.ttm.shiro.service.impl.PasswordHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml", "classpath:spring-mvc.xml"})
public class UserTest {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordHelper passwordHelper;

    @Test
    public void findUserTest() {
        List<User> userList = userService.findAll();
        for (User user : userList) {
            System.out.println("id: " + user.getId() + " name:" + user.getUsername());
        }
    }

    @Test
    public void saveUserTest() {
        User user = new User();
        user.setUsername("tangtaiming");
        user.setPassword("123456");
        userService.createUser(user);
    }

}
