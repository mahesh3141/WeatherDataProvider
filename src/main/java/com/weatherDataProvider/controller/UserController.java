package com.weatherDataProvider.controller;

import com.weatherDataProvider.apiResponse.CommonResponse;
import com.weatherDataProvider.entity.User;
import com.weatherDataProvider.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "User REST Api operations",
        description = "We can create user activate and deactivate the user and also view all the created users"
)
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Create new user",
            description = "create new user, default status for new user the deactivated."
    )
    @PostMapping
    public ResponseEntity<User> createUser(@RequestParam String name) {
        User newUser = userService.createUser(name);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
    @Operation(
            summary = "Activate the user by passing the user id",
            description = "Activate the user, default status for new user the deactivated. so activate it by passing" +
                    "user id."
    )
    @PutMapping("/activate/{id}")
    public ResponseEntity<User> activateUser(@PathVariable Long id) {
        User activatedUser = userService.activateUser(id);
        return ResponseEntity.ok(activatedUser);
    }
    @Operation(
            summary = "Deactivate the user by passing the user id",
            description = "Deactivate the user by passing user id."
    )
    @PutMapping("/deactivate/{id}")
    public ResponseEntity<User> deactivateUser(@PathVariable Long id) {
        User deactivatedUser = userService.deactivateUser(id);
        return ResponseEntity.ok(deactivatedUser);
    }
    @Operation(
            summary = "Check the user status by passing the username",
            description = "It will show the user is activated or deactivated."
    )
    @GetMapping("/{username}")
    public ResponseEntity<String> checkUserStatus(@PathVariable String username){
        boolean isActive = userService.checkUserStatus(username);
        String status = isActive ? "Active":"Deactivate";
        return ResponseEntity.ok(String.format("User %s is currently %s",username,status));
    }
    @Operation(
            summary = "Display all the users",
            description = "It will show all the users."
    )
    @GetMapping("/allUser")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> list = userService.getAllUser();
        return ResponseEntity.ok(list);
    }
    @Operation(
            summary = "Display the list of users as per status",
            description = "It will show all the users list as per activated or deactivated status."
    )
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
