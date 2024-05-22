package nl.hu.prbed.vliegtuigmaatschappij.data;

import nl.hu.prbed.vliegtuigmaatschappij.domain.Luchthaven;
import nl.hu.prbed.vliegtuigmaatschappij.domain.Vlucht;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VluchtRepository extends JpaRepository<Vlucht, Long> {
    Optional<Vlucht> findById(Long id);

    List<Vlucht> findVluchtByBeginLuchthavenAndEindHavenAndVertrekdatum(Luchthaven beginhaven, Luchthaven eindhaven, LocalDateTime vertrekdatum);
}