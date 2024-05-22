package nl.hu.prbed.vliegtuigmaatschappij.data;

import nl.hu.prbed.vliegtuigmaatschappij.domain.Vliegtuig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VliegtuigRepository extends JpaRepository<Vliegtuig, Long> {
    Optional<Vliegtuig> findById(Long id);
}
