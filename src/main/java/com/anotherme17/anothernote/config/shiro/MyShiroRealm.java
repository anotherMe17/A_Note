package com.anotherme17.anothernote.config.shiro;

import com.anotherme17.anothernote.config.redis.token.RedisTokenManager;
import com.anotherme17.anothernote.config.redis.token.TokenModel;
import com.anotherme17.anothernote.entity.PermissionEntity;
import com.anotherme17.anothernote.entity.RoleEntity;
import com.anotherme17.anothernote.entity.UserEntity;
import com.anotherme17.anothernote.mapper.PermissionMapper;
import com.anotherme17.anothernote.mapper.RoleMapper;
import com.anotherme17.anothernote.service.UserService;
import com.anotherme17.anothernote.utils.MyDES;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTokenManager mRedisTokenManager;

    //用户登录次数计数  redisKey 前缀
    private String SHIRO_LOGIN_COUNT = "a_note_login_count_";

    //用户登录是否被锁定    一小时 redisKey 前缀
    private String SHIRO_IS_LOCK = "a_note_is_lock_";

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

        String username = String.valueOf(token.getPrincipal());
        String clientDigest = String.valueOf(token.getCredentials());

        UserEntity user = null;

        if ("null".equals(username)) {
            TokenModel tm = mRedisTokenManager.getToken(clientDigest);

            if (!mRedisTokenManager.checkToken(tm)) {
                throw new UnknownAccountException("Token错误");
            }

            user = mUserService.getUserByID(tm.getUserId());

            if (user == null) {
                throw new UnknownAccountException("帐号或密码不正确！");
            } else if (user.getState() == UserEntity.STATE_LOCKED) {
                throw new LockedAccountException("账号被锁定！");
            } else if (user.getState() == UserEntity.STATE_UN_ACTIVE) {
                throw new DisabledAccountException("帐号未激活！");
            } else {
                mUserService.updateLastLoginTime(user.getId(), new Date());
            }
            return new SimpleAuthenticationInfo(user, clientDigest, getName());
        } else {

            //访问一次，计数一次
            ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
            opsForValue.increment(SHIRO_LOGIN_COUNT + username, 1);

            //计数大于5时，设置用户被锁定一小时
            if (Integer.parseInt(opsForValue.get(SHIRO_LOGIN_COUNT + username)) >= 5) {
                opsForValue.set(SHIRO_IS_LOCK + username, "LOCK");
                stringRedisTemplate.expire(SHIRO_IS_LOCK + username, 1, TimeUnit.HOURS);
            }

            if ("LOCK".equals(opsForValue.get(SHIRO_IS_LOCK + username))) {
                throw new ExcessiveAttemptsException("由于密码输入错误次数大于5次，帐号已经禁止登录！");
            }

            String paw = username + clientDigest;

            /*登录验证*/
            List<UserEntity> users = mUserService.authentication(username, MyDES.encryptBasedDes(paw));

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
            /*登录成功后清空登录失败次数*/
                opsForValue.set(SHIRO_LOGIN_COUNT + username, "0");
                mUserService.updateLastLoginTime(user.getId(), new Date());
            }
            return new SimpleAuthenticationInfo(user, clientDigest, getName());
        }
    }

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //System.out.println("doGetAuthorizationInfo");
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
