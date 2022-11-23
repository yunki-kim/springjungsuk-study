package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.UserDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired SqlSession session;
    private static String namespace = "com.fastcampus.ch4.dao.UserMapper.";

    @Override
    public int insert(UserDto userDto) throws Exception {
        return session.insert(namespace + "insert", userDto);
    }

    @Override
    public UserDto select(String id) throws Exception {
        return session.selectOne(namespace + "select", id);
    }

    @Override
    public int update(UserDto userDto) throws Exception {
        return session.update(namespace + "update", userDto);
    }

    @Override
    public int delete(String id) throws Exception {
        return session.delete(namespace + "delete", id);
    }

    @Override
    public int deleteAll() {
        return session.delete(namespace + "deleteAll");
    }

    @Override
    public int count() throws Exception {
        return session.selectOne(namespace + "count");
    }
}
