package com.example.gestionbacspalettes.Repository;

import com.example.gestionbacspalettes.Entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IArticleRepository extends JpaRepository<Article,Long> {
}
