package org.i2p.fidduniyabe.controller;
import jakarta.validation.Valid;
import org.i2p.fidduniyabe.model.SupplicationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(SupplicationsController.class);

    @PostMapping("/create")
    public ResponseEntity<Object> add(@Valid @RequestBody Supplications supplication) {
        if (supplication.getSupplicationId() != 0) {
            logger.error("Supplication not created");
            throw new IllegalArgumentException("ID should not be provided, it is auto-generated.");
        }

        // Extract the typeId from embedded object or however you're sending it
        Supplications created = supplicationsService.add(supplication);

        logger.info("Supplication added successfully with ID: {}", created.getSupplicationId());
        supplicationsService.add(supplication);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
        
    }

    //update any of the fields of supplication
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateSupplication(@PathVariable Long id, @RequestBody @Valid Supplications updatedSupplication) {
        Supplications updated = supplicationsService.update(id, updatedSupplication);
        if (updated != null) {
            logger.info("Supplication updated successfully.");
            return new ResponseEntity<>("Supplication with id: " + id + " updated successfully.", HttpStatus.OK);
        } else {
            logger.error("Supplication with id: " + id + " not found.");
            return new ResponseEntity<>("Supplication with id: " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    // soft delete Supplication by ID
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeSupplication(@PathVariable Long id) {
        boolean result = supplicationsService.removeSupplication(id);
        if (result) {
            logger.info("Supplication with id: " + id + " removed successfully.");
            return ResponseEntity.ok("Supplication with ID " + id + " removed successfully.");
        } else {
            logger.error("Supplication with id: " + id + " not found.");
            return ResponseEntity.status(404).body("Supplication with ID " + id + " not found.");
        }
    }

    // hard delete Supplication by ID
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {

       supplicationsService.deleteById(id);
       logger.info("Supplication with id: " + id + " deleted successfully.");
        return ResponseEntity.ok("Supplication with id " + id + " deleted successfully.");
    }



    //Read all Supplications
    @GetMapping("/getAll")
    public ResponseEntity<Object> findAll() {
        List<Supplications> supplicationsList = supplicationsService.findAll();
        logger.info("Supplications list returned");
        return ResponseEntity.ok(
                new java.util.HashMap<String, Object>() {{
                    put("count", supplicationsList.size());
                    put("supplications", supplicationsList);
                }}
        );
    }

    @GetMapping("/getAllIncludingInActive")
    public ResponseEntity<Object> findAllIncludingInActive() {
        logger.info("Supplications list returned including inactive supplications");
        return new ResponseEntity<>(supplicationsService.findAllIncludingInActive(), HttpStatus.OK);
    }
}
