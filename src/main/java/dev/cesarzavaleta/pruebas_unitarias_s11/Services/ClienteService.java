package dev.cesarzavaleta.pruebas_unitarias_s11.Services;

import dev.cesarzavaleta.pruebas_unitarias_s11.model.Cliente;

import java.util.List;

public interface ClienteService {
    List<Cliente> findAll();
    Cliente findById(Long id);
    Cliente save(Cliente cliente);
    void deleteById(Long id);
}