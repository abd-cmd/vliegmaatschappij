package nl.hu.prbed.vliegtuigmaatschappij.data;

import nl.hu.prbed.vliegtuigmaatschappij.domain.Boeking;
import nl.hu.prbed.vliegtuigmaatschappij.domain.Vlucht;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoekingRepository extends JpaRepository<Boeking, Long> {
    Optional<Boeking> findById(Long id);

    List<Boeking> findByVlucht(Vlucht vlucht);
}