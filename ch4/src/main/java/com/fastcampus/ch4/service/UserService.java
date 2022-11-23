package com.fastcampus.ch4.service;

import com.fastcampus.ch4.domain.UserDto;

public interface UserService {
    UserDto selectUser(String id) throws Exception;
}
