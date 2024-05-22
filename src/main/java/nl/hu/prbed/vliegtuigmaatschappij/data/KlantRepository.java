package nl.hu.prbed.vliegtuigmaatschappij.data;


import nl.hu.prbed.vliegtuigmaatschappij.domain.Klant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KlantRepository extends JpaRepository<Klant, Long> {
    Optional<Klant> findById(Long id);
}
