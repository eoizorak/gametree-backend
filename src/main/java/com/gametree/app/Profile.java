package com.gametree.app;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity 
public class Profile {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    private String nome;
    private String bio;

    @Column(columnDefinition = "TEXT")
    private String avatarUrl;

    private String statusServidor;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
  
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Link> links = new ArrayList<>();

    public Profile() {}

    public Profile(String nome, String bio, String avatarUrl, String statusServidor) {
        this.nome = nome;
        this.bio = bio;
        this.avatarUrl = avatarUrl;
        this.statusServidor = statusServidor;
    }


    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getBio() { return bio; }
    public String getAvatarUrl() { return avatarUrl; }
    public String getStatusServidor() { return statusServidor; }
    public List<Link> getLinks() { return links; }
    public User getUser() { return user; }


    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setBio(String bio) { this.bio = bio; }
    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }
    public void setStatusServidor(String statusServidor) { this.statusServidor = statusServidor; }
    public void setLinks(List<Link> links) { this.links = links; }
    public void setUser(User user) { this.user = user; }
}