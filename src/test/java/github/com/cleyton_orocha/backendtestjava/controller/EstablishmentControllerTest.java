package github.com.cleyton_orocha.backendtestjava.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import github.com.cleyton_orocha.backendtestjava.DTO.EstablishmentDTO;
import github.com.cleyton_orocha.backendtestjava.entity.Address;
import github.com.cleyton_orocha.backendtestjava.entity.Establishment;
import github.com.cleyton_orocha.backendtestjava.entity.Phones;
import github.com.cleyton_orocha.backendtestjava.service.EstablishmentService;

@WebMvcTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EstablishmentControllerTest {

    public static String ESTB_API = "/api/estb";

    @MockBean
    EstablishmentService establishmentService;

    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("Must register an establishment")
    public void registerEstablishmentTest() throws Exception {

        EstablishmentDTO estb = createEstablishmentDTO();

        Establishment estbSaved = Establishment.builder()
                .id(1L)
                .name("mockName")
                .CNPJ("mockCNPj")
                .address(new Address())
                .phones(new Phones())
                .motorcycleSpots(10)
                .carSpots(10)
                .build();

        BDDMockito.given(establishmentService.save(Mockito.any(Establishment.class))).willReturn(estbSaved);

    }

    private EstablishmentDTO createEstablishmentDTO() {
        return EstablishmentDTO.builder()
                .name("mockName")
                .CNPJ("mockCNPj")
                .address(new Address())
                .phones(new Phones())
                .motorcycleSpots(10)
                .carSpots(10)
                .build();
    }
}
