package org.i2p.fidduniyabe.repository;

import org.i2p.fidduniyabe.model.Supplications;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SupplicationsRepository extends CrudRepository<Supplications, Long> {
}
