package dev.cesarzavaleta.pruebas_unitarias_s11.Services;

import dev.cesarzavaleta.pruebas_unitarias_s11.model.HistorialMedico;

import java.util.List;

public interface HistorialMedicoService {
    List<HistorialMedico> findAll();
    HistorialMedico findById(Long id);
    HistorialMedico save(HistorialMedico historialMedico);
    void deleteById(Long id);
}