package com.orange.voice.dao;

import com.orange.voice.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user_info limit 10")
    List<User> getList();

    @Select("select * from user_info where username = #{username} and password = #{password}")
    User getUserByUserNameAndPassword(@Param("username") String username, @Param("password") String password);

    @Select("select * from user_info where username = #{username}")
    User getUserByUserName(@Param("username") String username);

    @Insert("insert into user_info (username, password, is_verify) values(#{userName}, #{passWord}, #{isVerify})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void setNewUser(User user);
}
