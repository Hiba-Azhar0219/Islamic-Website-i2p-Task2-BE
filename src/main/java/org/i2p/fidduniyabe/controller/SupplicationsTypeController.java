package org.i2p.fidduniyabe.controller;
import jakarta.validation.Valid;
import org.i2p.fidduniyabe.exception.InvalidSupplicationTypeException;
import org.i2p.fidduniyabe.model.SupplicationType;
import org.i2p.fidduniyabe.service.SupplicationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("supplicationType")
public class SupplicationsTypeController {
    final SupplicationTypeService supplicationTypeService;

    @Autowired
    public SupplicationsTypeController(SupplicationTypeService supplicationTypeService) {
        this.supplicationTypeService = supplicationTypeService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> add(@Valid @RequestBody SupplicationType supplicationType) {
        try {
            SupplicationType created = supplicationTypeService.add(supplicationType);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (InvalidSupplicationTypeException ex) {
            return new ResponseEntity<>(Map.of("error", ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    //update
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody SupplicationType updatedType) {
        Object result = supplicationTypeService.update(id, updatedType);

        if (result instanceof String) {
            return new ResponseEntity<>(Map.of("error", result), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //soft delete
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> softDeleteSupplicationType(@PathVariable Long id) {
        boolean success = supplicationTypeService.remove(id);
        if (success) {
            return ResponseEntity.ok("Supplication type with id " +id + " removed successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supplication type with id " + id + "not found.");
        }
    }

    //hardDelete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        String result = supplicationTypeService.deleteById(id);
        if (result.contains("not found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        } else {
            return ResponseEntity.ok(result);
        }
    }

    //Read all Supplications
    @GetMapping("/getAll")
    public ResponseEntity<Object> findAll() {
        List<SupplicationType> list = supplicationTypeService.findAll();
        int count = list.size();

        Map<String, Object> response = Map.of(
                "count", count,
                "data", list
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAllIncludingInActive")
    public ResponseEntity<Object> findAllIncludingInActive() {
        return new ResponseEntity<>(supplicationTypeService.findAllIncludingInActive(), HttpStatus.OK);
    }


}
