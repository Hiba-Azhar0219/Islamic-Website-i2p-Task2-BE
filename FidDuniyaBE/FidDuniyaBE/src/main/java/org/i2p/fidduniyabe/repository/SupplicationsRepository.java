package org.i2p.fidduniyabe.repository;
import org.i2p.fidduniyabe.model.Supplications;
import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface SupplicationsRepository extends CrudRepository<Supplications, Long> {
    List<Supplications> findAllByIsActiveTrue();

}
