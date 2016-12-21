package com.zhu.mapper;

import com.zhu.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zhu on 2016/11/3.
 */
@Mapper
public interface MySqlMapper {
    final String findUserByName = "select id,nickname,mobile from testuser where nickname like '%${nickname}%'";
    @Select(findUserByName)
    List<User> findUserByName(@Param("nickname") String nickname);



}
