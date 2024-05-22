package nl.hu.prbed.vliegtuigmaatschappij.application;

import nl.hu.prbed.vliegtuigmaatschappij.data.VliegtuigRepository;
import nl.hu.prbed.vliegtuigmaatschappij.domain.Vliegtuig;
import nl.hu.prbed.vliegtuigmaatschappij.domain.VliegtuigType;
import nl.hu.prbed.vliegtuigmaatschappij.domain.Vlucht;
import nl.hu.prbed.vliegtuigmaatschappij.domain.exceptions.VliegtuigNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class VliegtuigService {
    private final VliegtuigRepository vliegtuigRepository;

    public VliegtuigService(VliegtuigRepository vliegtuigRepository) {
        this.vliegtuigRepository = vliegtuigRepository;
    }


    public Vliegtuig save(Long id, List<Vlucht> list, VliegtuigType type) {
        Vliegtuig vliegtuig = new Vliegtuig(id, list, type);
        return this.vliegtuigRepository.save(vliegtuig);
    }

    public Vliegtuig update(Long id, VliegtuigType vliegtuigType) {
        Vliegtuig vliegtuig1 = findById(id);
        if (vliegtuig1 != null) {
            vliegtuig1.setVliegtuigType(vliegtuigType);
            return this.vliegtuigRepository.save(vliegtuig1);
        }
        return null;
    }

    public Vliegtuig delete(Long id) {
        Vliegtuig vliegtuig = findById(id);
        if (vliegtuigRepository.findById(id).isEmpty()) {
            return null;
        }
        this.vliegtuigRepository.delete(vliegtuig);
        return vliegtuig;
    }

    public Vliegtuig findById(Long id) {
        Vliegtuig vliegtuig = this.vliegtuigRepository.findById(id)
                .orElseThrow(() -> new VliegtuigNotFoundException());
        return vliegtuig;
    }

    public List<Vliegtuig> getAllVliegtuigen() {
        List<Vliegtuig> vliegtuigList = new ArrayList<>();
        vliegtuigRepository.findAll().forEach(vliegtuig -> vliegtuigList.add(vliegtuig));
        return vliegtuigList;
    }
}
