package com.evol.service.impl;

import com.evol.domain.PageBase;
import com.evol.domain.dto.StaffChangePwdDto;
import com.evol.domain.dto.StaffUpsertDto;
import com.evol.domain.model.Staff;
import com.evol.domain.model.StaffExample;
import com.evol.domain.request.StaffQueryRequest;
import com.evol.mapper.StaffMapper;
import com.evol.service.StaffService;
import com.evol.util.MD5Util;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
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


        String password = MD5Util.MD5(dto.getPassword(), "");
        staff.setPassword(password);
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
        staff.setLoginName(dto.getLoginName());
        staff.setRealName(dto.getRealName());
        staff.setMobile(dto.getMobile());
        staff.setStatus(dto.getStatus());
        staff.setAccessFailedCount(dto.getAccessFailedCount());
        staff.setCreateTime(new Date());
        Integer id = staffMapper.updateByPrimaryKey(staff);
        return id;
    }

    @Override
    public Integer changePassword(Integer staffId, StaffChangePwdDto dto){
        Staff staff = staffMapper.selectByPrimaryKey(staffId);
        if(staff == null){
            return null;
        }
        String oldPwd = MD5Util.MD5(dto.getPassword(), "");
        if(!staff.getPassword().equals(oldPwd)){
            throw new RuntimeException("旧密码错误");
        }
        if(StringUtils.isNotBlank(dto.getNewPassword()) || dto.getNewPassword().equals(dto.getConfimPassword())){
            throw new RuntimeException("两次新密码不同");
        }
        String newPwd = MD5Util.MD5(dto.getNewPassword(), "");
        staff.setPassword(newPwd);
        staff.setUpdateTime(new Date());
        Integer id = staffMapper.updateByPrimaryKey(staff);
        return id;
    }

    @Override
    public Integer deleteStaff(Integer staffId) {
        return staffMapper.deleteByPrimaryKey(staffId);
    }

    @Override
    public PageBase<Staff> queryPage(StaffQueryRequest staffQueryRequest) {

        Page<Object> page = PageHelper.startPage(staffQueryRequest.getPage(), staffQueryRequest.getPageSize(),"id asc");
        StaffExample staffExample = new StaffExample();
        staffExample.createCriteria();


        if(!StringUtils.isBlank(staffQueryRequest.getLoginName())){
            staffExample.createCriteria().andLoginNameEqualTo(staffQueryRequest.getLoginName());
        }

        if(!StringUtils.isBlank(staffQueryRequest.getRealName())){
            staffExample.createCriteria().andRealNameEqualTo(staffQueryRequest.getRealName());
        }

        if(!StringUtils.isBlank(staffQueryRequest.getMobile())){
            staffExample.createCriteria().andMobileEqualTo(staffQueryRequest.getMobile());
        }

        List<Staff> staffList = staffMapper.selectByExample(staffExample);

        if(CollectionUtils.isEmpty(staffList)){
            return PageBase.create(page.getTotal(), staffQueryRequest.getPage(), staffQueryRequest.getPageSize(),
                    new ArrayList<>());
        }
        return PageBase.create(page.getTotal(), staffQueryRequest.getPage(), staffQueryRequest.getPageSize(),staffList);
    }

    @Override
    public Staff getStaff(Integer staffId) {
        return staffMapper.selectByPrimaryKey(staffId);
    }
}
