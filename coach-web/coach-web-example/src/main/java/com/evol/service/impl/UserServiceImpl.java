package com.evol.service.impl;

import com.evol.domain.dto.AccountDetails;
import com.evol.domain.dto.UserChangePwdParam;
import com.evol.domain.model.*;
import com.evol.mapper.UserMapper;
import com.evol.service.PermissionService;
import com.evol.service.RoleService;
import com.evol.service.UserRoleService;
import com.evol.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.StringUtils;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    private PermissionService permissionService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(s);
        List<User> list = userMapper.selectByExample(userExample);
        if(list == null || list.get(0) == null){
            //return null;
            throw new RuntimeException("用户名不存在");
        }
        User user = list.get(0);

        List<Role> roleList = roleService.getRoleListByUser(user.getId());
        List<Integer> roleIds = roleList.stream().map(r -> r.getId()).collect(Collectors.toList());
        List<Permission> permissionList = permissionService.getUnionPermission(roleIds);
        UserDetails userDetails = new AccountDetails(user.getUsername(), user.getPassword(), user.getNickname(),
                user.getExpiredTime(), user.getLocked(), user.getPasswordExpiredTime(), user.getEnabled(),
                roleList,
                permissionList);
        return userDetails;
    }

    @Override
    public boolean addUser(String username, String password){
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return false;
        }

        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptPwd = passwordEncoder.encode(StringUtils.trim(password));
        log.info("encryptPwd:" + encryptPwd);
        User user = new User();
        user.setUsername(StringUtils.trim(username));
        user.setNickname(StringUtils.trim(username));
        user.setPassword(encryptPwd);
        user.setEmail("");
        user.setPhone("");
        user.setState(0);
        user.setSex(0);
        user.setCreateTime(new Date());
        user.setLocked(0);
        user.setEnabled(0);
        int count = userMapper.insert(user);
        return count > 0;
    }

    @Override
    public boolean addUser(User user, Integer[] roleIds) {
        String encryptPwd = passwordEncoder.encode(StringUtils.trim(user.getPassword()));
        user.setUsername(StringUtils.trim(user.getUsername()));
        user.setNickname(StringUtils.trim(user.getNickname()));
        user.setCreateTime(new Date());
        int count = this.userMapper.insert(user);
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


    @Override
    public boolean updateUser(User userParam, Integer[] roleIds){
        User user = this.userMapper.selectByPrimaryKey(userParam.getId());
        user.setNickname(StringUtils.trim(userParam.getNickname()));
        user.setEmail(userParam.getEmail());
        user.setPhone(userParam.getPhone());
        user.setState(userParam.getState());
        user.setRemarks(userParam.getRemarks());
        user.setUpdateTime(new Date());
        user.setExpiredTime(userParam.getExpiredTime());
        user.setLocked(userParam.getLocked());
        user.setPasswordExpiredTime(userParam.getPasswordExpiredTime());
        user.setEnabled(userParam.getEnabled());
        int count = this.userMapper.insert(user);
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

    @Override
    public boolean changePassword(UserChangePwdParam changePwdParam){
        User user = userMapper.selectByPrimaryKey(changePwdParam.getUserId());
        if(user == null){
            return false;
        }
        if(passwordEncoder.matches(changePwdParam.getPassword(), user.getPassword())){
            throw new RuntimeException("原密码错误");
        }

        if(!changePwdParam.getNewPassword().equals(changePwdParam.getConfirmPassword())){
            throw new RuntimeException("两次新密码不一致");
        }

        String encryptPwd = passwordEncoder.encode(StringUtils.trim(changePwdParam.getNewPassword()));
        user.setPassword(encryptPwd);
        return userMapper.updateByPrimaryKeySelective(user) > 1;
    }

//
////    @Autowired
////    private AuthenticationManager authenticationManager;
////
////    private JwtProvider jwtProvider;
//
//
//    @Autowired
//    private Cache caffeineCache;
//
//    @Override
//    public ApiResponse login(String loginAccount, String password) {
//
//        log.debug("进入login方法");
//        // 1 创建UsernamePasswordAuthenticationToken
//        UsernamePasswordAuthenticationToken usernameAuthentication = new UsernamePasswordAuthenticationToken(loginAccount, password);
//        // 2 认证
//        Authentication authentication = this.authenticationManager.authenticate(usernameAuthentication);
//        // 3 保存认证信息
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        // 4 生成自定义token
//        AccessToken accessToken = jwtProvider.createToken((UserDetails) authentication.getPrincipal());
//
//        StaffDetails userDetail = (StaffDetails) authentication.getPrincipal();
//        // 放入缓存
//        caffeineCache.put(CacheName.USER, userDetail.getUsername(), userDetail);
//        return ApiResponse.success(accessToken);
//    }
//
//    @Override
//    public ApiResponse logout() {
//        caffeineCache.remove(CacheName.USER, AuthProvider.getLoginAccount());
//        SecurityContextHolder.clearContext();
//        return ApiResponse.success(null);
//    }
//
//    @Override
//    public ApiResult refreshToken(String token) {
//        AccessToken accessToken = jwtProvider.refreshToken(token);
//        UserDetail userDetail = caffeineCache.get(CacheName.USER, accessToken.getLoginAccount(), UserDetail.class);
//        caffeineCache.put(CacheName.USER, accessToken.getLoginAccount(), userDetail);
//        return ApiResult.ok(accessToken);
//    }
}
