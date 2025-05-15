package org.example.fidduniyabe.repository;

import org.example.fidduniyabe.model.Supplications;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SupplicationsRepo extends CrudRepository<Supplications, Long> {
}
