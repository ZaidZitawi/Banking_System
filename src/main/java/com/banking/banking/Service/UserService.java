package com.banking.banking.Service;

import com.banking.banking.DTO.UserDTO;
import com.banking.banking.Model.Account;
import com.banking.banking.Model.User;
import com.banking.banking.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword()); // Ensure password hashing in real implementations
        user.setRole(userDTO.getRole());
        return userRepository.save(user);
    }

    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public User getUserById(int userid) {
        return userRepository.findById(userid)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
    }
}
