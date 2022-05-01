package com.example.usermanagementservice.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.example.usermanagementservice.models.User;
import com.example.usermanagementservice.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initUserAndRoles() {
        userService.initRolesAndUser();
    }

    @PostMapping("/registerNewUser")
    public ResponseEntity<User> setUserDetails(@RequestBody User user) {
        try {

            return ResponseEntity.of(Optional.of(userService.registerNewUser(user)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/forAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> forAdmin() {
        try {
            return ResponseEntity.ok().body("Welcome To The Admin Of This Application!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to access this link!");
        }
    }

    @GetMapping("/forUser")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> forUser() {
        try {
            return ResponseEntity.ok().body("Welcome User!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to access this link!");
        }
    }

    @GetMapping("/getUsers")
    public ResponseEntity<Boolean> getAllUSers(@RequestParam("username") String username) {

        try {

            List<User> users = userService.getAllUsers();
            boolean flag = true;
            for (User u : users) {
                System.out.println(u);
                if (u.getUsername().equals(username)) {
                    flag = false;
                    break;
                }
            }

            if (!flag) {
                return ResponseEntity.ok().body(true);
            }
            throw new Exception();

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.OK).body(false);
        }

    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserByUsername(@RequestParam("username") String username) {
        try {
            return ResponseEntity.ok().body(userService.getUserByUsername(username));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/forgot-password")
    public ResponseEntity<String> changePassword(@RequestParam("username") String username,
            @RequestParam("password") String password, @RequestParam("emailId") String emailId) {
        try {

            boolean ans = userService.forgotPassword(username, password, emailId);

            if (ans == true) {
                return ResponseEntity.ok().body("true");
            } else {
                return ResponseEntity.ok().body("false");
            }

        } catch (Exception e) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found!");
    }
}
