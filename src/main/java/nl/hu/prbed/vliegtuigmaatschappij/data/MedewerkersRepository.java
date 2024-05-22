package nl.hu.prbed.vliegtuigmaatschappij.data;

import nl.hu.prbed.vliegtuigmaatschappij.domain.Medewerker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedewerkersRepository extends JpaRepository<Medewerker, Long> {
    Optional<Medewerker> findById(Long id);
}
