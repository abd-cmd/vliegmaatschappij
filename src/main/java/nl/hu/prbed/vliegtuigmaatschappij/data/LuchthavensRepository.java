package nl.hu.prbed.vliegtuigmaatschappij.data;

import nl.hu.prbed.vliegtuigmaatschappij.domain.Luchthaven;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LuchthavensRepository extends JpaRepository<Luchthaven, Long> {
    Optional<Luchthaven> findById(Long id);

    Optional<Luchthaven> findLuchthavenByLuchthavennaam(String name);

}
