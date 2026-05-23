package com.crudquiz.app.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.crudquiz.app.entidades.Entrenador;
import com.crudquiz.app.repositorio.EntrenadorRepository;

@Controller
@RequestMapping("/entrenadores")
public class EntrenadorController {
	@Autowired private EntrenadorRepository repo;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("entrenadores", repo.findAll());
        return "entrenador/list";
    }

    @GetMapping("/nuevo")
    public String formNuevo(Model model) {
        model.addAttribute("entrenador", new Entrenador());
        return "entrenador/form";
    }

    @GetMapping("/{id}/editar")
    public String formEditar(@PathVariable Long id, Model model) {
        repo.findById(id).ifPresent(e -> model.addAttribute("entrenador", e));
        return "entrenador/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Entrenador entrenador) {
        repo.save(entrenador);
        return "redirect:/entrenadores";
    }

    @GetMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/entrenadores";
    }
}
