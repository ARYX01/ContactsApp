package com.turing.rubrica.repo;

import com.turing.rubrica.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer>{
    

}
