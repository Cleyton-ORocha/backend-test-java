package github.com.cleyton_orocha.backendtestjava.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import github.com.cleyton_orocha.backendtestjava.config.TestMethods;
import github.com.cleyton_orocha.backendtestjava.entity.Establishment;
import lombok.RequiredArgsConstructor;

@ActiveProfiles("test")
@RequiredArgsConstructor
@ExtendWith(SpringExtension.class)
public class EstablishmentServiceTest {

    TestMethods testMethods;
    
    

    @Test
    @DisplayName("must register an establishment")
    public void registerEstablishmentTest() {
        Establishment estb = testMethods.createEstablishment();
    }
}
