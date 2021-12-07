package com.example.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyFactory implements InvocationHandler {

    private Object target;  //目标对象
    public JdkProxyFactory(Object target) {
        this.target=target;
    }
/**
 * 获取代理对象
 * loader 自然是类加载器
 * interfaces 代码要用来代理的接口
 * h 一个 InvocationHandler 对象*/
    public Object getProxyObject(){
        //通过 Proxy 的静态方法 newProxyInstance 才会动态创建代理。 生成代理对象
        System.out.println("通过 Proxy 的静态方法 newProxyInstance 才会动态创建代理。");
        return  Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    /**
     * proxy 代理对象
     * method 代理对象调用的方法
     * args 调用的方法中的参数*/
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("增强代码，添加日志功能");
        return method.invoke(target,args);
    }
}
