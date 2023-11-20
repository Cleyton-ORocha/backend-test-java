package github.com.cleyton_orocha.backendtestjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import github.com.cleyton_orocha.backendtestjava.DTO.EstablishmentDTO;
import github.com.cleyton_orocha.backendtestjava.entity.Establishment;
import github.com.cleyton_orocha.backendtestjava.repository.EstablishmentRepository;

@Service
public class EstablishmentService {

    @Autowired
    private EstablishmentRepository establishmentRepository ;

    public EstablishmentDTO save(EstablishmentDTO estb) {
        Establishment estbSaved = establishmentRepository.save(EstablishmentDTO.toOrigin(estb));
        return EstablishmentDTO.toDTO(estbSaved);
    }

}
