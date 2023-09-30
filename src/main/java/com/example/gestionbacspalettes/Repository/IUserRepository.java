package com.example.gestionbacspalettes.Repository;

import com.example.gestionbacspalettes.Entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByEmail(String email);
}
