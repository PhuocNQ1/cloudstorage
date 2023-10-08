package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteModel;

@Mapper
public interface NoteMapper extends T_MyBatisMapper<NoteModel> {

    @Override
    @Select("SELECT * FROM NOTES")
    List<NoteModel> getAll();

    @Override
    @Select("SELECT * FROM NOTES WHERE noteid = #{noteId}")
    NoteModel get(int id);

    @Override
    @Insert("INSERT INTO NOTES(notetitle,notedescription,userid) VALUES(#{notetitle},#{notedescription},#{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    void add(NoteModel t);

    @Override
    @Update("UPDATE NOTES SET notetitle = #{notetitle},notedescription = #{notedescription},userid= #{userId} WHERE noteid = #{noteId}")
    void update(NoteModel t);

    @Override
    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
    void deleteByID(int id);

    @Override
    @Select("SELECT * FROM NOTES WHERE userId = #{userId}")
    List<NoteModel> getAllByUserId(int id);
}
