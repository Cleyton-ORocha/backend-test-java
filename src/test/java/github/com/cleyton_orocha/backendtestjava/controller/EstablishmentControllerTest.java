package github.com.cleyton_orocha.backendtestjava.controller;

import org.hamcrest.Matchers;
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
import github.com.cleyton_orocha.backendtestjava.exception.BusinessException;
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
    @DisplayName("must register an establishment")
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

        mvc.perform(getPostRequest(json))
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
                .andExpect(MockMvcResultMatchers.jsonPath("address.neighborhood")
                        .value(estb.getAddress().getNeighborhood()))
                .andExpect(MockMvcResultMatchers.jsonPath("address.street").value(estb.getAddress().getStreet()))

                .andExpect(MockMvcResultMatchers.jsonPath("phones.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("phones.phone1").value(estb.getPhones().getPhone1()))
                .andExpect(MockMvcResultMatchers.jsonPath("phones.phone2").value(estb.getPhones().getPhone2()));

    }

    @Test
    @DisplayName("a validation error should be thrown when there is not enough data to create the establishment")
    public void createInvalidEstablishmentTest() throws Exception {
        String json = new ObjectMapper().writeValueAsString(new EstablishmentDTO());

        mvc.perform(getPostRequest(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(6)));

    }

    @Test
    @DisplayName("should generate an error when a cpnj is persisted in the database")
    public void shouldGenerateAnErrorWhenACnpjIsPersistedInTheDatabase() throws Exception {
        String json = new ObjectMapper().writeValueAsString(createEstablishmentDTO());
        String errorMsg = "Cnpj is registered";

        BDDMockito.given(establishmentService.save(Mockito.any(EstablishmentDTO.class)))
                .willThrow(new BusinessException(errorMsg));

        mvc.perform(getPostRequest(json))
                .andExpect(MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("errors[0]").value(errorMsg));
    }

    @Test
    @DisplayName("should generate an error when motorcycle spots are smaller than one")
    public void shouldGenerateErrorWhenMotorcycleSpotsAreSmallerThanOne() throws Exception {
        EstablishmentDTO estb = createEstablishmentDTO();
        estb.setMotorcycleSpots(-1);
        String json = new ObjectMapper().writeValueAsString(estb);

        mvc.perform(getPostRequest(json))
                .andExpect(MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("errors[0]")
                        .value("Motorcycle spots must be greater than zero"));

    }

    @Test
    @DisplayName("should generate an error when cars spots are smaller than one")
    public void shouldGenerateErrorWhenCarSpotsAreSmallerThanOne() throws Exception {
        EstablishmentDTO estb = createEstablishmentDTO();
        estb.setCarSpots(-1);
        String json = new ObjectMapper().writeValueAsString(estb);

        mvc.perform(getPostRequest(json))
                .andExpect(MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("errors[0]")
                        .value("Car spots must be greater than zero"));
    }

    @Test
    @DisplayName("should return an information about the Establishment")
    public void shouldReturnInformationAboutEstablishment() throws Exception {
        Long id = 1L;
        EstablishmentDTO estb = createEstablishmentDTO();

        BDDMockito.given(establishmentService.getReferenceById(id)).willReturn(estb);

        estb.setId(id);

        String json = new ObjectMapper().writeValueAsString(estb);

        mvc.perform(getRequest(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").isNotEmpty());
    }

    private MockHttpServletRequestBuilder getRequest(String json) {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(ESTB_API.concat("/" + 1L))
                .accept(MediaType.APPLICATION_JSON);
        return request;
    }

    private MockHttpServletRequestBuilder getPostRequest(String json) {
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
                .neighborhood("Serra Dourada")
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
