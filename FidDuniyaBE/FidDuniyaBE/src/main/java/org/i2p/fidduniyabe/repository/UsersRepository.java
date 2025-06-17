package org.i2p.fidduniyabe.repository;
import org.i2p.fidduniyabe.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    List<Users> findByIsActiveTrue();

    //user login
    Optional<Users> findByEmail(String email);

}
