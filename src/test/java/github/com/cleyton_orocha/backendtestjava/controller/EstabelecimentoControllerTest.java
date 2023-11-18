package github.com.cleyton_orocha.backendtestjava.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;

@WebMvcTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EstabelecimentoControllerTest {

    @Test
    @DisplayName("Must register an establishment")
    public void registerEstablishmentTest() throws Exception{

    }
    
}
