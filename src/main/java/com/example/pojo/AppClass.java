package com.example.pojo;

import lombok.Data;

@Data
public class AppClass {
    private String name;
    //    身份证
    private String code;
    //    手机
    private String phone;
    private String uuid;
    //    验证码
    private String captcha;
}
