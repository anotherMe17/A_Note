package com.anotherme17.anothernote.config.shiro;

import com.anotherme17.anothernote.utils.TextUtils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 访问控制过滤器
 */
public class StatelessAccessControlFilter extends AccessControlFilter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String authorization = request.getHeader("Authorization");
        if (TextUtils.isEmpty(authorization))
            return false;
        try {
            StatelessToken token = new StatelessToken(null, authorization);
            SecurityUtils.getSubject().login(token);
        } catch (AuthenticationException ae) {
            return false;
        }
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        String username = request.getParameter("u");
        String clientDigest = request.getParameter("d");

        /*获取当前的Subject*/
        Subject currentUser = SecurityUtils.getSubject();

        /*已经登录的用户不做过滤*/
        if (currentUser.isAuthenticated())
            return true;

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(clientDigest)) {
            onLoginFail(response);
            return false;
        }

         /*认证验证*/
        StatelessToken token = new StatelessToken(username, clientDigest);

        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查。
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            logger.info("对用户[" + username + "]进行登录验证..验证开始");
            currentUser.login(token);
            logger.info("对用户[" + username + "]进行登录验证..验证通过");
        } catch (UnknownAccountException uae) {
            logger.warn("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            onLoginFail(response); //6、登录失败
            return false;
        } catch (IncorrectCredentialsException ice) {
            logger.warn("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
            onLoginFail(response); //6、登录失败
            return false;
        } catch (LockedAccountException lae) {
            logger.warn("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            onLoginFail(response); //6、登录失败
            return false;
        } catch (ExcessiveAttemptsException eae) {
            logger.warn("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
            onLoginFail(response); //6、登录失败
            return false;
        } catch (AuthenticationException ae) {
            logger.warn("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下", ae);
            onLoginFail(response);
            return false;
        }

        return true;
    }

    //登录失败时默认返回401状态码
    private void onLoginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.getWriter().write("login error");
    }
}
