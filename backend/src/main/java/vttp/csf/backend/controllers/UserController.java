package vttp.csf.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vttp.csf.backend.models.User;
import vttp.csf.backend.services.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userSvc;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userSvc.isUsernameAvailable(user.getUsername())) {
            userSvc.registerUser(user);
            // Return JSON response indicating successful registration
            return ResponseEntity.ok().body("{\"message\": \"Registration successful\"}");
        } else {
            // Return JSON response indicating registration failure
            return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"error\": \"Username is already taken\"}");
        }
    }

    @GetMapping("/check-username/{username}")
    public ResponseEntity<Boolean> checkUsernameAvailability(@PathVariable String username) {
        boolean isUsernameAvailable = userSvc.isUsernameAvailable(username);
        return ResponseEntity.ok(isUsernameAvailable);
    }
}
