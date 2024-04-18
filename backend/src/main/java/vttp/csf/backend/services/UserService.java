package vttp.csf.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import vttp.csf.backend.models.User;

@Service
public class UserService {

    @Autowired @Qualifier("myredis")
    private RedisTemplate<String, String> redisTemplate;

    public void registerUser(User user) {
        redisTemplate.opsForHash().put("users", user.getUsername(), user.getPassword() + "," + user.getEmail());
    }

    public boolean isUsernameAvailable(String username) {
        String lowercaseUsername = username.toLowerCase();
        return !redisTemplate.opsForHash().hasKey("users", lowercaseUsername);
    }

    public User getUserByUsername(String username) {
        String userString = (String) redisTemplate.opsForHash().get("users", username);
        if (userString != null) {
            String[] parts = userString.split(",");
            User user = new User();
            user.setUsername(username);
            user.setPassword(parts[0]);
            user.setEmail(parts[1]);
            return user;
        } else {
            return null;
        }
    }

}
