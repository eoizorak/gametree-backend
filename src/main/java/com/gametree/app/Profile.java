package com.gametree.app;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // Isso diz ao Java: "Crie uma tabela chamada 'profile' no banco"
public class Profile {

    @Id // Esta é a chave primária (ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O banco gera o ID (1, 2, 3...) sozinho
    private Long id;

    private String nome;
    private String bio;
    private String avatarUrl;
    private String statusServidor;
    private String discordUrl;
    private String youtubeUrl;
    private String instagramUrl;

    // O JPA exige um construtor vazio
    public Profile() {}

    // Construtor para criarmos fácil
    public Profile(String nome, String bio, String avatarUrl, String statusServidor, String discordUrl, String youtubeUrl, String instagramUrl) {
        this.nome = nome;
        this.bio = bio;
        this.avatarUrl = avatarUrl;
        this.statusServidor = statusServidor;
        this.discordUrl = discordUrl;
        this.youtubeUrl = youtubeUrl;
        this.instagramUrl = instagramUrl;
    }

    // --- Getters (Para o HTML conseguir ler os dados) ---
    public String getNome() { return nome; }
    public String getBio() { return bio; }
    public String getAvatarUrl() { return avatarUrl; }
    public String getStatusServidor() { return statusServidor; }
    public String getDiscordUrl() { return discordUrl; }
    public String getYoutubeUrl() { return youtubeUrl; }
    public String getInstagramUrl() { return instagramUrl; }

// --- Setters (Necessários para editar os dados) ---
    public void setId(Long id) { this.id = id; }
    // Adicione este método que estava faltando:
    public Long getId() {
        return id;
    }
    public void setNome(String nome) { this.nome = nome; }
    public void setBio(String bio) { this.bio = bio; }
    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }
    public void setStatusServidor(String statusServidor) { this.statusServidor = statusServidor; }
    public void setDiscordUrl(String discordUrl) { this.discordUrl = discordUrl; }
    public void setYoutubeUrl(String youtubeUrl) { this.youtubeUrl = youtubeUrl; }
    public void setInstagramUrl(String instagramUrl) { this.instagramUrl = instagramUrl; }
}