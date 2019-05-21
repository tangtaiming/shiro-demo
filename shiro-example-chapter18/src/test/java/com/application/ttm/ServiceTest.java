package com.application.ttm;

import com.application.ttm.entity.Authorize;
import com.application.ttm.entity.User;
import com.application.ttm.service.AuthorizeService;
import com.application.ttm.service.DoubanMovieService;
import com.application.ttm.service.UserService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @Autowired
    private AuthorizeService authorizeService;

    @Test
    public void testUpdate() {
//        User usr = userService.findOne(1L);
//        usr.setPassword("123456");
        userService.changePassword(1L, "123456");
    }

    @Autowired
    private DoubanMovieService doubanMovieService;

    @Ignore
    @Test
    public void testCreateUser() {
        User user = new User();
        user.setUsername("4");
        user.setPassword("123456");
        user.setOrganizationId(-1L);
        userService.createUser(user);
    }

    @Test
    public void testAuthoirze() {
        String defaultCreateDate = "YYYY-MM-dd HH:mm:ss";
        Authorize authorize = new Authorize();
        authorize.setUserId(1L);
        authorize.setCreateDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern(defaultCreateDate)));
        authorizeService.authorize(authorize);
    }

    @Ignore
    @Test
    public void testCount() {
        System.out.println(doubanMovieService.count());
    }

}
