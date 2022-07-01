package ftn.projekat.dostava.controller;

import ftn.projekat.dostava.dto.ArtikalDto;
import ftn.projekat.dostava.dto.RestoranDto;
import ftn.projekat.dostava.dto.RestoranPrikazDto;
import ftn.projekat.dostava.entity.*;
import ftn.projekat.dostava.service.ArtikalService;
import ftn.projekat.dostava.service.MenadzerService;
import ftn.projekat.dostava.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RestoranRestController {
    @Autowired
    private RestoranService restoranService;

    @Autowired
    private ArtikalService artikalService;

    @Autowired
    private MenadzerService menadzerService;


    @GetMapping("/api/restorani/{id}")
    @ResponseBody
    public ResponseEntity<RestoranPrikazDto> izborRestorana(@PathVariable(name = "id") Long id){

        Restoran restoran = restoranService.findOneById(id);

        /*List<Komentar> listaKomentara = komentarServicefindAll(restoran);

        List<Komentar> komentari = new ArrayList<>();

        for(Komentar komentar : listaKomentara){
            komentari.add(komentar);
        }*/

        RestoranPrikazDto prikazDto = new RestoranPrikazDto(restoran);

        return ResponseEntity.ok(prikazDto);
    }

    @GetMapping("api/svi-restorani")
    public ResponseEntity<List<RestoranDto>> getRestorani(){
        List<Restoran> restorani = this.restoranService.findAll();

        List<RestoranDto> dtos = new ArrayList<>();
        for(Restoran r : restorani){
            RestoranDto dto = new RestoranDto(r);
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);

    }
    }

