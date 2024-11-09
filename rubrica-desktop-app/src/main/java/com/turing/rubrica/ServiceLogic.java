package com.turing.rubrica;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turing.rubrica.entity.Persona;
import com.turing.rubrica.entity.Utente;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

enum AppMode {
  LOCAL,
  WEB
}

public class ServiceLogic {
    private static final Logger logger = Logger.getLogger(ServiceLogic.class.getName());
    
    private static final String LOCAL_SAVE_FILE_NAME = "informazioni.txt";
    private final List<Persona> contacts;
    
    private String apiBasePath;
    private String apiLoginPath;
    private RestTemplate restTemplate = new RestTemplateBuilder().build();
    
    private AppMode appMode;

    public ServiceLogic() {
        contacts = new ArrayList<>();
        readAppProperties();
        
        //TEST
//        Persona personaTest1 = new Persona("Pippo", "Baudo", "via fritto misto", "342534", 99);
//        personaTest1.setId(0);
//        contacts.add(personaTest1);
//        Persona personaTest2 = new Persona("Test", "Rest", "via tombolo", "0000000", 1);
//        personaTest2.setId(1);
//        contacts.add(personaTest2);
    }
    
    public List<Persona> getContacts() {
        return contacts;
    }
    
    public AppMode getAppMode() {
        return appMode;
    }
    
    public void login(String username, String password) throws Exception{
        System.out.println("apiBasePath: "+apiBasePath);
        System.out.println("apiLoginPath: "+apiLoginPath);
        
        Utente utenteDaAutenticare = new Utente(username, password);
        ObjectMapper mapper = new ObjectMapper();
        try{
            // LOGIN API
            ResponseEntity<String> response = restTemplate.postForEntity(apiLoginPath, utenteDaAutenticare, String.class);
            Map<String, Object> map = mapper.readValue(response.getBody(), Map.class);
            String jwtToken = map.get("token").toString();
            restTemplate = new RestTemplateBuilder()
                    .defaultHeader("Authorization", "Bearer "+jwtToken)
                    .build();
        } catch(org.springframework.web.client.ResourceAccessException e){
            logger.log(Level.SEVERE, "Errore, impossibile connettersi");
            throw new Exception("Impossibile stabilire una connessione con il server");
        } catch(RestClientResponseException e){
            // Deserialize api request error
            if(e.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(403)))
                throw new Exception("Accesso non autorizzato, credenziali errate");
            Map<String, Object> map = mapper.readValue(e.getResponseBodyAsString(), Map.class);
            throw new Exception(map.get("message").toString());
        }
        
