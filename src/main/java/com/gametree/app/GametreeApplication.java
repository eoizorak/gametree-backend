package com.gametree.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class GametreeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GametreeApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ProfileRepository repository) {
        return (args) -> {
            
            if (repository.count() == 0) {
                

                Profile meuPerfil = new Profile(
                    "Matheus - Full Links",
                    "Todos os links agora são dinâmicos e opcionais!",
                    "https://ui-avatars.com/api/?name=Matheus+Link&background=00ff88&color=000",
                    "🟢 Sistema Unificado Online"
                );


                List<Link> listaLinks = new ArrayList<>();
                

                listaLinks.add(new Link("🎮 Discord", "https://discord.gg/4FmkrCAPjZ", meuPerfil));
                listaLinks.add(new Link("📺 YouTube", "https://youtube.com/@eoizorak", meuPerfil));
                listaLinks.add(new Link("📷 Instagram", "https://instagram.com/dsaugustomatheus", meuPerfil));
                

                listaLinks.add(new Link("🚀 GitHub", "https://github.com/eoizorak", meuPerfil));
                listaLinks.add(new Link("💼 LinkedIn", "https://linkedin.com", meuPerfil));

                meuPerfil.setLinks(listaLinks);

                repository.save(meuPerfil);
                System.out.println("✅ Perfil unificado criado!");
            }
        };
    }
}