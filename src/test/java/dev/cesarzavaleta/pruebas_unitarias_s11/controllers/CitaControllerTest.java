package dev.cesarzavaleta.pruebas_unitarias_s11.controllers;

import dev.cesarzavaleta.pruebas_unitarias_s11.Services.CitaService;
import dev.cesarzavaleta.pruebas_unitarias_s11.model.Cita;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
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

class CitaControllerTest {
    private CitaController citaController;
    private CitaService citaService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        citaService = mock(CitaService.class);
        citaController = new CitaController(citaService);
        mockMvc = MockMvcBuilders.standaloneSetup(citaController).build();
    }

    @Test
    void getAllCitas() throws Exception {
        Cita cita1 = new Cita();
        cita1.setIdCita(1L);
        cita1.setFechaHora(LocalDateTime.now());
        cita1.setMotivoConsulta("Consulta general");

        Cita cita2 = new Cita();
        cita2.setIdCita(2L);
        cita2.setFechaHora(LocalDateTime.now().plusDays(1));
        cita2.setMotivoConsulta("Vacunación");

        List<Cita> citas = Arrays.asList(cita1, cita2);

        when(citaService.findAll()).thenReturn(citas);
        mockMvc.perform(get("/citas"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].idCita", is(1)))
                .andExpect(jsonPath("$[0].motivoConsulta", is("Consulta general")));
    }
}