package org.i2p.fidduniyabe.controller;
import jakarta.validation.Valid;
import org.i2p.fidduniyabe.exception.InvalidSupplicationTypeException;
import org.i2p.fidduniyabe.model.SupplicationType;
import org.i2p.fidduniyabe.service.SupplicationTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("supplicationType")
public class SupplicationsTypeController {

    final SupplicationTypeService supplicationTypeService;


    //Logging line
    Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    public SupplicationsTypeController(SupplicationTypeService supplicationTypeService) {
        this.supplicationTypeService = supplicationTypeService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> add(@Valid @RequestBody SupplicationType supplicationType) {
        try {
            SupplicationType created = supplicationTypeService.add(supplicationType);
            logger.info("Supplication type has been created");
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (InvalidSupplicationTypeException ex) {
            logger.error(ex.getMessage());
            return new ResponseEntity<>(Map.of("error", ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    //update
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody SupplicationType updatedType) {
        Object result = supplicationTypeService.update(id, updatedType);

        if (result instanceof String) {
            logger.error("Supplication type not found");
            return new ResponseEntity<>(Map.of("error", result), HttpStatus.NOT_FOUND);
        }
        logger.info("Supplication type has been updated");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //soft delete
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> softDeleteSupplicationType(@PathVariable Long id) {
        boolean success = supplicationTypeService.remove(id);
        if (success) {
            logger.info("Supplication type has been removed");
            return ResponseEntity.ok("Supplication type with id " +id + " removed successfully.");
        } else {
            logger.error("Supplication type not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supplication type with id " + id + "not found.");
        }
    }

    //hardDelete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        String result = supplicationTypeService.deleteById(id);
        if (result.contains("not found")) {
            logger.error("Supplication type with ID "+ id + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        } else {
            logger.info("Supplication type has been deleted");
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
        logger.info("supplication type list has been returned");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAllIncludingInActive")
    public ResponseEntity<Object> findAllIncludingInActive() {
        logger.info("supplication type list has been returned including inactive status");
        return new ResponseEntity<>(supplicationTypeService.findAllIncludingInActive(), HttpStatus.OK);
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty.");
        }

        try {
            // ✅ Save to a fixed folder outside of temp directories
            String uploadDir = System.getProperty("user.dir") + "/uploads";
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();  // 💡 Create uploads/ if missing
            }

            // Save the file
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File destination = new File(uploadDir, fileName);
            file.transferTo(destination);

            // URL to access image later (assuming uploads/ is served statically)
            String fileUrl = "http://localhost:3001/uploads/" + fileName;

            return ResponseEntity.ok(Map.of("imageUrl", fileUrl));
        } catch (IOException e) {
            logger.error("File upload failed", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image.");
        }
    }


}
