package com.example;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mapper.UsersMapper;
import com.example.pojo.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class WarpperTest {

    //继承了basemapper ，所有的方法都来自自己的父类。  我们也可以编写自己的扩展方法
    @Autowired
    private UsersMapper usersMapper;
    //条件查询
    @Test
    void test1() {
        //查询name不为空的用户，并且年龄大于等于32
        QueryWrapper<Users> warpper = new QueryWrapper<>();  //查询一般用Query
        warpper.isNotNull("name")  //name不为空
                .ge("age",32); //大于32
        List<Users> users = usersMapper.selectList(warpper);  //和map对比一下
        users.forEach(System.out::println);
    }

    @Test
    void test2() {
        //查询name不为空的用户，并且年龄大于等于32
        QueryWrapper<Users> warpper = new QueryWrapper<>();
        warpper.eq("name","张三丰"); //name不为空
        Users user= usersMapper.selectOne(warpper);  //查询一个数据，出现多个结构时使用list或者map
        System.out.println(user);
    }

    @Test
    void test3() {
        //查询年龄在20~30之间
        QueryWrapper<Users> warpper = new QueryWrapper<>();
        warpper.between("age", 20, 30);//区间
        Integer count = usersMapper.selectCount(warpper);//查询结果数
        System.out.println(count);
    }

    //模糊查询
    @Test
    void test4() {
        //查询年龄在20~30之间
        QueryWrapper<Users> warpper = new QueryWrapper<>();
        warpper.notLike("name","王")
                .likeRight("name","小");
        List<Map<String, Object>> maps = usersMapper.selectMaps(warpper);//查询结果数
        maps.forEach(System.out::println);
    }

    //模糊查询
    @Test
    void test5() {
        QueryWrapper<Users> warpper = new QueryWrapper<>();
        //id在子查询中查出来
        warpper.inSql("id","select id from users where id<9");
        List<Object> Object = usersMapper.selectObjs(warpper);
        Object.forEach(System.out::println);
    }

    //模糊查询
    @Test
    void test6() {
        QueryWrapper<Users> warpper = new QueryWrapper<>();
        //通过id进行排序
        warpper.orderByDesc("id");
        List<Users> users = usersMapper.selectList(warpper);
        users.forEach(System.out::println);
    }

}
