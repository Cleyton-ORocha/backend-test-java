package github.com.cleyton_orocha.backendtestjava.service;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import github.com.cleyton_orocha.backendtestjava.DTO.EstablishmentDTO;
import github.com.cleyton_orocha.backendtestjava.config.TestMethods;
import github.com.cleyton_orocha.backendtestjava.entity.Establishment;
import github.com.cleyton_orocha.backendtestjava.exception.BusinessException;
import github.com.cleyton_orocha.backendtestjava.repository.EstablishmentRepository;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class EstablishmentServiceTest {

    EstablishmentService establishmentService;

    @MockBean
    EstablishmentRepository establishmentRepository;
    
    @BeforeEach
    public void setup() {
        this.establishmentService = new EstablishmentService(establishmentRepository);
    }

    @Test
    @DisplayName("must register an establishment with id")
    public void registerEstablishmentTestById() {
        Long id = 1L;

        Establishment establishment = TestMethods.createEstablishment();
        
        establishment.setId(id);

        Mockito.when(establishmentRepository.findById(id)).thenReturn(Optional.of(establishment));
        
        Optional<Establishment> foundEstablishment = establishmentService.findById(id);

        Assertions.assertThat(foundEstablishment.isPresent()).isTrue();
        Assertions.assertThat(foundEstablishment.get().getId()).isEqualTo(establishment.getId());
        Assertions.assertThat(foundEstablishment.get().getName()).isEqualTo(establishment.getName());
        Assertions.assertThat(foundEstablishment.get().getCnpj()).isEqualTo(establishment.getCnpj());
        Assertions.assertThat(foundEstablishment.get().getAddress()).isEqualTo(establishment.getAddress());
        Assertions.assertThat(foundEstablishment.get().getPhones()).isEqualTo(establishment.getPhones());
        Assertions.assertThat(foundEstablishment.get().getMotorcycleSpots())
                .isEqualTo(establishment.getMotorcycleSpots());
        Assertions.assertThat(foundEstablishment.get().getCarSpots()).isEqualTo(establishment.getCarSpots());
    }

    @Test
    @DisplayName("must throw a business error when the cnpj already exists")
    public void mustThrowABusinessExceptionWhenTheDataAlreadyExists() {
    
        EstablishmentDTO estb = TestMethods.createEstablishmentDTO();

        Mockito.when(establishmentRepository.existsByCnpj(Mockito.anyString())).thenReturn(true);

        Throwable exception = Assertions.catchThrowable(() ->  establishmentService.save(estb));

        Assertions.assertThat(exception).isInstanceOf(BusinessException.class)
            .hasMessage("Cpnj is already registered, please contact the administration");
        
        Mockito.verify(establishmentRepository, Mockito.never()).save(EstablishmentDTO.toOrigin(estb));

    }

}
