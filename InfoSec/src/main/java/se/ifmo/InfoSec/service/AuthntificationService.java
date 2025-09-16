package se.ifmo.InfoSec.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import se.ifmo.InfoSec.entities.DTO.UserDTO;
import se.ifmo.InfoSec.entities.Util.HashUtil;
import se.ifmo.InfoSec.entities.User;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthntificationService {

    public List<String> signUp(User user, UserService userService, JWTService jwtService) throws NoSuchAlgorithmException {
        String hashedPass = HashUtil.hashPassword(user.getPassword());
        user.setPassword(hashedPass);
        userService.save(user);
        List<String> result = new ArrayList<>();
        result.add(user.getUsername());
        result.add(jwtService.generateToken(user.getUsername()));
        return result;
    }

    public List<String> signIn(User user, UserService userService, JWTService jwtService){
        List<String> result = new ArrayList<>();
        result.add(user.getUsername());
        result.add(jwtService.generateToken(user.getUsername()));
        return result;
    }
}
