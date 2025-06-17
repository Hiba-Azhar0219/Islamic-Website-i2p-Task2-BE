package org.i2p.fidduniyabe.repository;

import org.i2p.fidduniyabe.model.SupplicationType;
import org.i2p.fidduniyabe.model.Supplications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface SupplicationTypeRepository extends CrudRepository<SupplicationType, Long> {
    List<SupplicationType> findAllByIsActiveTrue();



}
