package com.blog.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.blog.blog.models.User;
import com.blog.blog.services.UserService;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    private @ResponseBody User save(@RequestBody User user){
        return userService.save(user);
    }

    @GetMapping(path = "/getAll")
    private @ResponseBody List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping(path = "/get")
    private @ResponseBody User get(@RequestParam final Long id){
        return userService.get(id);
    }

    @PostMapping(path = "/update")
    private @ResponseBody User update(@RequestParam final Long id, @RequestBody User user){
        return userService.update(id, user);
    }

    @DeleteMapping(path = "/delete")
    private ResponseEntity<?> delete(@RequestParam final Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/")
    public @ResponseBody String authentication(){
        return "Hello World";
    }

}
