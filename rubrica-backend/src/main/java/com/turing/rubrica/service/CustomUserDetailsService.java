package com.turing.rubrica.service;

import com.turing.rubrica.entity.Utente;
import com.turing.rubrica.repo.UtenteRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService{
    
    @Autowired
    private UtenteRepository utenteRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Utente> userTmp = utenteRepo.findByUsername(username);
        if(userTmp.isEmpty()){
            throw new UsernameNotFoundException("Utente non trovato");
        }
        
        return new CustomUserDetails(userTmp.get());
    }
    

}
