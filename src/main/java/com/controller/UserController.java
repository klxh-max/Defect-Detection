package com.controller;

import com.Vo.UserVo;
import com.entity.User;
import com.service.UserService;
import com.util.JwtUtils;
import io.swagger.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api(tags = "用户相关接口")
@CrossOrigin(origins = "*",maxAge = 3600)
public class UserController {

    @Autowired
    private UserService userService;
    private JwtUtils jwtUitls = new JwtUtils();
    private int id;

    @GetMapping("/login")
    @ApiOperation(value = "用户登陆接口")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "phone", value = "用户手机号"),
                    @ApiImplicitParam(name = "password", value = "用户密码")})
    public Map<String, Object> login(@Param("phone")String phone,@Param("password") String password) {
        Map<String, Object> map = new HashMap();
        User user = userService.login(phone, password);
        if (user == null) {
            map.put("code", 406);
            map.put("msg", "账号或密码错误");
            map.put("data", null);
        } else {
            String token = jwtUitls.createToken(user.getUserId(), user.getUsername());
            Map<String, Object> map1 = new HashMap<>();
            map1.put("username", user.getUsername());
            map1.put("token", token);
            map.put("code", 0);
            map.put("msg", "登陆成功");
            map.put("data", map1);
        }
        return map;
    }

    @PostMapping("/register")
    @ApiOperation(value = "用户注册接口")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "phone", value = "用户手机号"),
                    @ApiImplicitParam(name = "password", value = "用户密码"),
                    @ApiImplicitParam(name = "username", value = "用户昵称")})
    public Map<String, Object> register(@RequestBody Map<String, Object> map1) {
        String phone = (String) map1.get("phone");
        String password = (String) map1.get("password");
        String username = (String) map1.get("username");
        Map<String, Object> map = new HashMap();
        if (userService.register(username, password, phone) == 1) {
            map.put("code", 0);
            map.put("msg", "注册成功");
            map.put("data", null);
        } else {
            map.put("code", 500);
            map.put("msg", "服务器错误");
            map.put("data", null);
        }
        return map;
    }

    @GetMapping("/info")
    @ApiOperation(value = "获取用户个人信息")
    public Map<String, Object> info(@RequestHeader String token) {
        id = jwtUitls.getUserId(token);
        Map<String, Object> map = new HashMap();
        UserVo userVo = userService.info(id);
        if (userVo == null) {
            map.put("code", 406);
            map.put("msg", "服务器错误");
            map.put("data", null);
        } else {
            map.put("code", 0);
            map.put("msg", "获取成功");
            map.put("data", userVo);
        }
        return map;
    }

    @PutMapping("/info")
    @ApiOperation(value = "修改用户个人信息")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "phone", value = "用户手机号"),
                    @ApiImplicitParam(name = "userRealname", value = "用户真实姓名"),
                    @ApiImplicitParam(name = "username", value = "用户昵称"),
                    @ApiImplicitParam(name = "address", value = "用户地址"),
                    @ApiImplicitParam(name = "email", value = "用户邮箱"),
                    @ApiImplicitParam(name = "sex", value = "用户性别")})
    public Map<String, Object> updateUser(@RequestHeader String token,@RequestBody Map<String, Object> map1) {
        id = jwtUitls.getUserId(token);
        Map<String, Object> map = new HashMap();
        if (userService.updateUser(map1,id)!=1) {
            map.put("code", 406);
            map.put("msg", "服务器错误");
            map.put("data", null);
        } else {
            map.put("code", 0);
            map.put("msg", "修改成功");
            map.put("data", null);
        }
        return map;
    }

}
