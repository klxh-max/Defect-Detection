package com.service.serviceimpl;

import com.Vo.UserVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entity.User;
import com.mapper.UserMapper;
import com.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String phone, String password) {
        User user = userMapper.selectByPhone(phone);
        System.out.println(phone);
        if (user != null && password.equals(user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public int register(String username, String password, String phone) {
        User user=new User();
        user.setUserId(0);
        user.setUsername(username);
        user.setPassword(password);
        user.setPhone(phone);
        user.setIsDeleted(0);
        user.setGmtCreate(new Date());
        user.setGmtModified(user.getGmtCreate());
        return userMapper.insert(user);
    }

    @Override
    public UserVo info(Integer userId) {
        UserVo userVo=new UserVo();
        User user= userMapper.selectById(userId);
        BeanUtils.copyProperties(user, userVo);
        return userVo;
    }

    @Override
    public int updateUser(Map<String, Object> map,Integer userId) {
        int i;
        String username=(String) map.get("username");
        String userRealname=(String) map.get("userRealname");
        String phone=(String) map.get("phone");
        String address=(String) map.get("address");
        String email=(String) map.get("email");
        Integer sex=(Integer) map.get("sex");
        UserVo userVo=new UserVo(userId,username,"质检员",userRealname,phone,address,email,sex);
        User user= new User();
        BeanUtils.copyProperties(userVo,user);
        user.setGmtModified(new Date());
        return userMapper.updateById(user);
    }

}
