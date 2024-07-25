package com.example.springboot.util;

//TokenUtil的工具类，用于处理用户登录时的身份验证和生成JWT（JSON Web Token）的功能
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.example.springboot.Service.IUserService;
import com.example.springboot.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
@Component//将该类声明为Spring的组件，由Spring进行管理和实例化
public class TokenUtil {
    private static IUserService StaticUserService;

    @Resource
    private  IUserService userService;

    /**
     * 在构造函数执行后，通过@PostConstruct注解的方法将userService赋值给StaticUserService，确保能够在静态方法中使用。
     *
     */
    @PostConstruct
    public void setUserService(){
        StaticUserService= userService;
    }

    /**获取当前登录用户信息的方法。通过RequestContextHolder.getRequestAttributes()获取请求属性，进而获取请求对象和请求头中的token。解析token获取用户ID，并根据ID查询用户信息后返回。
     *
     * @return
     */
    public static User getCurrentUser() {
        // 获取请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 获取请求头中的token
        String token = request.getHeader("token");
        if (StrUtil.isNotBlank(token)) {
            try {
                // 解析token获取用户ID
                String userId = JWT.decode(token).getAudience().get(0);
                // 根据用户ID查询用户信息并返回
                return StaticUserService.getById(Integer.valueOf(userId));
            } catch (Exception e) {
                // 处理token解析失败的情况，例如记录日志或抛出异常
                e.printStackTrace(); // 仅为示例，实际情况应根据项目需求进行处理
            }
        }
        // 处理token为空的情况，例如抛出异常或返回默认用户等
        return null;
    }


    /**
     * 根据用户名和密码，使用加密算法生成JWT的token令牌。
     * @param userId 用户名
     * @param sign 签名
     * @return 生成的JWT token令牌
     * 根据用户名和密码生成JWT令牌的方法。使用用户ID作为audience，设置过期时间并使用指定的签名算法对JWT进行签名，最后返回生成的令牌。
     *
     */
    public static String getToken(String userId,String sign){
        // 创建JWT，并设置用户ID作为audience，设置过期时间并使用签名算法进行签名
       return   JWT
               .create()
               .withAudience(userId)
               //.withExpiresAt(DateUtil.offsetHour(new Date(),2))//生成的 JWT 在两个小时后会过期失效。
               .withExpiresAt(new Date(System.currentTimeMillis()+3600))
               .sign(Algorithm.HMAC256(sign));

    }
//    public  static User GetCurrentUser(){
//     HttpServletRequest request= ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//    request.getHeader("token");
//            String userId;
//            try{
//                userId=JWT.decode(token).getAudience().get(0);
//
//            }catch (JWTDecodeException e){
//
//            }
//
//    }


}
