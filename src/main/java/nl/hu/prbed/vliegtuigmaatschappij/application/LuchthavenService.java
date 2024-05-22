package nl.hu.prbed.vliegtuigmaatschappij.application;

import nl.hu.prbed.vliegtuigmaatschappij.data.LuchthavensRepository;
import nl.hu.prbed.vliegtuigmaatschappij.domain.Luchthaven;
import nl.hu.prbed.vliegtuigmaatschappij.domain.Vlucht;
import nl.hu.prbed.vliegtuigmaatschappij.domain.exceptions.LuchthavenNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class LuchthavenService {

    private final LuchthavensRepository luchthavensRepository;

    public LuchthavenService(LuchthavensRepository luchthavensRepository) {
        this.luchthavensRepository = luchthavensRepository;
    }

    public Luchthaven save(Long id, String luchthavennaam, String stad, String land, String code, String lat, String lon, List<Vlucht> vluchtList) {
        Luchthaven luchthaven = new Luchthaven(id, luchthavennaam, stad, land, code, lat, lon, vluchtList);
        return this.luchthavensRepository.save(luchthaven);
    }

    public Luchthaven update(Long id, String luchthavennaam, String stad, String land, String code, String lat, String lon, List<Vlucht> vluchtList) {
        Luchthaven luchthaven = findById(id);
        if (luchthaven != null) {
            luchthaven.setLuchthavennaam(luchthavennaam);
            luchthaven.setStad(stad);
            luchthaven.setLand(land);
            luchthaven.setCode(code);
            luchthaven.setLat(lat);
            luchthaven.setLon(lon);
            luchthaven.setVluchtList(vluchtList);
            return this.luchthavensRepository.save(luchthaven);
        }
        return null;
    }

    public Luchthaven delete(Long id) {
        Luchthaven luchthaven = findById(id);
        if (luchthavensRepository.findById(id).isEmpty()) {
            return null;
        }
        this.luchthavensRepository.delete(luchthaven);
        return luchthaven;
    }

    public Luchthaven findById(Long id) {
        Luchthaven luchthaven = this.luchthavensRepository.findById(id)
                .orElseThrow(() -> new LuchthavenNotFoundException());
        return luchthaven;
    }

    public List<Luchthaven> getLuchthavens() {
        List<Luchthaven> luchthavens = new ArrayList<>();
        this.luchthavensRepository.findAll().forEach(luchthaven -> luchthavens.add(luchthaven));
        return luchthavens;
    }

    public Luchthaven findByName(String name) {
        Luchthaven luchthaven = this.luchthavensRepository.findLuchthavenByLuchthavennaam(name)
                .orElseThrow(() -> new LuchthavenNotFoundException());
        return luchthaven;
    }
}
