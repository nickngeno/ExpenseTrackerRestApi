package com.kimmy.ExpenseTrackerREStAPI.service.serviceImpl;

import com.kimmy.ExpenseTrackerREStAPI.Constants;
import com.kimmy.ExpenseTrackerREStAPI.entity.User;
import com.kimmy.ExpenseTrackerREStAPI.exception.ApiRequestException;
import com.kimmy.ExpenseTrackerREStAPI.repository.UserRepository;
import com.kimmy.ExpenseTrackerREStAPI.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashedPassword);
        return userRepository.save(user);

    }

    public Map<String, String> login(User user) {
        Map<String, String> token = new HashMap<>();
        if (user.getEmail() == null || user.getPassword() == null) {
            throw new ApiRequestException("username or password cannot be empty!");
        } else {
            User existingUser = userRepository.findByEmail(user.getEmail());
            if (existingUser == null) throw new ApiRequestException("no such person exists!");
            else if (!BCrypt.checkpw(user.getPassword(), existingUser.getPassword())) {
                throw new ApiRequestException("invalid username/password combination");
            } else token = getJwtToken(existingUser);
        }

        return token;
    }

    private Map<String, String> getJwtToken(User user) {
        long timestamp = System.currentTimeMillis();

        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.JWT_TOKEN_SECRET)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.JWT_TOKEN_DURATION))
                .claim("userId",user.getUserId())
                .claim("email", user.getEmail())
                .claim("name",user.getName())
                .compact();

        Map<String, String> userToken = new HashMap<>();
        userToken.put("token", token);
        return userToken;

    }
}
