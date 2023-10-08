package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.udacity.jwdnd.course1.cloudstorage.model.FileModel;

@Mapper
public interface FileMapper extends T_MyBatisMapper<FileModel> {

    @Override
    @Select("SELECT * FROM FILES")
    List<FileModel> getAll();

    @Override
    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    FileModel get(int id);

    @Override
    @Insert("INSERT INTO FILES(filename,contenttype,filesize,filedata,userid) VALUES(#{fileName},#{contenType},#{fileSize},#{fileData},#{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    void add(FileModel t);

    @Override
    @Update("UPDATE FILES SET filename = #{fileName},contenttype = #{contenType}, filesize = #{fileSize}, filedata = #{fileData}, userid = #{userId} WHERE fileId = #{fileId}")
    void update(FileModel t);

    @Override
    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    void deleteByID(int id);

    @Override
    @Select("SELECT * FROM FILES WHERE userId = #{userId}")
    List<FileModel> getAllByUserId(int id);
}
