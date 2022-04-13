package com.evol.service;

import com.evol.domain.PageBase;
import com.evol.domain.dto.StaffChangePwdDto;
import com.evol.domain.dto.StaffUpsertDto;
import com.evol.domain.model.Staff;
import com.evol.domain.request.StaffQueryRequest;

public interface StaffService {

    Integer addStaff(StaffUpsertDto dto);

    Integer modifyStaff(Integer staffId, StaffUpsertDto dto);

    Integer changePassword(Integer staffId, StaffChangePwdDto dto);

    Integer deleteStaff(Integer staffId);

    PageBase<Staff> queryPage(StaffQueryRequest staffQueryRequest);

    Staff getStaff(Integer staffId);

}
