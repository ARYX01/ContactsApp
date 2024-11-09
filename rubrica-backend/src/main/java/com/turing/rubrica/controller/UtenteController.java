package com.turing.rubrica.controller;

import com.turing.rubrica.config.CustomPasswordEncoder;
import com.turing.rubrica.entity.Utente;
import com.turing.rubrica.repo.UtenteRepository;
import com.turing.rubrica.service.CustomUserDetailsService;
import com.turing.rubrica.service.JWTService;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UtenteController {
    
    @Autowired
    private UtenteRepository utenteRepo;
    
    @Autowired
    private AuthenticationManager authManager;
    
    @Autowired
    CustomUserDetailsService userDetailsService;
    
    @Autowired
    private JWTService jwtService;
    
    //private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    
    // TESTING PURPOUSE 
//    private final CustomPasswordEncoder encoder = new CustomPasswordEncoder();
//    
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody Utente u) throws Exception{
//        Optional<Utente> user = utenteRepo.findByUsername(u.getUsername());
//        if(user.isPresent()){
//            Map<String, String> response = Map.of("message", "Utente con nome '"+u.getUsername()+"' già registrato");
//            return ResponseEntity.badRequest().body(response);
//        }
//        
//        u.setPassword(encoder.encode(u.getPassword()));
//        
//        return ResponseEntity.ok(utenteRepo.save(u));
//    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Utente u){
        Map<String, String> response = new HashMap<>();
        
        try{
            userDetailsService.loadUserByUsername(u.getUsername());
        }catch(UsernameNotFoundException e){
            response.put("message", "L'utente '"+u.getUsername()+"' non esiste");
            return ResponseEntity.badRequest().body(response);
        }
        
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(u.getUsername(), u.getPassword()));
        if(auth.isAuthenticated())
            response.put("message", "Success");
        else
            response.put("message", "Fail");

        String token = jwtService.generateToken(u.getUsername());
        response.put("token", token);
        
        
        return ResponseEntity.ok(response);
    }

}
