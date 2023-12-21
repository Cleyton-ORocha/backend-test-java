package github.com.cleyton_orocha.backendtestjava.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import github.com.cleyton_orocha.backendtestjava.DTO.EstablishmentDTO;
import github.com.cleyton_orocha.backendtestjava.entity.Establishment;
import github.com.cleyton_orocha.backendtestjava.exception.BusinessException;
import github.com.cleyton_orocha.backendtestjava.repository.EstablishmentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstablishmentService {

    private final EstablishmentRepository establishmentRepository ;

    public EstablishmentDTO save(EstablishmentDTO estb) {
        if (establishmentRepository.existsByCnpj(estb.getCnpj())) {
            throw new BusinessException("Cpnj is already registered, please contact the administration");
        }

        Establishment estbSaved = establishmentRepository.save(EstablishmentDTO.toOrigin(estb));
        return EstablishmentDTO.toDTO(estbSaved);
    }

    public EstablishmentDTO getReferenceById(Long id) {
        return EstablishmentDTO.toDTO(establishmentRepository.getReferenceById(id));
    }

    public Optional<Establishment> findById(Long id) {
        return establishmentRepository.findById(id);
    }

}
