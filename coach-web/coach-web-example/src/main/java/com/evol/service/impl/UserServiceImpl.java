package com.evol.service.impl;

import com.evol.cache.Cache;
import com.evol.constant.CacheName;
import com.evol.domain.dto.AccessToken;
import com.evol.domain.dto.StaffDetails;
import com.evol.domain.model.*;
import com.evol.mapper.UserMapper;
import com.evol.provider.JwtProvider;
import com.evol.service.RoleService;
import com.evol.service.UserRoleService;
import com.evol.service.UserService;
import com.evol.web.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(s);
        List<User> list = userMapper.selectByExample(userExample);
        if(list == null || list.get(0) == null){
            return null;
        }
        User user = list.get(0);
        List<Role> roleList = roleService.getRoleListByUser(user.getId());


        UserDetails userDetails = new StaffDetails(user.getUsername(), user.getPassword(), roleList);
        return userDetails;
    }

    @Override
    public User addUser(String username, String password){
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return null;
        }

        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pwd1 = passwordEncoder.encode(StringUtils.trim(password));
        log.info("pwd1:" + pwd1);

        User user = new User();
        user.setUsername(StringUtils.trim(username));
        user.setNickname(StringUtils.trim(username));
        user.setPassword(pwd1);
        user.setEmail("");
        user.setPhone("");
        user.setState(0);
        user.setSex(0);
        user.setCreateTime(new Date());
        userMapper.insert(user);
        return user;
    }

    public boolean addUser(User user, Integer[] roleIds) {
        int count = this.userMapper.insert(user);
        log.warn("更新用户角色为空");
        if (null != roleIds) {
            Arrays.stream(roleIds).forEach(roleId -> {
                UserRole ur = new UserRole();
                ur.setUserId(user.getId());
                ur.setRoleId(roleId);
                this.userRoleService.save(ur);
            });
        }
        return count > 0;
    }


    @Autowired
    private AuthenticationManager authenticationManager;

    private JwtProvider jwtProvider;


    @Autowired
    private Cache caffeineCache;

    @Override
    public ApiResponse login(String loginAccount, String password) {

        log.debug("进入login方法");
        // 1 创建UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken usernameAuthentication = new UsernamePasswordAuthenticationToken(loginAccount, password);
        // 2 认证
        Authentication authentication = this.authenticationManager.authenticate(usernameAuthentication);
        // 3 保存认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 4 生成自定义token
        AccessToken accessToken = jwtProvider.createToken((UserDetails) authentication.getPrincipal());

        StaffDetails userDetail = (StaffDetails) authentication.getPrincipal();
        // 放入缓存
        caffeineCache.put(CacheName.USER, userDetail.getUsername(), userDetail);
        return ApiResponse.success(accessToken);
    }

    @Override
    public ApiResponse logout() {
        caffeineCache.remove(CacheName.USER, AuthProvider.getLoginAccount());
        SecurityContextHolder.clearContext();
        return ApiResponse.success(null);
    }

    @Override
    public ApiResult refreshToken(String token) {
        AccessToken accessToken = jwtProvider.refreshToken(token);
        UserDetail userDetail = caffeineCache.get(CacheName.USER, accessToken.getLoginAccount(), UserDetail.class);
        caffeineCache.put(CacheName.USER, accessToken.getLoginAccount(), userDetail);
        return ApiResult.ok(accessToken);
    }
}
