package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialModel;

@Mapper
public interface CredentialMapper extends T_MyBatisMapper<CredentialModel> {

    @Override
    @Select("SELECT * FROM CREDENTIALS")
    List<CredentialModel> getAll();

    @Override
    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    CredentialModel get(int id);

    @Override
    @Insert("INSERT INTO CREDENTIALS(url,username,key,password,userid) VALUES(#{url},#{username},#{key},#{password},#{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    void add(CredentialModel t);

    @Override
    @Update("UPDATE CREDENTIALS SET url = #{url},username = #{username}, key = #{key}, password = #{password}, userid = #{userId} WHERE credentialid = #{credentialId}")
    void update(CredentialModel t);

    @Override
    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    void deleteByID(int id);

    @Override
    @Select("SELECT * FROM CREDENTIALS WHERE userId = #{userId}")
    List<CredentialModel> getAllByUserId(int id);
}
