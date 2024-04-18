package vttp.csf.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vttp.csf.backend.models.User;
import vttp.csf.backend.services.UserService;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        // Fetch user details from Redis
        User storedUser = userService.getUserByUsername(user.getUsername());
        if (storedUser != null && storedUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok().body("{\"message\": \"Login successful\"}");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"Invalid username or password\"}");
        }
    }
}
