package com.example.gestionbacspalettes.Controller;

import com.example.gestionbacspalettes.Entity.Utilisateur;
import com.example.gestionbacspalettes.Service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IUserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Utilisateur utilisateur) {
        userService.signUp(utilisateur);
        return ResponseEntity.ok("Inscription réussie !");
    }

    @PostMapping("/signin")
    public ResponseEntity<Utilisateur> signIn(@RequestBody Utilisateur utilisateur) {
        Utilisateur authenticatedUser = userService.signIn(utilisateur.getEmail(), utilisateur.getPassword());

        if (authenticatedUser != null) {
            return ResponseEntity.ok(authenticatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody Utilisateur user) {
        try {
            Utilisateur newUser = userService.addUser(user);
            return ResponseEntity.ok("User added with ID: " + newUser.getIdUser());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding user");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Utilisateur>> getAllUsers() {
        try {
            List<Utilisateur> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}

