package org.i2p.fidduniyabe.service;
import org.i2p.fidduniyabe.model.SupplicationType;
import org.i2p.fidduniyabe.repository.SupplicationTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class SupplicationTypeService {
     final SupplicationTypeRepository supplicationTypeRepository;


    public SupplicationTypeService(SupplicationTypeRepository supplicationTypeRepository) {
        this.supplicationTypeRepository = supplicationTypeRepository;
    }

    public SupplicationType add(SupplicationType supplicationType){
        SupplicationType newSupplicationType  = supplicationTypeRepository.save(supplicationType);
        return newSupplicationType;
    }
}
