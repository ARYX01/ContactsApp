package com.turing.rubrica;

import com.turing.rubrica.entity.Persona;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;


public class ServiceLogic {
    
    private static final String LOCAL_SAVE_FILE_NAME = "informazioni.txt";
    private final List<Persona> contacts;

    public ServiceLogic() {
        contacts = new ArrayList<>();
        
        //TEST
//        Persona personaTest1 = new Persona("Pippo", "Baudo", "via fritto misto", "342534", 99);
//        personaTest1.setId(0);
//        contacts.add(personaTest1);
//        Persona personaTest2 = new Persona("Test", "Rest", "via tombolo", "0000000", 1);
//        personaTest2.setId(1);
//        contacts.add(personaTest2);
        
        //TODO check connection to db
        try {
            loadContactsFromFile();
        } catch (IOException ex) {
            Logger.getLogger(ServiceLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
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

        saveContactsToFile();
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

        saveContactsToFile();
    }
   
    
    public void deleteContact(int id){
        Optional<Persona> contactToDelete = contacts.stream().filter(x -> x.getId()==id).findFirst();
        if(!contactToDelete.isPresent())
            return;
        
        saveContactsToFile();
    }
    
    private void loadContactsFromFile() throws IOException{
        contacts.clear();
        Stream<String> contactLines = Files.lines(Paths.get(LOCAL_SAVE_FILE_NAME));
        contactLines.forEach(line -> {
            String[] fields = line.split(";");
            Integer tmpAge = fields[5].equalsIgnoreCase("null") ? null : Integer.valueOf(fields[5]);
            Persona p = new Persona(fields[1], fields[2], fields[3], fields[4], tmpAge);
            p.setId(Integer.parseInt(fields[0]));
            contacts.add(p);
        });
    }
    private void saveContactsToFile(){
        System.out.println("SAVING TO FILE");
        try {
            PrintWriter writer = new PrintWriter(LOCAL_SAVE_FILE_NAME);
            contacts.forEach(p -> {
                System.out.println("saving: "+p.getNome());
                writer.printf("%d;%s;%s;%s;%s;%d", p.getId(), p.getNome(), p.getCognome(), p.getIndirizzo(), p.getTelefono(), p.getEta());
                writer.println();
            });
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(ServiceLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    private void appendContactToFile(Persona p) throws IOException{
//        System.out.println("APPENDING TO FILE");
//        PrintWriter writer = new PrintWriter(LOCAL_SAVE_FILE_NAME);
//        String contactLine = String.format("%d;%s;%s;%s;%s;%d", p.getId(), p.getNome(), p.getCognome(), p.getIndirizzo(), p.getTelefono(), p.getEta());
//        System.out.println(contactLine);
//        writer.append(contactLine);
//        writer.close();
//    }
    
    
}
