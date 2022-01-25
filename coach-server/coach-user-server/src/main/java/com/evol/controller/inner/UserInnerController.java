package com.evol.controller.inner;

import com.evol.domain.request.UpdateUserBalanceParam;
import com.evol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author admin
 */
@RequestMapping("inner/user")
public class UserInnerController {

    @Autowired
    private UserService userService;

    @PostMapping("updateUserBalanceSync")
    public void updateUserBalanceSync(UpdateUserBalanceParam updateBalanceParam){
        userService.updateUserBalance(updateBalanceParam);
    }

}
