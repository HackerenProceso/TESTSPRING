package dev.cesarzavaleta.pruebas_unitarias_s11.controllers;

import dev.cesarzavaleta.pruebas_unitarias_s11.Services.MascotaService;
import dev.cesarzavaleta.pruebas_unitarias_s11.model.Mascota;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class MascotaControllerTest {
    private MascotaController mascotaController;
    private MascotaService mascotaService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mascotaService = mock(MascotaService.class);
        mascotaController = new MascotaController(mascotaService);
        mockMvc = MockMvcBuilders.standaloneSetup(mascotaController).build();
    }

    @Test
    void getAllMascotas() throws Exception {
        Mascota mascota1 = new Mascota();
        mascota1.setIdMascota(1L);
        mascota1.setNombre("Lucas");

        Mascota mascota2 = new Mascota();
        mascota2.setIdMascota(2L);
        mascota2.setNombre("Luna");

        List<Mascota> mascotas = Arrays.asList(mascota1, mascota2);

        when(mascotaService.findAll()).thenReturn(mascotas);
        mockMvc.perform(get("/mascotas"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].idMascota", is(2)))
                .andExpect(jsonPath("$[1].nombre", is("Luna")));

    }

    @Test
    void getMascotaById() throws Exception {
        Mascota mascota1 = new Mascota();
        mascota1.setIdMascota(1L);
        mascota1.setNombre("Lucas");

        Mascota mascota2 = new Mascota();
        mascota2.setIdMascota(2L);
        mascota2.setNombre("Luna");

        when(mascotaService.findById(1L)).thenReturn(mascota1);

        mockMvc.perform(get("/mascotas/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(jsonPath("$.idMascota", is(1)))
                .andExpect(jsonPath("$.nombre", is("Lucas")));
    }

    @Test
    void deleteMascotaById() throws Exception {
        Mascota mascota1 = new Mascota();
        mascota1.setIdMascota(1L);
        mascota1.setNombre("Lucas");

        Mascota mascota2 = new Mascota();
        mascota2.setIdMascota(2L);
        mascota2.setNombre("Luna");

        List<Mascota> mascotas = Arrays.asList(mascota1, mascota2);

        when(mascotaService.findAll()).thenReturn(mascotas);

        mockMvc.perform(delete("/mascotas/{id}", 2L))
                .andExpect(status().isNoContent());

        verify(mascotaService, times(1)).deleteById(2L);
    }

}