package org.i2p.fidduniyabe.controller;
import jakarta.validation.Valid;
import org.i2p.fidduniyabe.model.Users;
import org.i2p.fidduniyabe.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/users")
public class UsersController {
   private final UsersService usersService;

   @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    //Logging line
    Logger logger = LoggerFactory.getLogger(UsersController.class);

    @PostMapping("/create")
    public ResponseEntity<Object> add(@Valid @RequestBody Users user) {
        try {
            Users createdUser = usersService.add(user);
            logger.info("User has been created");
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //update method
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable long id, @RequestBody Users updatedUser) {
        try {
            Users result = usersService.updateUser(id, updatedUser);
            logger.info("User has been updated");
            return ResponseEntity.ok(result);
        } catch (RuntimeException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> remove(@PathVariable Long id) {
        boolean success = usersService.remove(id);
        if (success) {
            logger.info("User has been removed");
            return ResponseEntity.ok("User with ID " + id + " has been deactivated successfully.");
        } else {
            logger.error(HttpStatus.NOT_FOUND.getReasonPhrase());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with ID " + id + " not found. No action taken.");
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id) {
        String result = usersService.delete(id);
        if (result.contains("not found")) {
            logger.error("Cannot find ID. User not deleted");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        } else {
            logger.error("User deleted Successfully");
            return ResponseEntity.ok(result);
        }
    }

    @GetMapping("/getAllIncludingInActive")
    public ResponseEntity<Object> findAll() {
        logger.info("Got all users including inActive");
        return new ResponseEntity<>(usersService.findAll(), HttpStatus.OK);
    }


    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllActiveUsers() {
        logger.info("Got all users");

        List<Users> activeUsers = usersService.findAllActiveUsers();

        Map<String, Object> response = Map.of(
                "count", activeUsers.size(),
                "users", activeUsers
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //user login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users loginUser) {
        try {
            String token = usersService.login(loginUser.getEmail(), loginUser.getPassword());
            logger.info("User with email " + loginUser.getEmail() +"logged in successfully");
            return ResponseEntity.ok("JWT Token: " + token);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        }
    }


    @PostMapping("/google")
    public ResponseEntity<Map<String, String>> registerGoogleUser(@RequestBody Users user) {
        usersService.registerOrLoginGoogleUser(user);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User saved or already exists");
        return ResponseEntity.ok(response);
    }

}

