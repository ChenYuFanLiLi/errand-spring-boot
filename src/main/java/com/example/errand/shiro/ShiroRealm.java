package com.example.errand.shiro;

/**
 * @author : 陈宇凡
 * @date : 2022/3/13
 **/
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.example.errand.entity.Role;
import com.example.errand.entity.User;
import com.example.errand.mapper.RoleMapper;
import com.example.errand.service.PermService;
import com.example.errand.service.RoleService;
import com.example.errand.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 同时开启身份验证和权限验证，需要继承 AuthorizingRealm
 * 并实现其  doGetAuthenticationInfo()和 doGetAuthorizationInfo 两个方法
 */
@SuppressWarnings("serial")
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    RoleService roleService;
    @Autowired
    PermService permService;
    @Autowired
    UserService userService;

//    public static Map<String, User> userMap = new HashMap<String, User>(16);
//    public static Map<String, Set<String>> roleMap = new HashMap<String, Set<String>>(16);
//    public static Map<String, Set<String>> permMap = new HashMap<String, Set<String>>(16);

//    static {
//        User user1 = new User(1L, "graython", "dd524c4c66076d1fa07e1fa1c94a91233772d132", "灰先生", false);
//        User user2 = new User(2L, "plum", "cce369436bbb9f0325689a3a6d5d6b9b8a3f39a0", "李先生", false);
//
//        userMap.put("graython", user1);
//        userMap.put("plum", user2);
//
//        roleMap.put("graython", new HashSet<String>() {
//            {
//                add("admin");
//
//            }
//        });
//
//        roleMap.put("plum", new HashSet<String>() {
//            {
//                add("guest");
//            }
//        });
//        permMap.put("plum", new HashSet<String>() {
//            {
//                add("article:read");
//            }
//        });
//    }

    /**
     * 限定这个 Realm 只处理 UsernamePasswordToken
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        //System.out.println(token+"supports");
        System.out.println("usernamepasswordtoken");
        return token instanceof UsernamePasswordToken;
    }

    /**
     * 查询数据库，将获取到的用户安全数据封装返回
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 从 AuthenticationToken 中获取当前用户
        String username = (String) token.getPrincipal();
        // 查询数据库获取用户信息，此处使用 Map 来模拟数据库
        User user = userService.getUserByusername(username);
        System.out.println("查询用户信息成功ShiroRealm93");
        System.out.println(user);
        // 用户不存在
        if (user == null) {
            System.out.println("用户不存在");
            throw new UnknownAccountException("用户不存在！");
        }

        // 用户被锁定
        if (user.getLocked()) {
            System.out.println("用户被锁定");
            throw new LockedAccountException("该用户已被锁定,暂时无法登录！");
        }

        // 使用用户名作为盐值
        System.out.println("盐值加密1");
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);

        System.out.println("盐值加密"+credentialsSalt);

        /**
         * 将获取到的用户数据封装成 AuthenticationInfo 对象返回，此处封装为 SimpleAuthenticationInfo 对象。
         *  参数1. 认证的实体信息，可以是从数据库中获取到的用户实体类对象或者用户名
         *  参数2. 查询获取到的登录密码
         *  参数3. 盐值
         *  参数4. 当前 Realm 对象的名称，直接调用父类的 getName() 方法即可
         */
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), credentialsSalt,
                getName());
        System.out.println("-------------------------------");
        System.out.println(info);
        return info;
    }

    /**
     * 查询数据库，将获取到的用户的角色及权限信息返回
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取当前用户
        User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
        // UserEntity currentUser = (UserEntity)principals.getPrimaryPrincipal();
        // 查询数据库，获取用户的角色信息
        Set<String> roles = roleService.getRoleByUsername(currentUser.getUsername());
        // 查询数据库，获取用户的权限信息
        Set<String> perms = permService.getPermByUsername(currentUser.getUsername());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setStringPermissions(perms);
        System.out.println("用户角色权限信息"+info);
        return info;
    }

}