        // Check connection to backend
        loadContactsFromBackend();
        appMode = AppMode.WEB;
    }
    
    public void loadLocalMode(){
        try {
            loadContactsFromFile();
            appMode = AppMode.LOCAL;
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
    
    public void addContact(String name, String surname, String address, String phoneNo, Integer age) throws Exception {
        Persona contactToAdd = new Persona(name, surname, address, phoneNo, age);

        if(appMode==AppMode.LOCAL){
            int lastId = 0;
            if(!contacts.isEmpty())
                lastId = contacts.get(contacts.size()-1).getId();
            contactToAdd.setId(lastId+1);
            contacts.add(contactToAdd);
            saveContactsToFile();
        }
        if(appMode==AppMode.WEB){
            Persona savedContact = saveContactToWeb(contactToAdd, "/addContact");
            contacts.add(savedContact);
        }

    }
    
    public void editContact(int id, String name, String surname, String address, String phoneNo, Integer age) throws Exception {
        Optional<Persona> contactToEditFind = contacts.stream().filter(x -> x.getId()==id).findFirst();
        if(!contactToEditFind.isPresent())
            return;
            
        Persona contactToEdit = contactToEditFind.get();
        contactToEdit.setNome(name);
        contactToEdit.setCognome(surname);
        contactToEdit.setIndirizzo(address);
        contactToEdit.setTelefono(phoneNo);
        if(age!=null) contactToEdit.setEta(age);

        if(appMode==AppMode.LOCAL)
            saveContactsToFile();
        if(appMode==AppMode.WEB)
            saveContactToWeb(contactToEdit, "/editContact");
    }
   
    
    public void deleteContact(int id) throws Exception{
        Optional<Persona> contactToDelete = contacts.stream().filter(x -> x.getId()==id).findFirst();
        if(!contactToDelete.isPresent())
            return;
        contacts.remove(contactToDelete.get());
        
        if(appMode==AppMode.LOCAL)
            saveContactsToFile();
        if(appMode==AppMode.WEB)
            deleteContactToWeb(id);
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
    private void saveContactsToFile() throws Exception{
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
            logger.log(Level.SEVERE, null, ex);
            throw new Exception("File di salvataggio inacessibile o corrotto");
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
    
    private void readAppProperties() {
        try (InputStream input = ServiceLogic.class.getClassLoader().getResourceAsStream("app.config")) {

            Properties prop = new Properties();

            if (input == null) {
                logger.log(Level.SEVERE, "Errore, impossibile trovare il file di configurazione: application.properties");
                return;
            }
            prop.load(input);
            String apiPathTemp = prop.getProperty("backend.apiPath");
            if(apiPathTemp==null || apiPathTemp.isBlank())
                logger.log(Level.SEVERE, "Errore, proprietà 'backend.apiPath' non asseganta o inesistente");
            apiBasePath = apiPathTemp;
            String apiLoginPathTemp = prop.getProperty("backend.loginPath");
            if(apiLoginPathTemp==null || apiLoginPathTemp.isBlank())
                logger.log(Level.SEVERE, "Errore, proprietà 'backend.loginPath' non asseganta o inesistente");
            apiLoginPath = apiLoginPathTemp;
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadContactsFromBackend() throws Exception{
        contacts.clear();
        try{
            // Make GET request
            ResponseEntity<Persona[]> response = restTemplate.getForEntity(apiBasePath+"/getContacts", Persona[].class);

            // Deserialize the response to a Java object
            Persona[] contactsFromBackend = response.getBody();
            Collections.addAll(contacts, contactsFromBackend);
        } catch(org.springframework.web.client.ResourceAccessException e){
            logger.log(Level.SEVERE, "Errore, impossibile connettersi");
            throw new Exception("Impossibile stabilire una connessione con il server");
        } catch(org.springframework.web.client.HttpStatusCodeException e){
            logger.log(Level.SEVERE, "Errore, impossibile connettersi, accesso non autorizzato");
            throw new Exception("Accesso non autorizzato o credenziali scadute");
        }
    }
    
    private void deleteContactToWeb(int id) throws Exception{
        try{
            // Make DELETE request
            restTemplate.delete(apiBasePath+"/deleteContact/"+id);
        } catch(org.springframework.web.client.ResourceAccessException e){
            logger.log(Level.SEVERE, "Errore, impossibile connettersi");
            throw new Exception("Impossibile stabilire una connessione con il server");
        } catch(RestClientResponseException e){
            // Deserialize api request error
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(e.getResponseBodyAsString(), Map.class);
            throw new Exception(map.get("message").toString());
        }
    }
    
    private Persona saveContactToWeb(Persona p, String endpoint) throws Exception{
        try{
            // Make POST request
            Persona response = restTemplate.postForObject(apiBasePath+endpoint, p, Persona.class);
            if(response==null)
                throw new Exception("Persona non salvata");
            return response;
        } catch(org.springframework.web.client.ResourceAccessException e){
            logger.log(Level.SEVERE, "Errore, impossibile connettersi");
            throw new Exception("Impossibile stabilire una connessione con il server");
        } catch(RestClientResponseException e){
            // Deserialize api request error
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(e.getResponseBodyAsString(), Map.class);
            throw new Exception(map.get("message").toString());
        }
    }
//    private void editContactToWeb(Persona p) throws Exception{
//        try{
//            // Make POST request
//            Persona response = restTemplate.postForObject(apiBasePath+"/editContact", p, Persona.class);
//            if(response==null)
//                throw new Exception("Persona non salvata");
//        } catch(org.springframework.web.client.ResourceAccessException e){
//            logger.log(Level.SEVERE, "Errore, impossibile connettersi");
//            throw new Exception("Impossibile stabilire una connessione con il server");
//        } catch(RestClientResponseException e){
//            // Deserialize api request error
//            ObjectMapper mapper = new ObjectMapper();
//            Map<String, Object> map = mapper.readValue(e.getResponseBodyAsString(), Map.class);
//            throw new Exception(map.get("message").toString());
//        }
//    }
    
}
