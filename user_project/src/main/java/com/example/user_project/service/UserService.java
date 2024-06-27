package com.example.user_project.service;

import com.example.user_project.model.request.UserRequest;

public interface UserService {


    Object saveOrUpdate(UserRequest userRequest);
}
