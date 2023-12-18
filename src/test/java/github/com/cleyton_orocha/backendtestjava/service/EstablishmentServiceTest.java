package github.com.cleyton_orocha.backendtestjava.service;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import github.com.cleyton_orocha.backendtestjava.config.TestMethods;
import github.com.cleyton_orocha.backendtestjava.entity.Establishment;
import github.com.cleyton_orocha.backendtestjava.repository.EstablishmentRepository;

@ActiveProfiles("test")

// @ExtendWith(SpringExtension.class)
public class EstablishmentServiceTest {
    
    @Autowired
    EstablishmentService establishmentService;

    @MockBean
    EstablishmentRepository establishmentRepository;

    @Test
    @DisplayName("must register an establishment with id")
    public void registerEstablishmentTestById() {
        Long id = 1L;

        Establishment establishment = TestMethods.createEstablishment();
        establishment.setId(id);

        Mockito.when(establishmentRepository.findById(establishment.getId())).thenReturn(Optional.empty());

        Optional<Establishment> foundEstablishment = establishmentService.findById(id);

        Assertions.assertThat(foundEstablishment.isPresent()).isTrue();
        Assertions.assertThat(foundEstablishment.get().getId()).isEqualTo(establishment.getId());
        Assertions.assertThat(foundEstablishment.get().getName()).isEqualTo(establishment.getName());
        Assertions.assertThat(foundEstablishment.get().getCnpj()).isEqualTo(establishment.getCnpj());
        Assertions.assertThat(foundEstablishment.get().getAddress()).isEqualTo(establishment.getAddress());
        Assertions.assertThat(foundEstablishment.get().getPhones()).isEqualTo(establishment.getPhones());
        Assertions.assertThat(foundEstablishment.get().getMotorcycleSpots()).isEqualTo(establishment.getMotorcycleSpots());
        Assertions.assertThat(foundEstablishment.get().getCarSpots()).isEqualTo(establishment.getCarSpots());
    }
}
