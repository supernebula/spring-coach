package com.evol.controller;

import com.evol.domain.dto.CurrentLoginAccount;
import com.evol.util.ThreadLocalUtil;

public class BaseController {

    protected CurrentLoginAccount getCurrentAccount(){
        CurrentLoginAccount account = ThreadLocalUtil.getCurrentAccount();
        return account;
    }
}
