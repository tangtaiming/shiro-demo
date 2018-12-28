package com.application.ttm;

import com.application.ttm.entity.User;
import com.application.ttm.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2018-12-28</p>
 * <p>@Version 1.0</p>
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml", "classpath:spring-mvc.xml"})
public class ServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setUsername("3");
        user.setPassword("123456");
        user.setOrganizationId(-1L);
        userService.createUser(user);
    }

}
