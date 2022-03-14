package com.example.errand.shiro;

import java.util.Set;

import com.example.errand.entity.User;
import com.example.errand.jwt.JwtToken;
import com.example.errand.service.PermService;
import com.example.errand.service.RoleService;
import com.example.errand.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * @author : 陈宇凡
 * @date : 2022/3/13
 **/

/**
 * JwtRealm 只负责校验 JwtToken
 */
public class JwtRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PermService permService;

    /**
     * 限定这个 Realm 只处理我们自定义的 JwtToken
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        //这里flase
        return token instanceof JwtToken;
    }

    /**
     * 此处的 SimpleAuthenticationInfo 可返回任意值，密码校验时不会用到它
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) authcToken;
        if (jwtToken.getPrincipal() == null) {
            throw new AccountException("JWT token参数异常！");
        }
        // 从 JwtToken 中获取当前用户
        String username = jwtToken.getPrincipal().toString();
        // 查询数据库获取用户信息，此处使用 Map 来模拟数据库
        User user = userService.getUserByusername(username);

        // 用户不存在
        if (user == null) {
            throw new UnknownAccountException("用户不存在！");
        }

        // 用户被锁定
        if (user.getLocked()) {
            throw new LockedAccountException("该用户已被锁定,暂时无法登录！");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, username, getName());
        System.out.println("jwtRealm+"+info);
        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取当前用户
        User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
        // UserEntity currentUser = (UserEntity) principals.getPrimaryPrincipal();
        // 查询数据库，获取用户的角色信息
        Set<String> roles = roleService.getRoleByUsername(currentUser.getUsername());
        // 查询数据库，获取用户的权限信息
        Set<String> perms = permService.getPermByUsername(currentUser.getUsername());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setStringPermissions(perms);
        return info;
    }

}
