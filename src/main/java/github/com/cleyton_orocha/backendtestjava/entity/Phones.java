package github.com.cleyton_orocha.backendtestjava.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Phones {
    private Long id;
    private String phone1;
    private String phone2;
}
