package com.turing.rubrica.repo;

import com.turing.rubrica.entity.Utente;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Integer>{
    
   Optional<Utente> findByUsername(String username);

}
