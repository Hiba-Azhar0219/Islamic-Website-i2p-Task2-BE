package org.i2p.fidduniyabe.service;
import org.i2p.fidduniyabe.model.SupplicationType;
import org.i2p.fidduniyabe.model.Supplications;
import org.i2p.fidduniyabe.repository.SupplicationTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    //Delete Supplication Type (by ID)
    public void deleteById(Long id) {
        supplicationTypeRepository.deleteById(id);
    }

    //Read all supplications
    public List<SupplicationType> findAll() {
        return (List<SupplicationType>) supplicationTypeRepository.findAll();
    }
}
