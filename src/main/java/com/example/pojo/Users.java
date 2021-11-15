package com.example.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import sun.text.resources.ru.FormatData_ru;

import java.util.Date;

@Data
@AllArgsConstructor  //它是lombok中的注解,作用在类上;使用后添加一个构造函数，该构造函数含有所有已声明字段属性参数
@NoArgsConstructor   //注解在类上，为类提供一个无参的构造方法.
public class Users {
    //对应数据库中的主键（uuid、自增主键id、雪花算法、Redis、zookeeper）  
    @TableId(type = IdType.AUTO)  //ID_WORKER 默认全局唯一
    private Long id;
    private String  name;
    private Integer age;
    private String  password;
    @Version  //乐观锁注解
    private Integer version;
    @TableLogic //逻辑删除
    private Integer deleted;
    //字段填充内容
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
