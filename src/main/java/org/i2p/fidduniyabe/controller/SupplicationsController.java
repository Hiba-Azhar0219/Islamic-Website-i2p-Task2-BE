package org.i2p.fidduniyabe.controller;

import org.i2p.fidduniyabe.model.Supplications;
import org.i2p.fidduniyabe.service.SupplicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    //Delete Supplication by ID
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        supplicationsService.deleteById(id);
        return ResponseEntity.ok("Supplication with id " + id + " deleted successfully.");

    }

    //Read all Supplications
    @GetMapping("/all")
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(supplicationsService.findAll(), HttpStatus.OK);
    }

}
