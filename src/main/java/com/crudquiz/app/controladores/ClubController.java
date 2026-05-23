package com.crudquiz.app.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.crudquiz.app.entidades.*;
import com.crudquiz.app.repositorio.*;

@Controller
@RequestMapping("/clubs")
public class ClubController {
	@Autowired private ClubRepository clubRepo;
    @Autowired private EntrenadorRepository entrenadorRepo;
    @Autowired private JugadorRepository jugadorRepo;
    @Autowired private AsociacionRepository asociacionRepo;
    @Autowired private CompeticionRepository competicionRepo;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("clubs", clubRepo.findAll());
        return "club/list";
    }

    @GetMapping("/nuevo")
    public String formNuevo(Model model) {
        cargarSelects(model);
        return "club/form";
    }

    @GetMapping("/{id}/editar")
    public String formEditar(@PathVariable Long id, Model model) {
        clubRepo.findById(id).ifPresent(c -> model.addAttribute("club", c));
        cargarSelects(model);
        return "club/form";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam(required = false) Long id,
                          @RequestParam String nombre,
                          @RequestParam Long entrenadorId,
                          @RequestParam Long asociacionId,
                          @RequestParam(required = false) List<Long> jugadoresIds,
                          @RequestParam(required = false) List<Long> competicionesIds) {
        Club club = (id != null) ? clubRepo.findById(id).orElse(new Club()) : new Club();
        club.setNombre(nombre);
        entrenadorRepo.findById(entrenadorId).ifPresent(club::setEntrenador);
        asociacionRepo.findById(asociacionId).ifPresent(club::setAsociacion);
        club.setJugadores(jugadoresIds != null ? jugadorRepo.findAllById(jugadoresIds) : List.of());
        club.setCompeticiones(competicionesIds != null ? competicionRepo.findAllById(competicionesIds) : List.of());
        clubRepo.save(club);
        return "redirect:/clubs";
    }

    @GetMapping("/{id}/detalle")
    public String detalle(@PathVariable Long id, Model model) {
        clubRepo.findById(id).ifPresent(c -> model.addAttribute("club", c));
        return "club/detalle";
    }

    @GetMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        clubRepo.deleteById(id);
        return "redirect:/clubs";
    }

    private void cargarSelects(Model model) {
        model.addAttribute("entrenadores", entrenadorRepo.findAll());
        model.addAttribute("jugadores", jugadorRepo.findAll());
        model.addAttribute("asociaciones", asociacionRepo.findAll());
        model.addAttribute("competiciones", competicionRepo.findAll());
    }
}
