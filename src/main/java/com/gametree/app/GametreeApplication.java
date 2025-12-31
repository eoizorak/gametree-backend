package com.gametree.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList; // Import necessário para criar listas
import java.util.List;      // Import necessário para criar listas

@SpringBootApplication
public class GametreeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GametreeApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ProfileRepository repository) {
        return (args) -> {
            
            // Só cria se o banco estiver vazio
            if (repository.count() == 0) {
                
                // 1. Cria o Perfil Principal com seus dados fixos
                Profile meuPerfil = new Profile(
                    "Matheus - Links Infinitos",
                    "Agora com botões dinâmicos vindos do Banco!",
                    "https://ui-avatars.com/api/?name=Matheus+Dev&background=00ff88&color=000",
                    "🟢 Sistema Multi-Links Online",
                    "https://discord.gg/4FmkrCAPjZ",       // Teu Discord
                    "https://youtube.com/@eoizorak",       // Teu YouTube
                    "https://instagram.com/dsaugustomatheus" // Teu Instagram
                );

                // 2. Cria a lista de Links Extras (A NOVA TABELA)
                List<Link> linksExtras = new ArrayList<>();
                
                // Adicionando links de exemplo (eles vão aparecer embaixo dos fixos)
                // Note que passamos 'meuPerfil' no final para o banco saber de quem é o link
                linksExtras.add(new Link("🚀 Meu GitHub", "https://github.com/eoizorak", meuPerfil));
                linksExtras.add(new Link("💼 LinkedIn", "https://linkedin.com", meuPerfil));
                linksExtras.add(new Link("☕ Me pague um café", "https://ko-fi.com", meuPerfil));

                // 3. Conecta a lista ao perfil
                meuPerfil.setLinks(linksExtras);

                // 4. Salvar (O JPA é esperto e salva o Perfil + Links Extras tudo junto)
                repository.save(meuPerfil);
                
                System.out.println("✅ Perfil inicial criado com LINKS EXTRAS!");
            }
        };
    }
}