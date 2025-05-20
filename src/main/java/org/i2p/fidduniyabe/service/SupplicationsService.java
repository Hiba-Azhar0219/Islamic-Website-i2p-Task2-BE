package org.i2p.fidduniyabe.service;
import org.i2p.fidduniyabe.model.Supplications;
import org.i2p.fidduniyabe.repository.SupplicationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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


}
