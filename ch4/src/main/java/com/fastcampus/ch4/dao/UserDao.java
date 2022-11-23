package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.UserDto;

public interface UserDao {
    public int insert(UserDto userDto) throws Exception;

    public UserDto select(String id) throws Exception;

    public int update(UserDto userDto) throws Exception;

    public int delete(String id) throws Exception;

    public int deleteAll();

    public int count() throws Exception;
}
