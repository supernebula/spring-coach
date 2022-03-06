package com.evol.service.impl;

import com.evol.domain.dto.StaffDetails;
import com.evol.domain.model.Staff;
import com.evol.domain.model.StaffExample;
import com.evol.domain.model.User;
import com.evol.domain.model.UserExample;
import com.evol.mapper.StaffMapper;
import com.evol.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(s);
        List<User> list = userMapper.selectByExample(userExample);
        if(list == null || list.get(0) == null){
            return null;
        }

        User user = list.get(0);
        UserDetails userDetails = new StaffDetails(user.getUsername(), user.getPassword());
        return userDetails;
    }
}
