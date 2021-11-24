package com.evol.service;

import com.evol.domain.dto.LoginParam;
import com.evol.domain.dto.LoginUser;
import com.evol.domain.model.Staff;
import com.evol.web.ApiResponse;

public interface StaffService {
    ApiResponse<LoginUser> login(LoginParam param);
}
