package ftn.projekat.dostava.controller;


import ftn.projekat.dostava.dto.ArtikalPrikazDto;
import ftn.projekat.dostava.dto.KomentarPrikazDto;
import ftn.projekat.dostava.dto.PretragaDto;
import ftn.projekat.dostava.dto.RestoranPrikazDto;
import ftn.projekat.dostava.entity.*;
import ftn.projekat.dostava.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class RestoranRestController {

    @Autowired
    private RestoranService restoranService;




    @GetMapping("/api/restorani")
    public ResponseEntity<List<PretragaDto>> getRestorani(HttpSession session){

        Korisnik uk = (Korisnik) session.getAttribute("korisnik");

        if(uk == null) {
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        }

        List<Restoran> restorani = this.restoranService.findAll();

        List<PretragaDto> dtos = new ArrayList<>();
        for(Restoran restoran : restorani){
            PretragaDto dto = new PretragaDto(restoran);
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);


    }

    @GetMapping("/api/pretraga/naziv/{naziv}")
    public ResponseEntity<RestoranPrikazDto> getRestoranByNaziv(@PathVariable(name = "naziv") String naziv){


        Restoran restoran = restoranService.getByNaziv(naziv);

        List<Komentar> komentariRestorana = restoranService.findAllComments(restoran);

        List<KomentarPrikazDto> komentariZaPrikaz = new ArrayList<>();


        Set<Artikal> artikliRestorana = new HashSet<Artikal>();
        artikliRestorana = restoran.getArtikli();

        Set<ArtikalPrikazDto> artikliPrikaza = new HashSet<ArtikalPrikazDto>();

        for(Komentar komentar : komentariRestorana){
            KomentarPrikazDto dto = new KomentarPrikazDto(komentar);
            komentariZaPrikaz.add(dto);
        }

        RestoranPrikazDto prikazDto = new RestoranPrikazDto();

        prikazDto.setNazivRestorana(restoran.getNaziv());
        prikazDto.setLokacija(restoran.getLokacija());
        prikazDto.setTipRestorana(restoran.getTipRestorana());
        prikazDto.setKomentari(komentariRestorana);

        double prosecnaOcena = 0;
        for(Komentar komentar :  komentariRestorana){
            prosecnaOcena += komentar.getOcena();
        }

        if(komentariRestorana.size() > 0) {
            prosecnaOcena = prosecnaOcena / komentariRestorana.size();
        }

        prikazDto.setProsek(prosecnaOcena);

        for(Artikal artikal : artikliRestorana){
            ArtikalPrikazDto dto = new ArtikalPrikazDto(artikal);
            artikliPrikaza.add(dto);
        }

        prikazDto.setArtikliUPonudi(artikliPrikaza);
        prikazDto.setStatusRestorana(restoran.getStatusRestorana());

        return ResponseEntity.ok(prikazDto);
    }

    }

