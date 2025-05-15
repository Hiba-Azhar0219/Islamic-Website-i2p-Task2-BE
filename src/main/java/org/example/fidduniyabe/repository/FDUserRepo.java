package org.example.fidduniyabe.repository;

import org.example.fidduniyabe.model.FDUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface FDUserRepo extends JpaRepository<FDUser, Long> {
}
