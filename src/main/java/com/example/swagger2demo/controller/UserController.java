package com.example.swagger2demo.controller;

import com.example.swagger2demo.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//本controller的功能描述
@Api(value = "用户模块", description = "用户模块的接口信息")
@RestController
public class UserController {

    // 用List模拟代替数据库操作
    public static List<User> users = new ArrayList<>();

    static {
        users.add(new User("张三", "123456"));
        users.add(new User("李四", "123456"));
    }

    @GetMapping("/users")
    @ApiOperation(value = "获取用户列表", notes = "获取所有用户的列表")
    public Object users() {
        Map<String, Object> map = new HashMap<>();
        map.put("users", users);
        return map;
    }

    @ApiOperation(value = "获取单个用户", notes = "根据id查询某个用户信息")
    @ApiImplicitParam(name = "id", value = "用户id", paramType = "path")
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") int id) {
        return users.get(id);
    }

    @ApiOperation(value = "添加用户", notes = "根据传入的用户信息添加用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query")
    })
    @PostMapping("/user")
    public Object add(User user) {
        return users.add(user);
    }

    @ApiOperation(value = "删除用户", notes = "根据传入用户id删除对应用户")
    @ApiImplicitParam(name = "id", value = "用户id", paramType = "path")
    @DeleteMapping("/user/{id}")
    public Object delete(@PathVariable("id") int id) {
        return users.remove(id);
    }
}
