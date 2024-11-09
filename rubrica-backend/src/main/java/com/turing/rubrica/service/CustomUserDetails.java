package com.turing.rubrica.service;

import com.turing.rubrica.entity.Utente;
import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class CustomUserDetails implements UserDetails {
    
    private Utente u;

    public CustomUserDetails(Utente u) {
        this.u = u;
    }
    
    @Override
    public String getUsername() {
        return u.getUsername();
    }

    @Override
    public String getPassword() {
        return u.getPassword();
    }
    

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

}
