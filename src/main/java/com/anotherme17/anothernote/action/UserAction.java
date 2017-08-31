package com.anotherme17.anothernote.action;

import com.anotherme17.anothernote.entity.UserEntity;
import com.anotherme17.anothernote.result.BasePageResult;
import com.anotherme17.anothernote.result.BaseResult;
import com.anotherme17.anothernote.service.UserService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
    @RequiresPermissions("admin:user:list")
    public BasePageResult<UserEntity> getUserList(
            @ApiParam(value = "第几页", required = true) @RequestParam(value = "page") int page,
            @ApiParam(value = "每页多少行", required = true) @RequestParam(value = "rows") int rows
    ) {
        return mUserService.getUserList(page, rows);
    }

    @ApiOperation(
            value = "获取用户详情", notes = "获取用户详情", httpMethod = "GET",
            produces = "application/json", tags = {"用户"})
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BaseResult<UserEntity> getUser(@PathVariable String id) {
        return new BaseResult<>(1, "ok", mUserService.getUserByID(id));
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

    @ApiOperation(
            value = "更新用户", notes = "更新用户", httpMethod = "PUT",
            produces = "application/json", tags = {"用户"})
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public BaseResult<UserEntity> putUser(
            @PathVariable String id,
            @ApiParam(value = "用户名") @RequestParam(value = "username", required = false) String username,
            @ApiParam(value = "电话号码") @RequestParam(value = "phone", required = false) String phone,
            @ApiParam(value = "密码") @RequestParam(value = "password", required = false) String password,
            @ApiParam(value = "邮箱") @RequestParam(value = "email", required = false) String email
    ) {
        UserEntity user = new UserEntity(phone, username, password, email);
        user.setId(id);
        mUserService.updateUser(user);
        return new BaseResult<>(1, "ok", user);
    }

    @ApiOperation(
            value = "删除用户", notes = "删除用户", httpMethod = "DELETE",
            produces = "application/json", tags = {"用户"})
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public BaseResult<String> deleteUser(@PathVariable String id) {
        mUserService.deleteUser(id);
        return new BaseResult<>(1, "ok", "删除成功");
    }
}
