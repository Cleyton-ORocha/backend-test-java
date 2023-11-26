package github.com.cleyton_orocha.backendtestjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import github.com.cleyton_orocha.backendtestjava.DTO.EstablishmentDTO;
import github.com.cleyton_orocha.backendtestjava.service.EstablishmentService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/estb")
public class EstablishmentController {

    @Autowired
    private EstablishmentService establishmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstablishmentDTO create(@RequestBody @Valid EstablishmentDTO estb) {
        return establishmentService.save(estb);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EstablishmentDTO getById(@PathVariable Long id){
        return establishmentService.getReferenceById(id);
    }

}
