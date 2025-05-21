package org.i2p.fidduniyabe.service;
import org.i2p.fidduniyabe.model.Supplications;
import org.i2p.fidduniyabe.repository.SupplicationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SupplicationsService {
    final SupplicationsRepository supplicationsRepository;

    @Autowired
    public SupplicationsService(SupplicationsRepository supplicationsRepository) {
        this.supplicationsRepository = supplicationsRepository;
    }

    public Supplications add(Supplications supplication) {
        Supplications newsupplication  =  supplicationsRepository.save(supplication);
        return  newsupplication;
    }

   public void deleteById(Long id) {
        supplicationsRepository.deleteById(id);
   }

    public List<Supplications> findAll() {
        return (List<Supplications>) supplicationsRepository.findAll();
    }


}
