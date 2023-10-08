package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.udacity.jwdnd.course1.cloudstorage.model.UserModel;

@Mapper
public interface UserMapper extends T_MyBatisMapper<UserModel> {

    @Override
    @Select("SELECT * FROM USERS")
    List<UserModel> getAll();

    @Override
    @Select("SELECT * FROM USERS WHERE userId = #{userId}")
    UserModel get(int id);

    @Override
    @Insert("INSERT INTO USERS(username,salt,password,firstname,lastname) VALUES(#{userName},#{salt},#{password},#{firstName},#{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    void add(UserModel t);

    @Override
    @Update("UPDATE USERS SET username = #{userName},salt = #{salt}, password = #{firstName}, lastname = #{lastName} WHERE userid = #{userid}")
    void update(UserModel t);

    @Override
    @Delete("DELETE FROM USERS WHERE userid = #{userId}")
    void deleteByID(int id);
    
    @Select("SELECT * FROM USERS WHERE username = #{userName}")
    UserModel getByUserName(String userName);

}
