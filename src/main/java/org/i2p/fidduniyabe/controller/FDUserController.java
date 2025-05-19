package org.i2p.fidduniyabe.controller;


import org.i2p.fidduniyabe.model.FDUser;
import org.i2p.fidduniyabe.service.FDUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/fdUsers")
public class FDUserController {
   private final FDUserService fdUserService;

   @Autowired
    public FDUserController(FDUserService fdUserService) {
        this.fdUserService = fdUserService;
    }

    @PostMapping("/")
    public ResponseEntity<Object> createFDUser(@RequestBody FDUser fdUser) {
       return fdUserService.newFDUser(fdUser);
    }
}
