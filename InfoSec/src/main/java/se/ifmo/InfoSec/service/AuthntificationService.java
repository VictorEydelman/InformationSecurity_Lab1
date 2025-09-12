package se.ifmo.InfoSec.service;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ifmo.InfoSec.entities.DTO.UserDTO;
import se.ifmo.InfoSec.entities.Hash.HashUtil;
import se.ifmo.InfoSec.entities.User;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthntificationService {

    public List<String> signUp(UserDTO userDTO, UserService userService, JWTService jwtService) throws NoSuchAlgorithmException {
        String hashedPass = HashUtil.hashPassword(userDTO.getPassword());
        var user =  new User();
        user.setUsername(userDTO.getUsername());
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
