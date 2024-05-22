package nl.hu.prbed.vliegtuigmaatschappij.presentation.controller;

import nl.hu.prbed.vliegtuigmaatschappij.application.VliegtuigService;
import nl.hu.prbed.vliegtuigmaatschappij.domain.Vliegtuig;
import nl.hu.prbed.vliegtuigmaatschappij.domain.VliegtuigType;
import nl.hu.prbed.vliegtuigmaatschappij.domain.exceptions.VliegtuigNotFoundException;
import nl.hu.prbed.vliegtuigmaatschappij.presentation.dto.VliegtuigTypeDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/vliegmaatschappij")
public class VliegtuigController {

    private final VliegtuigService service;

    public VliegtuigController(VliegtuigService service) {
        this.service = service;
    }

    @PostMapping("/vliegtuig")
    public Vliegtuig save(@Validated @RequestBody VliegtuigTypeDto type) {
        try {
            Vliegtuig vliegtuig = new Vliegtuig();

            VliegtuigType vliegtuigType = new VliegtuigType(type.type,
                    type.firstclassstoelenmax,
                    type.businessstoelenmax,
                    type.economyclassstoelenmax,
                    type.prijsfirstclass,
                    type.prijsbusinessclass,
                    type.prijseconomyclass
            );

            vliegtuig.setVliegtuigType(vliegtuigType);

            return this.service.save(vliegtuig.getId(), vliegtuig.getVlucht(), vliegtuig.getVliegtuigType());

        } catch (VliegtuigNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, exception.getMessage());
        }
    }

    @PutMapping("/vliegtuig/{id}")
    public Vliegtuig update(@PathVariable("id") Long vliegtuigId, @Validated @RequestBody VliegtuigTypeDto type) {
        try {
            VliegtuigType vliegtuigType1 = new VliegtuigType(type.type,
                    type.firstclassstoelenmax,
                    type.businessstoelenmax,
                    type.economyclassstoelenmax,
                    type.prijsfirstclass,
                    type.prijsbusinessclass,
                    type.prijseconomyclass
            );
            return service.update(vliegtuigId, vliegtuigType1);

        } catch (VliegtuigNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @DeleteMapping("/vliegtuig/{id}")
    public Vliegtuig delete(@PathVariable("id") Long vliegtuigid) {
        try {
            return service.delete(vliegtuigid);

        } catch (VliegtuigNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.OK, exception.getMessage());
        }
    }

    @GetMapping("/vliegtuig/{id}")
    public Vliegtuig findById(@PathVariable("id") Long vliegtuigId) {
        try {
            return service.findById(vliegtuigId);
        } catch (VliegtuigNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping("/vliegtuigen")
    public List<Vliegtuig> getAllVliegtuigen() {
        try {
            return service.getAllVliegtuigen();
        } catch (VliegtuigNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }
}
