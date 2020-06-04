package com.boke.service;

import com.boke.mapper.UserRespority;
import com.boke.pojo.User;
import com.boke.util.MD5Utils;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRespority userRespority;

    public User checkUser(String username,String password){
        User user = userRespority.findByUsernameAndPassword(username, MD5Utils.code(password));

        System.out.println("--------------"+password+"-------------------------------");
      return user;
    }

}
