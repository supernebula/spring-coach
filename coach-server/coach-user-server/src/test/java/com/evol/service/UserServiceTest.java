package com.evol.service;

import com.evol.domain.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void saveWechatTokenTest() {


        //                {
//                    "access_token":"ACCESS_TOKEN",
//                        "expires_in":7200,
//                        "refresh_token":"REFRESH_TOKEN",
//                        "openid":"OPENID",
//                        "scope":"SCOPE"
//                }

        Date expiresDate = new Date(System.currentTimeMillis() + (7200 * 1000));
        userService.saveWechatToken("openId2", "ACCESS_TOKEN21", expiresDate, "REFRESH_TOKEN21", "SCOPE21");
    }

}
