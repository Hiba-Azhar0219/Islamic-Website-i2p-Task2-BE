package org.i2p.fidduniyabe.controller;
import org.i2p.fidduniyabe.model.SupplicationType;
import org.i2p.fidduniyabe.service.SupplicationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/supplicationType")
public class SupplicationsTypeController {
    final SupplicationTypeService supplicationTypeService;

    @Autowired
    public SupplicationsTypeController(SupplicationTypeService supplicationTypeService) {
        this.supplicationTypeService = supplicationTypeService;
    }

    //Creating Supplication Type
    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody SupplicationType supplicationType) {
        supplicationTypeService.add(supplicationType);
        return new ResponseEntity<>(supplicationType, HttpStatus.CREATED);

    }

    //Deleting Supplication Type
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        supplicationTypeService.deleteById(id);
        return ResponseEntity.ok("Supplication with id " + id + " deleted successfully.");
    }

    //Read all Supplication Types
    @GetMapping("/all")
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(supplicationTypeService.findAll(), HttpStatus.OK);
    }



}
