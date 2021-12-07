package com.example.aop;

public class UserServiceImpl implements UserService{
    @Override
    public void save() {
        System.out.println("save  jdk  动态代理");
    }

    @Override
    public int select() {
        System.out.println("select  jdk  动态代理");
        return 10;
    }
}
