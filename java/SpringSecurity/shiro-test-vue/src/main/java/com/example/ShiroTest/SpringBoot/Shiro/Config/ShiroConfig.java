package com.example.ShiroTest.SpringBoot.Shiro.Config;

import com.example.ShiroTest.SpringBoot.Shiro.Realm.UserRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/**
 * @Auther: 吕宏博
 * @Date: 2024--03--27--10:22
 * @Description:
 */
@Configuration
public class ShiroConfig {
    /**
     * ShiroFilterFactoryBean 需要注入 securityManager
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置安全管理器，过滤器就是shiro就行权限校验的核心，进行认证和授权是需要SecurityManager的
//        securityManager.setRealm(new IniRealm("classpath:shiro.ini"));
        // 注入安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 添加shiro的内置过滤器
        /**
         * 规则
         * anno	不需要授权、登录就可以访问。eg:/index
         * authc	 需要登录授权才能访问。eg：/用户中心
         * authcBasic	Basic HTTP身份验证拦截器
         * logout	退出拦截器。退出成功后，会 redirect到设置的/URI
         * noSessionCreation	不创建会话连接器
         * perms	授权拦截器:perm['user:create']
         * port	端口拦截器.eg:port[80]
         * rest	rest风格拦截器
         * roles	角色拦截器。eg：role[administrator]
         * ssl	ssl拦截器。通过https协议才能通过
         * user	用户拦截器。eg：登录后（authc），第二次没登陆但是有记住我(remmbner)都可以访问。
         * */
        // 设置登录的请求
//        shiroFilterFactoryBean.setLoginUrl("/login.html");
//        // 设置未授权的请求
//        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized.html");
        // 3设置拦截器
        //3.1设置shiro的拦截规则
        Map<String, String> filterMap = new HashMap<>();
        // 设置哪些页面需要受保护.
        // 以及访问这些页面需要的权限.
        filterMap.put("/index.html","anon");//主页不需要拦截
        filterMap.put("/login","anon");//登录页面不需要拦截
        filterMap.put("/login.html","anon");//登录页面不需要拦截
        filterMap.put("/user/login","anon");
        filterMap.put("/user/logout","logout");//退出登录
        filterMap.put("/register.html","anon");//注册页面不需要拦截
        filterMap.put("/register", "anon");//注册页面不需要拦截
        filterMap.put("/user/register", "anon");
        filterMap.put("/static/**","anon");//静态文件不需要拦截
        filterMap.put("/test.html","anon");//test页码不需要拦截

        filterMap.put("/**","authc");
        // 设置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了

        //3.2设置拦截目标
        //3.3设置过滤器链
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        shiroFilterFactoryBean.setLoginUrl("/login.html");

        // 设置未授权的请求
        shiroFilterFactoryBean.setUnauthorizedUrl("/login.html");
        shiroFilterFactoryBean.setSuccessUrl("/test.html");
        return shiroFilterFactoryBean;
    }

    /**
     * DefaultWebSecurityManager 需要注入 realm
     */
//    @Bean
//    public DefaultWebSecurityManager getDefaultWebSecurityManager(UserRealm userRealm){
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        // 关联realm
//        securityManager.setRealm(userRealm);
//        return securityManager;
//    }

    /**
     * DefaultWebSecurityManager 先注入 realm测试
     * 然后注入 securityManager测试
     * */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // securityManager完成校验关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    /**
     * 创建userrealm对象，需要自定义类
     */
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }
    /**
     * 创建IniRealm对象，需要自定义类
     */
//    @Bean
//    public IniRealm iniRealm(){
//        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
//        return new IniRealm();
//    }
}
