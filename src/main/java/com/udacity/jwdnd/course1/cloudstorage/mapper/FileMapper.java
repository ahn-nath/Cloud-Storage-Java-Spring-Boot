package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.udacity.jwdnd.course1.cloudstorage.model.File;


@Mapper
public interface FileMapper {

	@Select("SELECT * FROM FILES WHERE userId = #{id}")
	public List<File> getFilesByUser(Integer id);

	@Select("SELECT * FROM FILES")
	List<File> getFiles();

	@Select("SELECT filename FROM FILES WHERE userId = #{id} AND filename=#{name}")
	public String findFileByName(String name, Integer id);

	@Select("SELECT * FROM FILES WHERE fileid = #{id}")
	public File findFile(Integer id);

	@Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) "
			+ "VALUES (#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
	@Options(useGeneratedKeys = true, keyProperty = "fileId")
	public void insert(File file);

	@Delete("DELETE FROM FILES WHERE fileid = #{id}")
	public void delete(Integer id);

}
