package com.boke.service;

import com.boke.pojo.User;

public interface UserService {
    User checkUser(String username,String password);
}
