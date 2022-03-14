package com.example.errand.config;

import com.example.errand.jwt.JwtFilter;
import com.example.errand.shiro.JwtCredentialsMatcher;
import com.example.errand.shiro.JwtRealm;
import com.example.errand.shiro.ShiroRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AuthenticationStrategy;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : 陈宇凡
 * @date : 2022/3/13
 **/
@Configuration
public class ShiroConfig {
    /**
     * 交由spring接管shiro-bean的生命周期
     */
    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }
    /**
     * 为spring-bean开启对shiro注解的支持
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    /**
     * 不向spring容器中注册JwtFilter bean，防止spring将jwtfilter注册为全局过滤器
     * 全局过滤器会对所有请求进行拦截，
     */
    @Bean
    public FilterRegistrationBean<Filter> registrationBean(JwtFilter filter){
        FilterRegistrationBean<Filter> registration=new FilterRegistrationBean<Filter>(filter);
        registration.setEnabled(false);
        return registration;
    }

    @Bean
    public JwtFilter jwtFilter(){
        return new JwtFilter();
    }
    /**
     * 配置访问资源需要的权限
     */
    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/user/login");
        shiroFilterFactoryBean.setSuccessUrl("/authorized");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");

        // 添加 jwt 专用过滤器，拦截除 /login 和 /logout 外的请求
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("jwtFilter", jwtFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/user/login", "anon"); // 可匿名访问
        filterChainDefinitionMap.put("/user/register", "anon"); // 可匿名访问

        filterChainDefinitionMap.put("/logout", "logout"); // 退出登录
        filterChainDefinitionMap.put("/**", "jwtFilter,authc"); // 需登录才能访问
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
    /**
     * 配置ModularRealmAuthenticator
     */
    @Bean
    public ModularRealmAuthenticator authenticator(){
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        AuthenticationStrategy strategy = new FirstSuccessfulStrategy();
        authenticator.setAuthenticationStrategy(strategy);
        return authenticator;
    }
    /**
     * 禁用session,保证每次请求都要重新认证
     */
    @Bean
    protected SessionStorageEvaluator sessionStorageEvaluator(){
        DefaultSessionStorageEvaluator sessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        return sessionStorageEvaluator;
    }
    /**
     * JwtRealm配置，实现Realm接口
     */
    @Bean
    JwtRealm jwtRealm(){
        JwtRealm jwtRealm = new JwtRealm();
        //设置加密算法
        CredentialsMatcher credentialsMatcher = new JwtCredentialsMatcher();
        //设置加密次数
        jwtRealm.setCredentialsMatcher(credentialsMatcher);
        return jwtRealm;
    }

    /**
     * ShiroRealm 配置，需实现 Realm 接口
     */
    @Bean
    ShiroRealm shiroRealm() {
        ShiroRealm shiroRealm = new ShiroRealm();
        // 设置加密算法
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher("SHA-1");

        // 设置加密次数
        credentialsMatcher.setHashIterations(1);
        shiroRealm.setCredentialsMatcher(credentialsMatcher);
        return shiroRealm;
    }

    /**
     * 配置SecurityManage
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        // 1.Authenticator
        securityManager.setAuthenticator(authenticator());

        // 2.Realm
        List<Realm> realms = new ArrayList<Realm>(16);
        realms.add(jwtRealm());
        realms.add(shiroRealm());
        securityManager.setRealms(realms);


        // 3.关闭shiro自带的session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        subjectDAO.setSessionStorageEvaluator(sessionStorageEvaluator());
        securityManager.setSubjectDAO(subjectDAO);

        return securityManager;
    }

}
