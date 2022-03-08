package com.service;

import com.Vo.UserVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.entity.User;

import java.util.Map;

public interface UserService extends IService<User> {
    User login(String phone,String password);
    int register(String username,String password,String phone);
    UserVo info(Integer userId);
    int updateUser(Map<String, Object> map,Integer userId);
}
