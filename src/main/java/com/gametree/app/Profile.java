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
    private String avatarUrl;
    private String statusServidor;
    private String discordUrl;
    private String youtubeUrl;
    private String instagramUrl;


    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Link> links = new ArrayList<>();

    public Profile() {}


    public Profile(String nome, String bio, String avatarUrl, String statusServidor, String discordUrl, String youtubeUrl, String instagramUrl) {
        this.nome = nome;
        this.bio = bio;
        this.avatarUrl = avatarUrl;
        this.statusServidor = statusServidor;
        this.discordUrl = discordUrl;
        this.youtubeUrl = youtubeUrl;
        this.instagramUrl = instagramUrl;
    }

    // --- Getters ---
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getBio() { return bio; }
    public String getAvatarUrl() { return avatarUrl; }
    public String getStatusServidor() { return statusServidor; }
    public String getDiscordUrl() { return discordUrl; }
    public String getYoutubeUrl() { return youtubeUrl; }
    public String getInstagramUrl() { return instagramUrl; }
    

    public List<Link> getLinks() { return links; }


    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setBio(String bio) { this.bio = bio; }
    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }
    public void setStatusServidor(String statusServidor) { this.statusServidor = statusServidor; }
    public void setDiscordUrl(String discordUrl) { this.discordUrl = discordUrl; }
    public void setYoutubeUrl(String youtubeUrl) { this.youtubeUrl = youtubeUrl; }
    public void setInstagramUrl(String instagramUrl) { this.instagramUrl = instagramUrl; }


    public void setLinks(List<Link> links) { this.links = links; }
}