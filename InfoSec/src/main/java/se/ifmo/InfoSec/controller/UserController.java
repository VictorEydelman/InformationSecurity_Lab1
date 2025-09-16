package se.ifmo.InfoSec.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.ifmo.InfoSec.entities.DTO.UserDTO;
import se.ifmo.InfoSec.entities.Hash.HashUtil;
import se.ifmo.InfoSec.entities.User;
import se.ifmo.InfoSec.service.AuthntificationService;
import se.ifmo.InfoSec.service.JWTService;
import se.ifmo.InfoSec.service.UserService;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final AuthntificationService authntificationService;
    private final JWTService jwtService;

    public UserController(UserService userService, AuthntificationService authntificationService, JWTService jwtService) {
        this.userService = userService;
        this.authntificationService = authntificationService;
        this.jwtService = jwtService;
    }

    @PostMapping(value = "/login",produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<String>> login(@RequestBody UserDTO userDTO) throws NoSuchAlgorithmException {
        User user = userService.getByUsername(userDTO.getUsername());

        if (user == null) {
            List<String> n = authntificationService.signUp(userDTO, userService, jwtService);
            return ResponseEntity.ok(n);
        } else if(HashUtil.checkPassword(userDTO.getPassword(),user.getPassword())) {
            List<String> n = authntificationService.signIn(user, userService, jwtService);
            return ResponseEntity.ok(n);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}