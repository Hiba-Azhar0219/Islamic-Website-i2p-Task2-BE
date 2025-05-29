package org.i2p.fidduniyabe.service;
import org.i2p.fidduniyabe.exception.GlobalExceptionHandler;
import org.i2p.fidduniyabe.model.SupplicationType;
import org.i2p.fidduniyabe.repository.SupplicationTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplicationTypeService {
     final SupplicationTypeRepository supplicationTypeRepository;


    public SupplicationTypeService(SupplicationTypeRepository supplicationTypeRepository) {
        this.supplicationTypeRepository = supplicationTypeRepository;
    }

    //create Supplication Type
    public SupplicationType add(SupplicationType supplicationType){
        SupplicationType newSupplicationType  = supplicationTypeRepository.save(supplicationType);
        return newSupplicationType;
    }

    //Update type or image by id
    public Object update(Long id, SupplicationType updatedType) {
        Optional<SupplicationType> type = supplicationTypeRepository.findById(id);

        if (type.isPresent()) {
            SupplicationType existingType = type.get();

            if (updatedType.getName() != null) {
                existingType.setName(updatedType.getName());
            }
            if (updatedType.getImageUrl() != null) {
                existingType.setImageUrl(updatedType.getImageUrl());
            }
            return supplicationTypeRepository.save(existingType);
        } else {
            return "SupplicationType with id: " + id + " not found";
        }
    }


    //hardDelete
    public String deleteById(Long id) {
        if (supplicationTypeRepository.existsById(id)) {
            supplicationTypeRepository.deleteById(id);
            return "Supplication type with id " + id + " deleted successfully.";
        } else {
            return "Supplication type with id " + id + " not found.";
        }
    }

    public boolean remove(Long id) {
        Optional<SupplicationType> optionalType = supplicationTypeRepository.findById(id);
        if (optionalType.isPresent()) {
            SupplicationType type = optionalType.get();
            type.setIsActive(false);
            supplicationTypeRepository.save(type);
            return true;
        }
        return false;
    }

    public List<SupplicationType> findAll() {
        return supplicationTypeRepository.findAllByIsActiveTrue();
    }

    public List<SupplicationType> findAllIncludingInActive() {
        return (List<SupplicationType>) supplicationTypeRepository.findAll();
    }




}
