package br.com.fiap.mercadoexpressmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Rotas auxiliares: raiz do site e pagina de login.
 */
@Controller
public class HomeController {

    // Redireciona a raiz para a listagem
    @GetMapping("/")
    public String home() {
        return "redirect:/produtos";
    }

    // Exibe a pagina de login customizada
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
