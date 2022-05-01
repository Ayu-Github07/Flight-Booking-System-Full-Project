package com.example.usermanagementservice.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.usermanagementservice.models.Role;
import com.example.usermanagementservice.models.User;
import com.example.usermanagementservice.repository.RoleRepo;
import com.example.usermanagementservice.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User setNewUser(User user) {
        return userRepo.save(user);
    }

    public boolean forgotPassword(String username, String password, String emailId) {

        User user = userRepo.findByUsername(username);
        System.out.println("Username is: " + user);

        if (user.getEmailId().equals(emailId)) {
            user.setPassword(passwordEncoder.encode(password));
            userRepo.save(user);
            return true;
        }
        return false;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public User registerNewUser(User user) {

        Role role = roleRepo.findById("USER").get();
        System.out.println("Roles : " + role);

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return user;
    }

    public void initRolesAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("ADMIN");
        adminRole.setRoleDescription("Admin Role");
        roleRepo.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("USER");
        userRole.setRoleDescription("Passenger Role");
        roleRepo.save(userRole);

        Role airportAuthority = new Role();
        airportAuthority.setRoleName("AIRPORT AUTHORITY");
        airportAuthority.setRoleDescription("Airport Authority Role");
        roleRepo.save(airportAuthority);

        User user = new User();
        user.setUsername("ConiferousClone");
        user.setPassword(passwordEncoder.encode("Ayush@123"));
        user.setEmailId("07ayush0707@gmail.com");
        user.setFirstName("Ayush");
        user.setLastName("Agrawal");
        user.setMobile(877083505);
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        user.setRoles(adminRoles);
        userRepo.save(user);
    }

}
