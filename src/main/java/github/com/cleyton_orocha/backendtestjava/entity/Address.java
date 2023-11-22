package github.com.cleyton_orocha.backendtestjava.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private Long id;
    private String UF;
    private String city;
    private String neighborhood;
    private String street;

    @OneToOne
    @JoinColumn(name = "establishment_id")
    private Establishment establishment;
}
