package com.turing.rubrica.controller;
import com.turing.rubrica.entity.Persona;
import com.turing.rubrica.service.RubricaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class RubricaController {
    
    @Autowired
    private RubricaService service;
    
    @GetMapping("/getContacts")
    public List<Persona> getContacts() {
        return service.getAllContacts();
    }
    
    @PostMapping("/addContact")
    public Persona addContact(@RequestBody Persona p) throws Exception {
        return service.addContact(p);
    }
    
    @PostMapping("/editContact")
    public Persona editContact(@RequestBody Persona p) throws Exception {
        return service.editContact(p);
    }
   
    @DeleteMapping("/deleteContact/{id}")
    public void deleteContact(@PathVariable int id) throws Exception{
        System.out.println("ID:"+ id);
        service.deleteContact(id);
    }

}
