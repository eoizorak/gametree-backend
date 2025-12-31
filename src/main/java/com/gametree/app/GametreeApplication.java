package com.gametree.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GametreeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GametreeApplication.class, args);
    }

    // Isso roda toda vez que o site inicia
    @Bean
    public CommandLineRunner demo(ProfileRepository repository) {
        return (args) -> {
            // Verifica se o banco está vazio
            if (repository.count() == 0) {
                // Cria o seu perfil inicial e SALVA NO BANCO DE DADOS NEON!
                Profile meuPerfil = new Profile(
                    "Matheus - Admin DB",
                    "Agora estes dados vêm direto do PostgreSQL!",
                    "https://ui-avatars.com/api/?name=Admin+DB&background=random",
                    "🟢 Sistema Database Online",
                    "https://discord.gg/4FmkrCAPjZ",
                    "https://youtube.com/@eoizorak",
                    "https://instagram.com/dsaugustomatheus"
                );
                repository.save(meuPerfil);
                System.out.println("✅ Perfil inicial criado no Banco de Dados!");
            }
        };
    }
}