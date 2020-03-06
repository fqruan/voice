package com.orange.voice.dao;

import com.orange.voice.bean.Mobile;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MobileMapper {

    @Select("select * from mobile_info where user_id = #{userid}")
    Mobile getMobileInfoByUserId(@Param("userid") int userId);

    @Insert("insert into mobile_info (user_id, mobile_id) values(#{userId}, #{mobileId})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void setNewMobileInfo(Mobile mobile);
}
