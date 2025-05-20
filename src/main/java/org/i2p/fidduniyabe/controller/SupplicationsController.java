package org.i2p.fidduniyabe.controller;

import org.i2p.fidduniyabe.model.Supplications;
import org.i2p.fidduniyabe.service.SupplicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/supplications")
public class SupplicationsController {
    final SupplicationsService supplicationsService;

    @Autowired
    public SupplicationsController(SupplicationsService supplicationsService) {
        this.supplicationsService = supplicationsService;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody Supplications supplication) {
      supplicationsService.add(supplication);
      return new ResponseEntity<>(supplication, HttpStatus.CREATED);


    }

}
