package com.fastcampus.ch3;

public interface UserDAO {
    int insertUser(User user);

    User selectUser(String id);

    int updateUser(User user);

    int deleteUser(String id);

    void deleteAll() throws Exception;
}
