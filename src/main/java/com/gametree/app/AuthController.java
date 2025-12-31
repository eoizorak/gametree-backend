package com.gametree.app;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register/save")
    public String registrarUsuario(@RequestParam String username, @RequestParam String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            return "redirect:/register?error=exists";
        }

        User novoUser = new User(username, passwordEncoder.encode(password));
        
        Profile novoPerfil = new Profile(
            username, 
            "Bem-vindo ao GameTree!",
            "https://ui-avatars.com/api/?name=" + username + "&background=random",
            "🟢 Online"
        );
        
        novoPerfil.setUser(novoUser);
        novoUser.setProfile(novoPerfil);

        userRepository.save(novoUser); 

        return "redirect:/login?created=true";
    }
}