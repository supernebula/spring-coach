package com.evol.service.impl;

import com.evol.domain.dto.LoginParam;
import com.evol.domain.model.Staff;
import com.evol.domain.model.StaffExample;
import com.evol.mapper.StaffMapper;
import com.evol.service.StaffService;
import com.evol.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffMapper staffMapper;

    @Override
    public Staff login(LoginParam param) {

        StaffExample staffExample = new StaffExample();
        staffExample.createCriteria().andLoginNameEqualTo(param.getLoginName());
        List<Staff> items = staffMapper.selectByExample(staffExample);
        if(CollectionUtils.isEmpty(items)){
            throw  new RuntimeException("用户不存在");
        }
        Staff staff = items.get(0);
        String password = MD5Util.MD5(param.getPassword(), "");
        if(staff == null){
            throw  new RuntimeException("用户不存在");
        }

        if(!staff.getPassword().equals(password)){
            throw  new RuntimeException("密码错误");
        }
        return staff;
    }
}
