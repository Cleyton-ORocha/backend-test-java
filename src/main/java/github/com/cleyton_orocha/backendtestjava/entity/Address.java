package github.com.cleyton_orocha.backendtestjava.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private Long id;
    private String UF;
    private String city;
    private String neighborhood;
    private String street;
    private Establishment establishment;
}
