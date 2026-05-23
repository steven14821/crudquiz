package com.crudquiz.app.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.crudquiz.app.repositorio.*;

@Controller
public class HomeController {
	
	@Autowired private ClubRepository clubRepo;
    @Autowired private EntrenadorRepository entrenadorRepo;
    @Autowired private JugadorRepository jugadorRepo;
    @Autowired private AsociacionRepository asociacionRepo;
    @Autowired private CompeticionRepository competicionRepo;

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("totalClubes", clubRepo.findAll().size());
        model.addAttribute("totalEntrenadores", entrenadorRepo.findAll().size());
        model.addAttribute("totalJugadores", jugadorRepo.findAll().size());
        model.addAttribute("totalAsociaciones", asociacionRepo.findAll().size());
        model.addAttribute("totalCompeticiones", competicionRepo.findAll().size());
        return "index";
    }
}
