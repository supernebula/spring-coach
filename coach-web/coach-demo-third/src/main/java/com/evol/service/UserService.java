package com.evol.service;

import com.evol.domain.UserEntity;
import com.evol.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 4.4 配置使用Spring Data JDBC
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    //查
    public List<UserEntity> getAll(){
        List<UserEntity> userList = userRepository.findAll();
        return userList;
    }
    //查
    public List<UserEntity> getAllByName(String name){
        return userRepository.findByName(name);
    }

    //增
    @Transactional
    public int insertUser(UserEntity userEntity){
        return userRepository.insertNameAndAge(userEntity.getId(), userEntity.getName(), userEntity.getAge());
    }
    //改
    @Transactional
    public int updateUser(UserEntity userEntity){
        return userRepository.updateNameAndAge(userEntity.getId(), userEntity.getName(), userEntity.getAge());
    }
    //删
    @Transactional
    public void deleteUserById(Integer id){
        userRepository.deleteById(id);
    }
}
