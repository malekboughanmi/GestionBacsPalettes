package com.example.gestionbacspalettes.Service;

import com.example.gestionbacspalettes.Entity.Fournisseur;
import com.example.gestionbacspalettes.Entity.Utilisateur;
import com.example.gestionbacspalettes.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository utilisateurRepository;

    @Override
    public Utilisateur signUp(Utilisateur utilisateur) {
        // Vérifier si l'utilisateur existe déjà dans la base de données
        if (utilisateurRepository.findByEmail(utilisateur.getEmail()) != null) {
            throw new IllegalArgumentException("Cet email est déjà utilisé.");
        }

        // Autres validations et logiques ici

        // Enregistrer l'utilisateur dans la base de données
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Utilisateur signIn(String email, String password) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);

        // Vérifier si l'utilisateur existe et si le mot de passe correspond
        if (utilisateur != null && utilisateur.getPassword().equals(password)) {
            return utilisateur;
        }

        return null; // Authentification échouée
    }

    @Override
    public Utilisateur addUser(Utilisateur u) {

        utilisateurRepository.save(u);
        return(u) ;
    }

    @Override
    public List<Utilisateur> getAllUsers() {
        return utilisateurRepository.findAll();
    }


}







