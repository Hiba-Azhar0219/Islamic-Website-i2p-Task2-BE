package org.i2p.fidduniyabe.controller;

import org.i2p.fidduniyabe.model.SupplicationType;
import org.i2p.fidduniyabe.model.Users;
import org.i2p.fidduniyabe.service.SupplicationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/supplicationType")
public class SupplicationsTypeController {
    final SupplicationTypeService supplicationTypeService;

    @Autowired
    public SupplicationsTypeController(SupplicationTypeService supplicationTypeService) {
        this.supplicationTypeService = supplicationTypeService;
    }
    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody SupplicationType supplicationType) {
        supplicationTypeService.add(supplicationType);
        return new ResponseEntity<>(supplicationType, HttpStatus.CREATED);

    }


}
