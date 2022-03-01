package com.evol.service.impl;

import com.evol.domain.dto.StaffDetails;
import com.evol.domain.model.Staff;
import com.evol.domain.model.StaffExample;
import com.evol.mapper.StaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private StaffMapper staffMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        StaffExample staffExample = new StaffExample();
        staffExample.createCriteria().andLoginNameEqualTo(s);
        List<Staff> list = staffMapper.selectByExample(staffExample);
        if(list == null || list.get(0) == null){
            return null;
        }

        Staff staff = list.get(0);
        StaffDetails staffDetails = new StaffDetails(staff.getLoginName(), staff.getPassword());
        return staffDetails;
    }
}
