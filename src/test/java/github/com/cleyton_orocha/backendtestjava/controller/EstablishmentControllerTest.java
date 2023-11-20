package github.com.cleyton_orocha.backendtestjava.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import github.com.cleyton_orocha.backendtestjava.DTO.EstablishmentDTO;
import github.com.cleyton_orocha.backendtestjava.entity.Address;
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

        EstablishmentDTO estbSaved = EstablishmentDTO.builder()
                .id(1L)
                .name("mockName")
                .cnpj("mockCNPj")
                .address(createAddressEstablishmentDTO())
                .phones(createPhonesEstablishmentDTO())
                .motorcycleSpots(10)
                .carSpots(10)
                .build();

        estbSaved.getAddress().setId(1L);
        estbSaved.getPhones().setId(1L);

        BDDMockito.given(establishmentService.save(Mockito.any(EstablishmentDTO.class))).willReturn(estbSaved);

        String json = new ObjectMapper().writeValueAsString(estb);

        mvc.perform(getRequest(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                // .andExpect(MockMvcResultMatchers.jsonPath("id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value(estb.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("cnpj").value(estb.getCnpj()))
                .andExpect(MockMvcResultMatchers.jsonPath("motorcycleSpots").value(estb.getMotorcycleSpots()))
                .andExpect(MockMvcResultMatchers.jsonPath("carSpots").value(estb.getCarSpots()))

                .andExpect(MockMvcResultMatchers.jsonPath("address.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("address.uf").value(estb.getAddress().getUF()))
                .andExpect(MockMvcResultMatchers.jsonPath("address.city").value(estb.getAddress().getCity()))
                .andExpect(MockMvcResultMatchers.jsonPath("address.street").value(estb.getAddress().getStreet()))

                .andExpect(MockMvcResultMatchers.jsonPath("phones.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("phones.phone1").value(estb.getPhones().getPhone1()))
                .andExpect(MockMvcResultMatchers.jsonPath("phones.phone2").value(estb.getPhones().getPhone2()));



        // .andExpect(MockMvcResultMatchers.jsonPath("phones").value(estb.getPhones()))

    }

    private MockHttpServletRequestBuilder getRequest(String json) {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(ESTB_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);
        return request;
    }

    private EstablishmentDTO createEstablishmentDTO() {
        return EstablishmentDTO.builder()
                .name("mockName")
                .cnpj("mockCNPj")
                .address(createAddressEstablishmentDTO())
                .phones(createPhonesEstablishmentDTO())
                .motorcycleSpots(10)
                .carSpots(10)
                .build();
    }

    private Address createAddressEstablishmentDTO() {
        return Address.builder()
                .UF("MG")
                .city("Vespasiano")
                .street("Serra do Contorno")
                .build();
    }

    private Phones createPhonesEstablishmentDTO() {
        return Phones.builder()
                .phone1("31996709760")
                .phone2("31998288542")
                .build();
    }
}
