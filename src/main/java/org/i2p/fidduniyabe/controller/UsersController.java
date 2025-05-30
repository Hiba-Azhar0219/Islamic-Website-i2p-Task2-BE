package org.i2p.fidduniyabe.controller;
import jakarta.validation.Valid;
import org.i2p.fidduniyabe.model.Users;
import org.i2p.fidduniyabe.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UsersController {
   private final UsersService usersService;

   @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> add(@Valid @RequestBody Users user) {
        try {
            Users createdUser = usersService.add(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //update method
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable long id, @RequestBody Users updatedUser) {
        try {
            Users result = usersService.updateUser(id, updatedUser);
            return ResponseEntity.ok(result);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> remove(@PathVariable Long id) {
        boolean success = usersService.remove(id);
        if (success) {
            return ResponseEntity.ok("User with ID " + id + " has been deactivated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with ID " + id + " not found. No action taken.");
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id) {
        String result = usersService.delete(id);
        if (result.contains("not found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        } else {
            return ResponseEntity.ok(result);
        }
    }
    @GetMapping("/getAllIncludingInActive")
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(usersService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllActiveUsers() {
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
            return ResponseEntity.ok("JWT Token: " + token);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        }
    }
}

