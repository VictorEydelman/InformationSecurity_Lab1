package se.ifmo.InfoSec.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.ifmo.InfoSec.entities.DTO.UserDTO;
import se.ifmo.InfoSec.entities.User;
import se.ifmo.InfoSec.service.JWTService;
import se.ifmo.InfoSec.service.UserService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/data")
public class DataContoller {
    private final UserService userService;
    private final JWTService jwtService;

    @GetMapping(value = "/getListUser", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<String>> getListUser(HttpServletRequest request, HttpServletResponse response) {
        return new ResponseEntity<>(userService.getListUser(),HttpStatus.OK);
    }

    @PutMapping(value = "/updatePassword", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> updatePassword(@RequestBody UserDTO userDTO, HttpServletRequest request, HttpServletResponse response) {
        User user = userService.getByUsername(jwtService.extractUsername(jwtService.resolveToken(request)));
        user.setPassword(userDTO.getPassword());
        userService.save(user);
        return new ResponseEntity<>("Пароль обновлён",HttpStatus.OK);
    }

}
