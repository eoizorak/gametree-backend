package com.gametree.app;

public record Profile(
    String nome,
    String bio,
    String avatarUrl,
    String statusServidor,
    String discordUrl,
    String youtubeUrl,
    String instagramUrl
) {}