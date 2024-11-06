package com.turing.rubrica;

import com.turing.rubrica.entity.Persona;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ServiceLogic {
    
    private final List<Persona> contacts;

    public ServiceLogic() {
        contacts = new ArrayList<>();
        
        //TEST
        Persona personaTest1 = new Persona("Pippo", "Baudo", "via fritto misto", "342534", 99);
        personaTest1.setId(0);
        contacts.add(personaTest1);
        Persona personaTest2 = new Persona("Test", "Rest", "via fritto misto", "0000000", 1);
        personaTest2.setId(1);
        contacts.add(personaTest2);
    }

    public List<Persona> getContacts() {
        return contacts;
    }
    
    public void addContact(String name, String surname, String address, String phoneNo, Integer age) {
        int lastId = 0;
        if(!contacts.isEmpty())
            lastId = contacts.get(contacts.size()-1).getId();
        Persona contact = new Persona(name, surname, address, phoneNo, age);
        contact.setId(lastId+1);
        contacts.add(contact);
    }
    
    public void editContact(int id, String name, String surname, String address, String phoneNo, Integer age) {
        Optional<Persona> contactToEditFind = contacts.stream().filter(x -> x.getId()==id).findFirst();
        if(!contactToEditFind.isPresent())
            return;
            
        Persona contactToEdit = contactToEditFind.get();
        contactToEdit.setNome(name);
        contactToEdit.setCognome(surname);
        contactToEdit.setIndirizzo(address);
        contactToEdit.setTelefono(phoneNo);
        if(age!=null) contactToEdit.setEta(age);
    }
   
    
    public void deleteContact(int id){
        Optional<Persona> contactToDelete = contacts.stream().filter(x -> x.getId()==id).findFirst();
        if(contactToDelete.isPresent())
            contacts.remove(contactToDelete.get());
    }
    
    
}
