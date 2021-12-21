package com.evol.service;

import com.evol.domain.dto.LoginParam;
import com.evol.domain.LoginUser;
import com.evol.domain.dto.LoginUserDTO;
import com.evol.domain.model.Staff;
import com.evol.web.ApiResponse;

public interface StaffService {
    ApiResponse<LoginUserDTO> login(LoginParam param);
}
