package com.anotherme17.anothernote.action;

import com.anotherme17.anothernote.config.shiro.StatelessToken;
import com.anotherme17.anothernote.result.BaseResult;

import org.apache.shiro.SecurityUtils;
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
 * 通用接口
 */
@Api(value = "/", description = "通用接口")
@Controller
@RequestMapping(value = "/")
public class CommonAction {

    @ApiOperation(
            value = "用户登录", notes = "用户登录", httpMethod = "POST",
            produces = "application/json", tags = {"通用"})
    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public BaseResult<StatelessToken> login(
            @ApiParam(value = "用户名", required = true) @RequestParam(value = "username") String username,
            @ApiParam(value = "密码", required = true) @RequestParam(value = "password") String password
    ) {
        StatelessToken token = new StatelessToken(username, password);
        SecurityUtils.getSubject().login(token);
        return new BaseResult<>(1, "ok", token);
    }

    @ApiOperation(
            value = "403", notes = "403", httpMethod = "GET",
            produces = "application/json", tags = {"通用"})
    @ResponseBody
    @RequestMapping(value = "403", method = RequestMethod.GET)
    public BaseResult<String> permissionDenied(
            HttpServletResponse response
    ) {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        return new BaseResult<>(0, "403", "没有权限");
    }
}
