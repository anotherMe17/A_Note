package com.anotherme17.anothernote.config.shiro;

import com.anotherme17.anothernote.entity.PermissionEntity;
import com.anotherme17.anothernote.entity.RoleEntity;
import com.anotherme17.anothernote.entity.UserEntity;
import com.anotherme17.anothernote.mapper.PermissionMapper;
import com.anotherme17.anothernote.mapper.RoleMapper;
import com.anotherme17.anothernote.service.UserService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Shiro 认证
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService mUserService;

    @Autowired
    private RoleMapper mRoleMapper;

    @Autowired
    private PermissionMapper mPermissionMapper;

    public boolean supports(AuthenticationToken token) {
        //仅支持StatelessToken类型的Token
        return token instanceof StatelessToken;
    }

    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("doGetAuthenticationInfo");
        String username = String.valueOf(token.getPrincipal());
        String password = String.valueOf(token.getCredentials());

        /*登录验证*/
        List<UserEntity> users = mUserService.authentication(username, password);

        UserEntity user = null;

        if (users != null && users.size() > 0) {
            user = users.get(0);
        }

        if (user == null) {
            throw new UnknownAccountException("帐号或密码不正确！");
        } else if (user.getState() == UserEntity.STATE_LOCKED) {
            throw new LockedAccountException("账号被锁定！");
        } else if (user.getState() == UserEntity.STATE_UN_ACTIVE) {
            throw new DisabledAccountException("帐号未激活！");
        } else {
            mUserService.updateLastLoginTime(user.getId(), new Date());
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("doGetAuthorizationInfo");
        UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //根据用户ID查询角色（role），放入到Authorization里。
        Set<RoleEntity> roles = mRoleMapper.getRoleByUserID(user.getId());
        for (RoleEntity role : roles) {
            info.addRole(role.getRole());
        }

        /*查询权限*/
        Set<PermissionEntity> permissions = mPermissionMapper.getPermissionByUserID(user.getId());
        for (PermissionEntity permission : permissions) {
            info.addStringPermission(permission.getPermission());
        }
        return info;
    }
}
