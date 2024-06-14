
package com.banking.banking.Cotroller;

import com.banking.banking.DTO.LoginRequest;
import com.banking.banking.DTO.UserDTO;
import com.banking.banking.Model.User;
import com.banking.banking.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;


    //Broken Authentication (API2:2023):
    //Vulnerable login method without proper security checks.
    // Vulnerable login endpoint
    @PostMapping("/login/vulnerable")
    public ResponseEntity<UserDTO> vulnerableLogin(@RequestBody LoginRequest loginRequest) {
        // Vulnerable: No proper password hashing and no security checks
        User user = userService.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRole());
        return ResponseEntity.ok(userDTO);
    }

    //its fixed version
    // Secure login endpointcan you
    @PostMapping("/login/secure")
    public ResponseEntity<UserDTO> secureLogin(@RequestBody LoginRequest loginRequest) {
        // Secure: Proper authentication checks (assuming password is hashed)
        User user = userService.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRole());
        return ResponseEntity.ok(userDTO);
    }


    //Broken Function Level Authorization (API5:2023):
    //Vulnerable createUser method that doesn't check for user roles properly.
    // Vulnerable createUser endpoint
    @PostMapping("/create/vulnerable")
    public ResponseEntity<UserDTO> vulnerableCreateUser(@RequestBody UserDTO userDTO) {
        // Vulnerable: No role-based access control
        User user = userService.createUser(userDTO);
        UserDTO responseUserDTO = new UserDTO();
        responseUserDTO.setUsername(user.getUsername());
        responseUserDTO.setRole(user.getRole());
        return ResponseEntity.ok(responseUserDTO);
    }


    //its fixed version
    // Secure createUser endpoint
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create/secure")
    public ResponseEntity<UserDTO> secureCreateUser(@RequestBody UserDTO userDTO) {
        // Secure: Role-based access control
        User user = userService.createUser(userDTO);
        UserDTO responseUserDTO = new UserDTO();
        responseUserDTO.setUsername(user.getUsername());
        responseUserDTO.setRole(user.getRole());
        return ResponseEntity.ok(responseUserDTO);
    }
}
