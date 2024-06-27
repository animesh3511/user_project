package com.example.user_project.controller;

import com.example.user_project.model.request.UserRequest;
import com.example.user_project.model.response.CustomEntityResponse;
import com.example.user_project.model.response.EntityResponse;
import com.example.user_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<?> saveOrUpdate(@RequestBody UserRequest userRequest)
    {
     try
     {
         return new ResponseEntity(new EntityResponse(userService.saveOrUpdate(userRequest),0), HttpStatus.OK);

     }catch (Exception e)
     {

         return new ResponseEntity(new CustomEntityResponse(e.getMessage(),-1),HttpStatus.BAD_REQUEST);

     }




    }





}
