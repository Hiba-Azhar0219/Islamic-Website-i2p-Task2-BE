package org.i2p.fidduniyabe.service;

import org.i2p.fidduniyabe.model.FDUser;
import org.i2p.fidduniyabe.repository.FDUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FDUserService {
    private FDUserRepository fdUserRepository;

    @Autowired
public FDUserService(FDUserRepository fdUserRepository)
    {
    this.fdUserRepository = fdUserRepository;
}       //constructor ends

    public ResponseEntity<Object> newFDUser(
    FDUser fdUser){
        fdUserRepository.save(fdUser);
        return new ResponseEntity<>(fdUser, HttpStatus.CREATED);
    }
}
