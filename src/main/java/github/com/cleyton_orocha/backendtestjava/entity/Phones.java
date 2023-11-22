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
public class Phones {
    private Long id;
    private String phone1;
    private String phone2;

    @OneToOne
    @JoinColumn(name = "establishment_id")
    private Establishment establishment;
}
