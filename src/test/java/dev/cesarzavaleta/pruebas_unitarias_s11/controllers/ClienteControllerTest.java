package dev.cesarzavaleta.pruebas_unitarias_s11.controllers;

import dev.cesarzavaleta.pruebas_unitarias_s11.Services.ClienteService;
import dev.cesarzavaleta.pruebas_unitarias_s11.model.Cliente;
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

class ClienteControllerTest {
    private ClienteController clienteController;
    private ClienteService clienteService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        clienteService = mock(ClienteService.class);
        clienteController = new ClienteController(clienteService);
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
    }

    @Test
    void getAllClientes() throws Exception {
        Cliente cliente1 = new Cliente();
        cliente1.setIdCliente(1L);
        cliente1.setNombre("Juan");
        cliente1.setApellido("Perez");

        Cliente cliente2 = new Cliente();
        cliente2.setIdCliente(2L);
        cliente2.setNombre("Ana");
        cliente2.setApellido("Gomez");

        List<Cliente> clientes = Arrays.asList(cliente1, cliente2);

        when(clienteService.findAll()).thenReturn(clientes);
        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].idCliente", is(1)))
                .andExpect(jsonPath("$[0].nombre", is("Juan")));
    }
}