package dev.cesarzavaleta.pruebas_unitarias_s11.controllers;

import dev.cesarzavaleta.pruebas_unitarias_s11.Services.HistorialMedicoService;
import dev.cesarzavaleta.pruebas_unitarias_s11.model.HistorialMedico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class HistorialMedicoControllerTest {
    private HistorialMedicoController historialMedicoController;
    private HistorialMedicoService historialMedicoService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        historialMedicoService = mock(HistorialMedicoService.class);
        historialMedicoController = new HistorialMedicoController(historialMedicoService);
        mockMvc = MockMvcBuilders.standaloneSetup(historialMedicoController).build();
    }

    @Test
    void getAllHistorialesMedicos() throws Exception {
        HistorialMedico historial1 = new HistorialMedico();
        historial1.setIdHistorial(1L);
        historial1.setDiagnostico("Diagnóstico 1");

        HistorialMedico historial2 = new HistorialMedico();
        historial2.setIdHistorial(2L);
        historial2.setDiagnostico("Diagnóstico 2");

        List<HistorialMedico> historiales = Arrays.asList(historial1, historial2);

        when(historialMedicoService.findAll()).thenReturn(historiales);
        mockMvc.perform(get("/historiales-medicos"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].idHistorial", is(1)))
                .andExpect(jsonPath("$[0].diagnostico", is("Diagnóstico 1")));
    }
}