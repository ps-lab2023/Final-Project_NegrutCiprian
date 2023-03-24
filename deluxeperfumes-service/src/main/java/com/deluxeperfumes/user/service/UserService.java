package com.deluxeperfumes.user.service;

import com.deluxeperfumes.user.entity.UserEntity;

public interface UserService {
    UserEntity saveUser(UserEntity user);

    UserEntity findUser(String username);

}
