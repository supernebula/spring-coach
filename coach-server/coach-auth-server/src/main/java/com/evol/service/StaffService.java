package com.evol.service;

import com.evol.domain.dto.LoginParam;
import com.evol.domain.model.Staff;

public interface StaffService {
    Staff login(LoginParam param);
}
