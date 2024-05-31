package dev.cesarzavaleta.pruebas_unitarias_s11.controllers;

import dev.cesarzavaleta.pruebas_unitarias_s11.Services.HistorialMedicoService;
import dev.cesarzavaleta.pruebas_unitarias_s11.model.HistorialMedico;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/historiales-medicos")
public class HistorialMedicoController {
    private final HistorialMedicoService historialMedicoService;

    public HistorialMedicoController(HistorialMedicoService historialMedicoService) {
        this.historialMedicoService = historialMedicoService;
    }

    @GetMapping
    public List<HistorialMedico> getAllHistorialesMedicos() {
        return historialMedicoService.findAll();
    }

    @GetMapping("/{id}")
    public HistorialMedico getHistorialMedicoById(@PathVariable Long id) {
        return historialMedicoService.findById(id);
    }
}