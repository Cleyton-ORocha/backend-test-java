package github.com.cleyton_orocha.backendtestjava.DTO;

import github.com.cleyton_orocha.backendtestjava.entity.Address;
import github.com.cleyton_orocha.backendtestjava.entity.Phones;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstablishmentDTO{
    private Long id;
    private String name;
    private String CNPJ;
    private Integer motorcycleSpots;
    private Integer carSpots;
    private Address address;
    private Phones phones;

}
