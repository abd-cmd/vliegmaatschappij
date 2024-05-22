package nl.hu.prbed.vliegtuigmaatschappij.presentation.controller;

import nl.hu.prbed.vliegtuigmaatschappij.application.LuchthavenService;
import nl.hu.prbed.vliegtuigmaatschappij.application.VliegtuigService;
import nl.hu.prbed.vliegtuigmaatschappij.application.VluchtService;
import nl.hu.prbed.vliegtuigmaatschappij.domain.Luchthaven;
import nl.hu.prbed.vliegtuigmaatschappij.domain.Vliegtuig;
import nl.hu.prbed.vliegtuigmaatschappij.domain.Vlucht;
import nl.hu.prbed.vliegtuigmaatschappij.domain.exceptions.BoekingNotCorrectException;
import nl.hu.prbed.vliegtuigmaatschappij.domain.exceptions.BoekingNotFoundException;
import nl.hu.prbed.vliegtuigmaatschappij.domain.exceptions.VliegtuigNotFoundException;
import nl.hu.prbed.vliegtuigmaatschappij.presentation.dto.VluchtDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/vliegmaatschappij")
public class VluchtController {

    private final LuchthavenService luchthavenService;
    private final VliegtuigService vliegtuigService;
    private final VluchtService vluchtService;

    public VluchtController(VluchtService vluchtService, LuchthavenService luchthavenService, VliegtuigService vliegtuigService) {
        this.vluchtService = vluchtService;
        this.luchthavenService = luchthavenService;
        this.vliegtuigService = vliegtuigService;
    }

    @PostMapping("/vlucht")
    public Vlucht save(@Validated @RequestBody VluchtDto vluchtDto) {
        try {
            Vlucht vlucht = new Vlucht();
            Luchthaven beginHaven = this.luchthavenService.findById(vluchtDto.beginhavenid);
            Luchthaven eindHaven = this.luchthavenService.findById(vluchtDto.eindhavenid);
            Vliegtuig vliegtuig = this.vliegtuigService.findById(vluchtDto.vliegtuigid);

            return this.vluchtService.save(vlucht.getId(), vluchtDto.vertrekdatum
                    , beginHaven, eindHaven,
                    vluchtDto.stoelenbezet,
                    vluchtDto.tijdsduur, vluchtDto.boekingList, vliegtuig);

        } catch (BoekingNotCorrectException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, exception.getMessage());
        }
    }

    @PutMapping("/vlucht/{id}/{beginhavenid}/{eindhavenid}")
    public Vlucht update(@PathVariable("id") Long vluchtId, @PathVariable("beginhavenid") Long beginhavenid, @PathVariable("eindhavenid") Long eindhavenid, @Validated @RequestBody VluchtDto vluchtDto) {
        try {
            Vliegtuig vliegtuig = this.vliegtuigService.findById(vluchtDto.vliegtuigid);
            return this.vluchtService.update(vluchtId, vluchtDto.vertrekdatum
                    , beginhavenid, eindhavenid,
                    vluchtDto.stoelenbezet,
                    vluchtDto.tijdsduur, vluchtDto.boekingList, vliegtuig);

        } catch (BoekingNotCorrectException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @DeleteMapping("/vlucht/{id}")
    public Vlucht delete(@PathVariable("id") Long vluchtid) {
        try {
            return this.vluchtService.delete(vluchtid);

        } catch (VliegtuigNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.OK, exception.getMessage());
        }
    }

    @GetMapping("/vlucht/{id}")
    public Vlucht findById(@PathVariable("id") Long vluchtid) {
        try {
            return vluchtService.findById(vluchtid);
        } catch (BoekingNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping("/vluchten")
    public List<Vlucht> getAlleVluchten() {
        try {
            return this.vluchtService.getVluchten();
        } catch (BoekingNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }
}