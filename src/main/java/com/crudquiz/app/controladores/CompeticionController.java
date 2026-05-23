package com.crudquiz.app.controladores;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.crudquiz.app.entidades.Competicion;
import com.crudquiz.app.repositorio.CompeticionRepository;

@Controller
@RequestMapping("/competiciones")
public class CompeticionController {
	@Autowired private CompeticionRepository repo;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("competiciones", repo.findAll());
        return "competicion/list";
    }

    @GetMapping("/nuevo")
    public String formNuevo(Model model) {
        model.addAttribute("competicion", new Competicion());
        return "competicion/form";
    }

    @GetMapping("/{id}/editar")
    public String formEditar(@PathVariable Long id, Model model) {
        repo.findById(id).ifPresent(c -> model.addAttribute("competicion", c));
        return "competicion/form";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam(required = false) Long id,
                          @RequestParam String nombre,
                          @RequestParam Long montoPremio,
                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        Competicion c = (id != null) ? repo.findById(id).orElse(new Competicion()) : new Competicion();
        c.setNombre(nombre);
        c.setMontoPremio(montoPremio);
        c.setFechaInicio(fechaInicio);
        c.setFechaFin(fechaFin);
        repo.save(c);
        return "redirect:/competiciones";
    }

    @GetMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/competiciones";
    }
}
