package org.i2p.fidduniyabe.service;
import org.i2p.fidduniyabe.model.Users;
import org.i2p.fidduniyabe.repository.UsersRepository;
import org.i2p.fidduniyabe.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //add user
    public Users add(Users user) {
        if (user.getUserId() != 0) {
            throw new IllegalArgumentException("User ID is auto-generated. Please do not provide it manually.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usersRepository.save(user);
    }

    //update user
    public Users updateUser(Long id, Users updatedUser) {
        Optional<Users> optionalUser = usersRepository.findById(id);

        if (optionalUser.isPresent()) {
            Users existingUser = optionalUser.get();

            if (updatedUser.getUsername() != null) {
                existingUser.setUsername(updatedUser.getUsername());
            }
            if (updatedUser.getEmail() != null) {
                existingUser.setEmail(updatedUser.getEmail());
            }
            if (updatedUser.getPassword() != null) {
                existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }
            if (updatedUser.getPhoneNumber() != null) {
                existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
            }
            if (updatedUser.getGender() != null) {
                existingUser.setGender(updatedUser.getGender());
            }
            existingUser.setUpdatedAt(LocalDateTime.now());

            return usersRepository.save(existingUser);
        } else {
            throw new RuntimeException("User with ID " + id + " not found.");
        }
    }

    //soft delete
    public boolean remove(Long id) {
        Optional<Users> userOptional = usersRepository.findById(id);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            user.setIsActive(false); // set isActive to false
            usersRepository.save(user); // save the update
            return true;
        }
        else return false;
    }

    // HardDeleteById
    public String delete(Long id) {
        if (usersRepository.existsById(id)) {
            usersRepository.deleteById(id);
            return "User with id " + id + " deleted successfully.";
        } else {
            return "User with id " + id + " not found.";
        }
    }
    public List<Users> findAllActiveUsers() {
        return usersRepository.findByIsActiveTrue();
    }
    public List<Users> findAll() {
        return (List<Users>) usersRepository.findAll();
    }

//    user login
@Autowired
private JwtUtil jwtUtil; // Injected JWT util

    public String login(String email, String password) {
        Users user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (passwordEncoder.matches(password, user.getPassword())) {
            return jwtUtil.generateToken(user.getEmail());
        } else {
            throw new RuntimeException("Invalid email or password");
        }
    }
}
