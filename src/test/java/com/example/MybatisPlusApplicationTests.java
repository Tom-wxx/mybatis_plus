package com.example;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mapper.UsersMapper;
import com.example.pojo.Users;

import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisPlusApplicationTests {

    //继承了basemapper ，所有的方法都来自自己的父类。  我们也可以编写自己的扩展方法
    @Autowired
    private UsersMapper usersMapper;
    @Test
    void contextLoads() {
        //参数是一个Wrapper，条件构造器，这里我们先不用
        //查询全部用户
        List<Users> users = usersMapper.selectList(null);
        users.forEach(System.out::println);
    }

    //测试插入
    @Test
    public void testInsert(){
        Users user=new Users();
        user.setName("王++");
        user.setAge(18);
        user.setPassword("12");
        int insert = usersMapper.insert(user);  //帮我们自动生成id
        System.out.println(insert);      //受影响的行数
        System.out.println(user);   //发现，id会自动回填
    }

    //测试更新
    @Test
    public void testUpdate(){
        Users user=new Users();
        //通过条件自动拼接动态sql
        user.setId(1402629900297371656L);//注意长整型后面加 L
        //user.setName("erva编程");
        user.setPassword("13dss4");
        int update = usersMapper.updateById(user);
        System.out.println(update);
    }

    //测试乐观锁成功的案例
    @Test
    public void testOptimisticlocker(){
        //1.查询用户信息
        Users users = usersMapper.selectById(1402629900297371650l);
        //2.修改用户信息
       // users.setName("狂神");
        users.setPassword("85552");
        //3.执行更新操作
        usersMapper.updateById(users);
    }

    //测试乐观锁失败的案例，多线程下
    @Test
    public void testOptimisticlocker2(){
        //线程一
        //1.查询用户信息
        Users users = usersMapper.selectById(1402629900297371650l);
        //2.修改用户信息
        users.setName("狂神111");
        users.setPassword("852");

        //模拟另外一个线程执行了插队操作
        Users users2 = usersMapper.selectById(1402629900297371650l);
        users2.setName("狂神222");
        users2.setPassword("852");
        usersMapper.updateById(users2);

        //3.执行更新操作，可以使用自旋锁来多次尝试提交
        usersMapper.updateById(users);  //如果没有乐观锁就会覆盖插队线程的值
    }

    //查询测试
    @Test
    public void testSelectById(){
        Users users = usersMapper.selectById(3l);
        System.out.println(users);
    }

    //批量查询测试
    @Test
    public void testSelectByBatchId(){                  //使用数组工具类查询id为1，2，3
        List<Users> users = usersMapper.selectBatchIds(Arrays.asList(1, 2, 3, 4));
        users.forEach(System.out::println);
    }

    //条件查询之一map更加方便，   自动拼接sql
    @Test
    public void testSelectByBatchIds(){
        HashMap<String,Object> map=new HashMap<>();
        //自定义查询
        map.put("name","小龙女");
        map.put("password","123");
        List<Users> users = usersMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    //测试分页查询
    @Test
    public void testPage(){
        //参数一：当前页
        //参数二：页面大小
        //使用分页插件之后，所有的分页操作都变的简单了
        Page<Users> page = new Page<>(1,5);
        usersMapper.selectPage(page,null);
        page.getRecords().forEach(System.out::println);
        System.out.println(page.getTotal());
    }


    //删除通过id
    @Test
    public void deleteById(){
        usersMapper.deleteById(1402629900297371656l);
    }

    //删除通过id批量删除
    @Test
    public  void  deletetByBatchId(){
        usersMapper.deleteBatchIds(Arrays.asList(1402629900297371651l,1402629900297371649l));
    }

    //删除通过id map删除
    @Test
    public  void  deletetMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("name","王祥祥c++");
        usersMapper.deleteByMap(map);

    }

















}
