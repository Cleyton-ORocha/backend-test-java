package github.com.cleyton_orocha.backendtestjava.DTO;

import org.hibernate.validator.constraints.Range;

import github.com.cleyton_orocha.backendtestjava.entity.Address;
import github.com.cleyton_orocha.backendtestjava.entity.Establishment;
import github.com.cleyton_orocha.backendtestjava.entity.Phones;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstablishmentDTO {

    private Long id;

    @NotBlank(message = "The value canot be null or empty")
    private String name;

    @NotBlank(message = "The value canot be null or empty")
    private String cnpj;

    @Column(nullable = false)
    @NotNull(message = "The value canot be null or empty")
    @Range(min = 1, message = "Motorcycle spots must be greater than zero")
    private Integer motorcycleSpots;

    @Column(nullable = false)
    @NotNull(message = "The value canot be null or empty")
    @Range(min = 1, message = "Car spots must be greater than zero")
    private Integer carSpots;

    @NotNull(message = "The value canot be null")
    private Address address;

    @NotNull(message = "The value canot be null")
    private Phones phones;

    public static Establishment toOrigin(EstablishmentDTO estb) {
        return Establishment.builder()
                .id(estb.getId())
                .name(estb.getName())
                .cnpj(estb.getCnpj())
                .motorcycleSpots(estb.getMotorcycleSpots())
                .carSpots(estb.getCarSpots())
                .address(estb.getAddress())
                .phones(estb.getPhones())
                .build();
    }

    public static EstablishmentDTO toDTO(Establishment estb) {
        return EstablishmentDTO.builder()
                .id(estb.getId())
                .name(estb.getName())
                .cnpj(estb.getCnpj())
                .motorcycleSpots(estb.getMotorcycleSpots())
                .carSpots(estb.getCarSpots())
                .address(estb.getAddress())
                .phones(estb.getPhones())
                .build();
    }

}
