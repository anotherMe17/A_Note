package com.anotherme17.anothernote.action;

import com.anotherme17.anothernote.config.cache.token.manager.EhcacheTokenManager;
import com.anotherme17.anothernote.config.shiro.StatelessToken;
import com.anotherme17.anothernote.entity.UserEntity;
import com.anotherme17.anothernote.result.BaseResult;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * 通用接口
 */
@Api(value = "/", description = "通用接口")
@Controller
@RequestMapping(value = "/")
public class CommonAction {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EhcacheTokenManager mEhcacheTokenManager;

    @ApiOperation(
            value = "用户登录", notes = "用户登录", httpMethod = "POST",
            produces = "application/json", tags = {"通用"})
    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public BaseResult<String> login(
            @ApiParam(value = "用户名", required = true) @RequestParam(value = "username") String username,
            @ApiParam(value = "密码", required = true) @RequestParam(value = "password") String password,
            HttpServletResponse response
    ) {
        StatelessToken token = new StatelessToken(username, password);
        try {
            logger.info("对用户[" + username + "]进行登录验证..验证开始");
            SecurityUtils.getSubject().login(token);
            logger.info("对用户[" + username + "]进行登录验证..验证通过");
        } catch (UnknownAccountException uae) {
            logger.warn("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //6、登录失败
            return new BaseResult<>(0, uae.toString(), null);
        } catch (IncorrectCredentialsException ice) {
            logger.warn("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //6、登录失败
            return new BaseResult<>(0, ice.toString(), null);
        } catch (LockedAccountException lae) {
            logger.warn("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //6、登录失败
            return new BaseResult<>(0, lae.toString(), null);
        } catch (ExcessiveAttemptsException eae) {
            logger.warn("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //6、登录失败
            return new BaseResult<>(0, eae.toString(), null);
        } catch (AuthenticationException ae) {
            logger.warn("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下", ae);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //6、登录失败
            return new BaseResult<>(0, ae.toString(), null);
        }

        UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        String tk = mEhcacheTokenManager.createToken(user.getId()).toString();
        return new BaseResult<>(1, "ok", tk);
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
