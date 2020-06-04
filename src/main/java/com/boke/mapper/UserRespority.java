package com.boke.mapper;

import com.boke.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespority extends JpaRepository<User,Long> {
    User findByUsernameAndPassword(String username,String password);
}
