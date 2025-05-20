package org.i2p.fidduniyabe.controller;
import org.i2p.fidduniyabe.model.Users;
import org.i2p.fidduniyabe.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/Users")
public class UsersController {
   private final UsersService usersService;

   @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody Users user) {
       usersService.add(user);
       return new ResponseEntity<>(user, HttpStatus.CREATED);

    }
}

