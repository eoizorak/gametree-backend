package com.gametree.app;

import jakarta.persistence.*;

@Entity
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String url;

    @ManyToOne
    @JoinColumn(name = "profile_id") 
    private Profile profile;

    public Link() {}

    public Link(String titulo, String url, Profile profile) {
        this.titulo = titulo;
        this.url = url;
        this.profile = profile;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }


    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    
    public Profile getProfile() { return profile; }
    public void setProfile(Profile profile) { this.profile = profile; }
}