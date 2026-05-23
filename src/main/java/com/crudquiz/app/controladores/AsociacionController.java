package com.crudquiz.app.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.crudquiz.app.entidades.Asociacion;
import com.crudquiz.app.repositorio.AsociacionRepository;

@Controller
@RequestMapping("/asociaciones")
public class AsociacionController {
	@Autowired private AsociacionRepository repo;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("asociaciones", repo.findAll());
        return "asociacion/list";
    }

    @GetMapping("/nuevo")
    public String formNuevo(Model model) {
        model.addAttribute("asociacion", new Asociacion());
        return "asociacion/form";
    }

    @GetMapping("/{id}/editar")
    public String formEditar(@PathVariable Long id, Model model) {
        repo.findById(id).ifPresent(a -> model.addAttribute("asociacion", a));
        return "asociacion/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Asociacion asociacion) {
        repo.save(asociacion);
        return "redirect:/asociaciones";
    }

    @GetMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/asociaciones";
    }
}
