package com.crudquiz.app.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.crudquiz.app.entidades.Jugador;
import com.crudquiz.app.repositorio.JugadorRepository;

@Controller
@RequestMapping("/jugadores")
public class JugadorController {
	@Autowired private JugadorRepository repo;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("jugadores", repo.findAll());
        return "jugador/list";
    }

    @GetMapping("/nuevo")
    public String formNuevo(Model model) {
        model.addAttribute("jugador", new Jugador());
        return "jugador/form";
    }

    @GetMapping("/{id}/editar")
    public String formEditar(@PathVariable Long id, Model model) {
        repo.findById(id).ifPresent(j -> model.addAttribute("jugador", j));
        return "jugador/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Jugador jugador) {
        repo.save(jugador);
        return "redirect:/jugadores";
    }

    @GetMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/jugadores";
    }
}
