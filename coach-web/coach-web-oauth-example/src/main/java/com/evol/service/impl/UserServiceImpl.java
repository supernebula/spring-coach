package com.evol.service.impl;

import com.evol.domain.dto.AccountDetails;
import com.evol.domain.model.User;
import com.evol.domain.model.UserExample;
import com.evol.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService {
    //private List<User> userList;
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

//    @PostConstruct
//    public void initData() {
//        String password = passwordEncoder.encode("123456");
//        userList = new ArrayList<>();
//        User user1 = new User();
//        user1.setUsername("yuan11");
//        user1.setPassword(password);
//        user1.setNickname("yuan11");
//        userList.add(user1);
//
//        User user2 = new User();
//        user2.setUsername("maochao");
//        user2.setPassword(password);
//        user2.setNickname("maochao");
//        userList.add(user2);
//
//        User user3 = new User();
//        user3.setUsername("zhaoyun");
//        user3.setPassword(password);
//        user3.setNickname("zhaoyun");
//        userList.add(user3);
//    }

//    @Override
//    public AccountDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        List<User> findUserList = userList.stream().filter(user -> user.getUsername().equals(username)).collect(Collectors.toList());
//        if (!CollectionUtils.isEmpty(findUserList)) {
//            User user = findUserList.get(0);
//            return new AccountDetails(user.getUsername(), user.getPassword(), user.getNickname(), null, null);
//        } else {
//            throw new UsernameNotFoundException("用户名或密码错误");
//        }
//    }

    @Override
    public AccountDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> userList = userMapper.selectByExample(userExample);
        if(userList == null || userList.size() == 0){
            throw new UsernameNotFoundException("用户名或密码错误.");
        }
        User user = userList.get(0);
        return new AccountDetails(user.getUsername(), user.getPassword(), user.getNickname(), null, null);
    }
}