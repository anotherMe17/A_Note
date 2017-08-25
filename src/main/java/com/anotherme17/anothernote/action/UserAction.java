package com.anotherme17.anothernote.action;

import com.anotherme17.anothernote.entity.UserEntity;
import com.anotherme17.anothernote.result.BasePageResult;
import com.anotherme17.anothernote.result.BaseResult;
import com.anotherme17.anothernote.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 用户接口
 */
@Api(value = "/users", description = "用户接口")
@Controller
@RequestMapping(value = "v1/users")
public class UserAction {

    @Autowired
    private UserService mUserService;

    @ApiOperation(
            value = "获取用户列表", notes = "获取用户列表", httpMethod = "GET",
            produces = "application/json", tags = {"用户"})
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public BasePageResult<UserEntity> getUserList() {

        return null;
    }

    @ApiOperation(
            value = "创建用户", notes = "创建用户", httpMethod = "POST",
            produces = "application/json", tags = {"用户"})
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public BaseResult<UserEntity> postUser(
            @ApiParam(value = "用户名", required = true) @RequestParam(value = "username") String username,
            @ApiParam(value = "电话号码", required = true) @RequestParam(value = "phone") String phone,
            @ApiParam(value = "密码", required = true) @RequestParam(value = "password") String password,
            @ApiParam(value = "邮箱", required = true) @RequestParam(value = "email") String email,
            HttpServletResponse response
    ) {
        UserEntity user = new UserEntity(phone, username, password, email);
        mUserService.insertUser(user);
        return new BaseResult<>(1, "ok", user);
    }
}
