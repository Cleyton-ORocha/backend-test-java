package github.com.cleyton_orocha.backendtestjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import github.com.cleyton_orocha.backendtestjava.entity.Establishment;

public interface EstablishmentRepository extends JpaRepository<Establishment, Long>{
    
}
