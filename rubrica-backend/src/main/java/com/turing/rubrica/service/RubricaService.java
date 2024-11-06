package com.turing.rubrica.service;

import com.turing.rubrica.entity.Persona;
import com.turing.rubrica.repo.PersonaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RubricaService {
    
    @Autowired
    private PersonaRepository personaRepository;
    
    public List<Persona> getTestContacts() {
        List<Persona> contacts = new ArrayList<>();
        
        //TEST
        Persona personaTest1 = new Persona("Pippo", "Baudo", "via fritto misto", "342534", 99);
        personaTest1.setId(0);
        contacts.add(personaTest1);
        Persona personaTest2 = new Persona("Test", "Rest", "via tombolo", "0000000", 1);
        personaTest2.setId(1);
        contacts.add(personaTest2);
        return contacts;
    }
    
    public List<Persona> getAllContacts() {
        return personaRepository.findAll();
    }
    
    public Persona addContact(Persona p) throws Exception{
        try{
            return personaRepository.save(p);
        }catch(Exception e){
            if(e.getMessage().contains("Duplicate"))
            throw new Exception("Numero di telefono gia assegnato a una persona");
        }
        return null;
    }
    
    public Persona editContact(Persona p) throws Exception{
        Optional<Persona> personaToEdit = personaRepository.findById(p.getId());
        if(!personaToEdit.isPresent())
            throw new Exception("Persona da modificare non trovata in database");
        
        return addContact(p);
    }
    
    public void deleteContact(int id) throws Exception{
        Optional<Persona> personaToDelete = personaRepository.findById(id);
        if(!personaToDelete.isPresent())
            throw new Exception("Persona da eliminare non trovata in database");
        
        personaRepository.deleteById(id);
    }

}
