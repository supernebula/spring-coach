package com.evol.service;

import com.evol.domain.dto.UserChangePwdParam;
import com.evol.domain.model.User;

public interface UserService {

    boolean addUser(String username, String password);

    boolean addUser(User user, Integer[] roleIds);

    boolean updateUser(User userParam, Integer[] roleIds);

    boolean changePassword(UserChangePwdParam changePwdParam);



}
