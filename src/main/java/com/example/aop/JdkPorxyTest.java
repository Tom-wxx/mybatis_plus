package com.example.aop;

import org.junit.Test;


public class JdkPorxyTest {
    @Test
    public void jdkProxyTest(){
        //创建目标对象
        UserService userService = new UserServiceImpl();
        //创建工程对象（放入需要代理的接口）
        JdkProxyFactory jdkProxyFactory = new JdkProxyFactory(userService);
        //调用InvocationHandler 是一个接口  类里的方法，执行动态代理 生成代理对象
        UserService proxy = (UserService) jdkProxyFactory.getProxyObject();
        proxy.save();
        System.out.println("============================");
        proxy.select();
    }
}
