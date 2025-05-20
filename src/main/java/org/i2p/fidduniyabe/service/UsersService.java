package org.i2p.fidduniyabe.service;
import org.i2p.fidduniyabe.model.Users;
import org.i2p.fidduniyabe.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository)
    {
    this.usersRepository = usersRepository;
    }

    public Users add(Users user){
        Users newuser = usersRepository.save(user);
        return newuser;
    }
}
