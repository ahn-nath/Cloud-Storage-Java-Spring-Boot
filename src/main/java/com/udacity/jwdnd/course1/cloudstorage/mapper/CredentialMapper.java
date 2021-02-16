package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;


	@Mapper
	public interface  CredentialMapper {
	   @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{id}")
	   Credential findCredential(int id);
	   
	   @Select("SELECT * FROM CREDENTIALS")
	   List<Credential> getCredentials();
	   
	   @Select("SELECT * FROM CREDENTIALS WHERE userId = #{userid}")
	   List<Credential> getCredentialsByUser(Integer userId);

	   @Insert("INSERT INTO CREDENTIALS(url, username, key, password, userid) VALUES(#{url},  #{username},  #{key}, #{password}, #{userId})")
	   @Options(useGeneratedKeys = true, keyProperty = "credentialId")
	   Integer insert(Credential credential);

	   @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{id}")
	   void delete(int id);
	   
	   @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, key = #{key}, password = #{password} " +
	            "WHERE credentialid = #{credentialId}")
	   void updateCredential(Credential credential);
	}

