package com.user.service.User.Service.Services;

import com.user.service.User.Service.Entities.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUser();

    User getUser(String userId);
}
