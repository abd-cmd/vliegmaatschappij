package nl.hu.prbed.vliegtuigmaatschappij.application;

import nl.hu.prbed.vliegtuigmaatschappij.data.VluchtRepository;
import nl.hu.prbed.vliegtuigmaatschappij.domain.Boeking;
import nl.hu.prbed.vliegtuigmaatschappij.domain.Luchthaven;
import nl.hu.prbed.vliegtuigmaatschappij.domain.Vliegtuig;
import nl.hu.prbed.vliegtuigmaatschappij.domain.Vlucht;
import nl.hu.prbed.vliegtuigmaatschappij.domain.exceptions.VluchtNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class VluchtService {

    private final VluchtRepository vluchtRepository;
    private final LuchthavenService luchthavenService;
    //private final BoekingService boekingService;
//    private final LuchthavensRepository luchthavensRepository;
//    private final VliegtuigService vliegtuigService;


    public VluchtService(VluchtRepository vluchtRepository, LuchthavenService luchthavenService) {
        this.vluchtRepository = vluchtRepository;
        this.luchthavenService = luchthavenService;
        // this.boekingService = boekingService;
    }


    public Vlucht save(Long id, LocalDateTime vertrekdatum, Luchthaven beginhaven, Luchthaven eindhaven, int stoelenBezet, double tijdsduur, List<Boeking> boekingList, Vliegtuig vliegtuig) {
//        Luchthaven beginHaven = this.luchthavenService.findById(beginhavenid);
//        Luchthaven eindHaven = this.luchthavenService.findById(eindhavenid);

        Vlucht vlucht = new Vlucht(id, vertrekdatum, beginhaven, eindhaven, stoelenBezet, tijdsduur, boekingList, vliegtuig);
        vlucht.setBeginLuchthaven(beginhaven);
        vlucht.setEindHaven(eindhaven);
        return this.vluchtRepository.save(vlucht);
    }

    public Vlucht update(Long id, LocalDateTime vertrekdatum, Long beginhavenid, Long eindhavenid, int stoelenBezet, double tijdsduur, List<Boeking> boekList, Vliegtuig vliegtuig) {
        Vlucht vlucht = findById(id);
        Luchthaven beginHaven = this.luchthavenService.findById(beginhavenid);
        Luchthaven eindHaven = this.luchthavenService.findById(eindhavenid);
//        Luchthaven beginHaven = this.luchthavensRepository.findById(beginhavenid)
//                .orElseThrow(() -> new LuchthavenNotFoundException());
//
//        Luchthaven eindHaven = this.luchthavensRepository.findById(eindhavenid)
//                .orElseThrow(() -> new LuchthavenNotFoundException());


        if (vlucht != null) {
            vlucht.setVertrekdatum(vertrekdatum);
            vlucht.setBeginLuchthaven(beginHaven);
            vlucht.setEindHaven(eindHaven);
            vlucht.setStoelenbezet(stoelenBezet);
            vlucht.setTijdsduur(tijdsduur);
            vlucht.setBoekingList(boekList);
            vlucht.setVliegtuig(vliegtuig);
            return this.vluchtRepository.save(vlucht);
        }
        return null;
    }

    public Vlucht delete(Long id) {
        Vlucht vlucht = findById(id);
        if (vluchtRepository.findById(id).isEmpty()) {
            return null;
        }
        this.vluchtRepository.delete(vlucht);
        return vlucht;
    }

    public Vlucht findById(Long id) {
        Vlucht vlucht = this.vluchtRepository.findById(id)
                .orElseThrow(() -> new VluchtNotFoundException());
        return vlucht;
    }

    public List<Vlucht> getVluchten() {
        List<Vlucht> vluchten = new ArrayList<>();
        vluchtRepository.findAll().forEach(vliegtuig -> vluchten.add(vliegtuig));
        return vluchten;
    }


}
