package se.ifmo.InfoSec.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.ifmo.InfoSec.entities.DTO.UserDTO;
import se.ifmo.InfoSec.entities.Util.HashUtil;
import se.ifmo.InfoSec.entities.User;
import se.ifmo.InfoSec.entities.Util.XSSUtil;
import se.ifmo.InfoSec.service.JWTService;
import se.ifmo.InfoSec.service.UserService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class DataContoller {
    private final UserService userService;
    private final JWTService jwtService;

    @GetMapping(value = "/data", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<String>> getListUser(HttpServletRequest request) {
        return new ResponseEntity<>(userService.getListUser(),HttpStatus.OK);
    }

    @PutMapping(value = "/updatePassword", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> updatePassword(@RequestBody UserDTO userDTO, HttpServletRequest request) {
        User screeningUser = User.builder().password(XSSUtil.screening(userDTO.getPassword())).build();
        User user = userService.getByUsername(jwtService.extractUsername(jwtService.resolveToken(request)));
        String hashedPass = HashUtil.hashPassword(screeningUser.getPassword());

        user.setPassword(hashedPass);
        userService.save(user);
        return new ResponseEntity<>("Пароль обновлён",HttpStatus.OK);
    }

}
