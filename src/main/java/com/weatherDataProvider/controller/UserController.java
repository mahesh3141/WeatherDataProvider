package com.weatherDataProvider.controller;

import com.weatherDataProvider.apiResponse.CommonResponse;
import com.weatherDataProvider.entity.User;
import com.weatherDataProvider.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestParam String name) {
        User newUser = userService.createUser(name);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
    @PutMapping("/activate/{id}")
    public ResponseEntity<User> activateUser(@PathVariable Long id) {
        User activatedUser = userService.activateUser(id);
        return ResponseEntity.ok(activatedUser);
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<User> deactivateUser(@PathVariable Long id) {
        User deactivatedUser = userService.deactivateUser(id);
        return ResponseEntity.ok(deactivatedUser);
    }

    @GetMapping("/{username}")
    public ResponseEntity<String> checkUserStatus(@PathVariable String username){
        boolean isActive = userService.checkUserStatus(username);
        String status = isActive ? "Active":"Deactivate";
        return ResponseEntity.ok(String.format("User %s is currently %s",username,status));
    }

    @GetMapping("/allUser")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> list = userService.getAllUser();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/checkUser/{status}")
    public ResponseEntity<CommonResponse> getUserByStatus(@PathVariable String status){
       boolean flag = status.equals("active");
       List<User> list = userService.getAllUserByStatus(flag);
        CommonResponse response = null;
       if(list.size()>0) {
           response = new CommonResponse(list);
       } else {
           response = new CommonResponse(new String(String.format("No user found for %s status", status)));
       }
        return ResponseEntity.ok(response);
    }
}
