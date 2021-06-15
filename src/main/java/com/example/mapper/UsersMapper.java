package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.Users;
import org.springframework.stereotype.Repository;

//在对应的mapper上实现基本的接口，BaseMapper
@Repository   //代表持久层
public interface UsersMapper extends BaseMapper<Users> {
    //所有的crud操作已经编写完成
    //你不需要像以前一样配置一大堆文件

}
