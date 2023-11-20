package github.com.cleyton_orocha.backendtestjava.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Establishment {
    private Long id;
    private String name;
    private String cnpj;
    private Integer motorcycleSpots;
    private Integer carSpots;
    private Address address;
    private Phones phones;
}
