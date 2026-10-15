package com.userdao;

import java.util.List;

import com.user.User;
import org.springframework.context.annotation.Bean;

public interface UserDao {
    void initTable();
    Long create(String username);
    int delete(Long id);
    User select(Long id);
    List<User> selectAll();
}
