package org.i2p.fidduniyabe.repository;

import org.i2p.fidduniyabe.model.FDUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@RepositoryRestResource
public interface FDUserRepository extends JpaRepository<FDUser, String> {


}
