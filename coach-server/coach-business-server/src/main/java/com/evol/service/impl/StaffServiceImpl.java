package com.evol.service.impl;

import com.evol.domain.PageBase;
import com.evol.domain.dto.StaffUpsertDto;
import com.evol.domain.model.Staff;
import com.evol.domain.model.StaffExample;
import com.evol.domain.request.StaffQueryRequest;
import com.evol.mapper.StaffMapper;
import com.evol.service.StaffService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffMapper staffMapper;

    @Override
    public Integer addStaff(StaffUpsertDto dto) {
        Staff staff = new Staff();
        staff.setCreateStaffId(dto.getCreateStaffId());
        staff.setLoginName(dto.getLoginName());

//        staff.setSalt(UUID.randomUUID().toString());
//        String password = MD5Util.MD5(userAddDto.getPassword(), user.getSalt());
        staff.setPassword(dto.getPassword());
        staff.setRealName(dto.getRealName());
        staff.setMobile(dto.getMobile());
        staff.setStatus(dto.getStatus());
        //staff.setPasswordResetCode("");
        staff.setAccessFailedCount(dto.getAccessFailedCount());
        staff.setCreateTime(new Date());
        Integer id = staffMapper.insert(staff);
        return id;
    }

    @Override
    public Integer modifyStaff(Integer staffId, StaffUpsertDto dto) {
        Staff staff = staffMapper.selectByPrimaryKey(staffId);
        if(staff == null){
            return null;
        }

        staff.setCreateStaffId(dto.getCreateStaffId());
        staff.setLoginName(dto.getLoginName());

//        staff.setSalt(UUID.randomUUID().toString());
//        String password = MD5Util.MD5(userAddDto.getPassword(), user.getSalt());
        staff.setPassword(dto.getPassword());
        staff.setRealName(dto.getRealName());
        staff.setMobile(dto.getMobile());
        staff.setStatus(dto.getStatus());
        //staff.setPasswordResetCode("");
        staff.setAccessFailedCount(dto.getAccessFailedCount());
        staff.setCreateTime(new Date());
        Integer id = staffMapper.updateByPrimaryKey(staff);
        return id;
    }

    @Override
    public Integer deleteStaff(Integer staffId) {
        return staffMapper.deleteByPrimaryKey(staffId);
    }

    @Override
    public PageBase<Staff> queryPage(StaffQueryRequest staffQueryRequest) {
        Page page =  PageHelper.startPage(staffQueryRequest.getPageNo(), staffQueryRequest.getPageSize());
        StaffExample staffExample = new StaffExample();
        staffExample.createCriteria();
        List<Staff> staffList = staffMapper.selectByExample(staffExample);
        if(CollectionUtils.isEmpty(staffList)){
            return PageBase.create(page.getTotal(), new ArrayList<>());
        }
        return PageBase.create(page.getTotal(),staffList);
    }

    @Override
    public Staff getStaff(Integer staffId) {
        return staffMapper.selectByPrimaryKey(staffId);
    }
}
