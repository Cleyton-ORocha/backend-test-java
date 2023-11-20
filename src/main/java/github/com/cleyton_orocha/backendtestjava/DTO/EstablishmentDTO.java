package github.com.cleyton_orocha.backendtestjava.DTO;

import github.com.cleyton_orocha.backendtestjava.entity.Address;
import github.com.cleyton_orocha.backendtestjava.entity.Establishment;
import github.com.cleyton_orocha.backendtestjava.entity.Phones;
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
    private String name;
    private String cnpj;
    private Integer motorcycleSpots;
    private Integer carSpots;
    private Address address;
    private Phones phones;

    public static Establishment toOrigin(EstablishmentDTO estb) {
        return Establishment.builder()
                .id(estb.getId())
                .name(estb.getName())
                .cnpj(estb.getCnpj())
                .motorcycleSpots(estb.getMotorcycleSpots())
                .carSpots(estb.getCarSpots())
                // .address(estb.getAddress())
                // .phones(estb.getPhones())
                .build();
    }

    public static EstablishmentDTO toDTO(Establishment estb) {
        return EstablishmentDTO.builder()
                .id(estb.getId())
                .name(estb.getName())
                .cnpj(estb.getCnpj())
                .motorcycleSpots(estb.getMotorcycleSpots())
                .carSpots(estb.getCarSpots())
                // .address(estb.getAddress())
                // .phones(estb.getPhones())
                .build();
    }

}
