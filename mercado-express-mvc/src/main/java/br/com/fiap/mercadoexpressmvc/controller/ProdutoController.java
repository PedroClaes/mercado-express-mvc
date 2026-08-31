package br.com.fiap.mercadoexpressmvc.controller;

import br.com.fiap.mercadoexpressmvc.model.Produto;
import br.com.fiap.mercadoexpressmvc.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

/**
 * Controller MVC. Diferente do @RestController da Parte I, aqui os metodos
 * retornam o NOME de uma view (template Thymeleaf), que o Spring renderiza
 * como HTML e devolve pronto pro navegador.
 */
@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository repository;

    public ProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }

    // Tela de listagem (rota PUBLICA)
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("produtos", repository.findAll());
        return "produtos/lista";
    }

    // Formulario de cadastro (rota PRIVADA)
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("produto", new Produto());
        model.addAttribute("modoEdicao", false);
        return "produtos/form";
    }

    // Formulario de edicao (rota PRIVADA)
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
        model.addAttribute("produto", produto);
        model.addAttribute("modoEdicao", true);
        return "produtos/form";
    }

    // Salvar (cria ou atualiza, conforme o id vier preenchido) - rota PRIVADA
    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("produto") Produto produto,
                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicao", produto.getId() != null);
            return "produtos/form"; // volta pro form mostrando os erros
        }
        repository.save(produto);
        return "redirect:/produtos";
    }

    // Excluir (rota PRIVADA)
    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/produtos";
    }
}
