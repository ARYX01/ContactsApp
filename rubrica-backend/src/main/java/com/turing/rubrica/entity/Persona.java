package com.turing.rubrica.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "persone")
public class Persona {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String cognome;
    private String indirizzo;
    @Column(length=18, unique=true)
    private String telefono;
    private Integer eta=null;

    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", indirizzo=" + indirizzo + ", telefono=" + telefono + ", eta=" + eta + '}';
    }

    public Persona(String nome, String cognome, String indirizzo, String telefono, Integer eta) {
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.telefono = telefono;
        if(eta != null) this.eta = eta;
    }
    
    public Persona() {}

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getEta() {
        return eta;
    }
    public void setEta(Integer eta) {
        this.eta = eta;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    
}
