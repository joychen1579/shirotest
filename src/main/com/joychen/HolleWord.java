package com.joychen;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by joychen on 2018/1/30.
 */
public class HolleWord {

    private static final Logger log = LoggerFactory.getLogger(HolleWord.class);

    public static void main(String [] args){

//        log.info("我们正在测试Log4j......");
        System.out.println("aaa");
        /*
        //1、获到安全管理器
        //2、获取用户
        //3、用户登录验证
        //4、权限管理
        //5、角色管理
        //6、session*/


        //1、获到安全管理器
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //2、需设置安全全管理器

        //3、获了Subject对象，即将登录的用户
        Subject currentUser = SecurityUtils.getSubject();

        Session session = currentUser.getSession();
        session.setAttribute("name","joychen");
        String value = (String) session.getAttribute("name");
        if (value !=null){
            System.out.println("aaaa"+value);
        }

        if (currentUser.isAuthenticated() == false){
            UsernamePasswordToken token = new UsernamePasswordToken("root","secret");
            token.setRememberMe(true);

            //登录
            try{
                currentUser.login(token);
                System.out.println("登录成功！");
            }catch (UnknownAccountException e){
                System.out.println("账号不存在！");
            }catch (IncorrectCredentialsException e){
                System.out.println("密码错误！");
            }catch (LockedAccountException e){
                System.out.println("用户已经锁死！");
            }catch (AuthenticationException e){
                System.out.println("认证异常");
            }
        }


        if(currentUser.hasRole("admin")){
            System.out.println("有指定的角色");
        }else{
            System.out.println("没有指定的角色");
        }


        if (currentUser.isPermitted("aa")){
            System.out.println("有指定权限");
        }else{
            System.out.println("没有指定权限");
        }

        //4、权限管理



        //5、角色管理
    }
}
