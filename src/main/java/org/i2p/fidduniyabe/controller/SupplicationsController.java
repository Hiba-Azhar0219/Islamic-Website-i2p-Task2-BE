package org.i2p.fidduniyabe.controller;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.i2p.fidduniyabe.model.Supplications;
import org.i2p.fidduniyabe.service.SupplicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("supplications")
public class SupplicationsController {
    final SupplicationsService supplicationsService;

    @Autowired
    public SupplicationsController(SupplicationsService supplicationsService) {
        this.supplicationsService = supplicationsService;
    }

//    //add supplication
//    @PostMapping("/create")
//    public ResponseEntity<Object> add(@RequestBody Supplications supplication) {
//            if (supplication.getSupplicationId() != 0) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                        .body("ID should not be provided, it is auto-generated.");
//            }
//            else {
//                supplicationsService.add(supplication);
//                return new ResponseEntity<>(supplication, HttpStatus.CREATED);
//            }
//    }


    @PostMapping("/create")
    public ResponseEntity<Object> add(@Valid @RequestBody Supplications supplication) {
        if (supplication.getSupplicationId() != 0) {
            throw new IllegalArgumentException("ID should not be provided, it is auto-generated.");
        }

        // Save logic here (this will throw DataIntegrityViolationException on duplicates)
        supplicationsService.add(supplication);

        return new ResponseEntity<>(supplication, HttpStatus.CREATED);
    }


    //update any of the fields of supplication
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateSupplication(@PathVariable Long id, @RequestBody @Valid Supplications updatedSupplication) {
        Supplications updated = supplicationsService.update(id, updatedSupplication);
        if (updated != null) {
            return new ResponseEntity<>("Supplication with id: " + id + " updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Supplication with id: " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    // Delete Supplication by ID
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeSupplication(@PathVariable Long id) {
        boolean result = supplicationsService.removeSupplication(id);
        if (result) {
            return ResponseEntity.ok("Supplication with ID " + id + " removed successfully.");
        } else {
            return ResponseEntity.status(404).body("Supplication with ID " + id + " not found.");
        }
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        supplicationsService.deleteById(id);
        return ResponseEntity.ok("Supplication with id " + id + " deleted successfully.");
    }


    //Read all Supplications
    @GetMapping("/getAll")
    public ResponseEntity<Object> findAll() {
        List<Supplications> supplicationsList = supplicationsService.findAll();

        return ResponseEntity.ok(
                new java.util.HashMap<String, Object>() {{
                    put("count", supplicationsList.size());
                    put("supplications", supplicationsList);
                }}
        );
    }

    @GetMapping("/getAllIncludingInActive")
    public ResponseEntity<Object> findAllIncludingInActive() {
        return new ResponseEntity<>(supplicationsService.findAllIncludingInActive(), HttpStatus.OK);
    }

}
