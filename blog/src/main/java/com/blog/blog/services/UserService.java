package com.blog.blog.services;

import java.util.List;

import com.blog.blog.models.User;

public interface UserService {

    User save(User user);

    List<User> getAll();

    User get(Long id);

    User update(Long id, User user);

    void delete(Long id);

}
