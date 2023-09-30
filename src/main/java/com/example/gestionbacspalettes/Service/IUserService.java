package com.example.gestionbacspalettes.Service;

import com.example.gestionbacspalettes.Entity.Fournisseur;
import com.example.gestionbacspalettes.Entity.Utilisateur;

import java.util.List;

public interface IUserService {
    Utilisateur signUp(Utilisateur utilisateur);
    Utilisateur signIn(String email, String password);

    public Utilisateur addUser(Utilisateur u);

    List<Utilisateur> getAllUsers();




}
