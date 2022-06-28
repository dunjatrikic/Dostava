package ftn.projekat.dostava.controller;

import ftn.projekat.dostava.dto.ArtikalDto;
import ftn.projekat.dostava.entity.*;
import ftn.projekat.dostava.service.ArtikalService;
import ftn.projekat.dostava.service.MenadzerService;
import ftn.projekat.dostava.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class RestoranRestController {
    @Autowired
    private RestoranService restoranService;

    @Autowired
    private ArtikalService artikalService;

    @Autowired
    private MenadzerService menadzerService;

@PostMapping("/api/dodajArtikal")
    public ResponseEntity<String> dodavanjeArtikla(@RequestBody ArtikalDto artikalDto, HttpSession session) {
    Korisnik ulogovani = (Korisnik) session.getAttribute("korisnik");

    if (ulogovani == null)
        return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
    if (ulogovani.getUloga() != Uloga.Menadzer)
        return new ResponseEntity("Funkcionalnost je dostupna samo menadzerima aplikacije", HttpStatus.BAD_REQUEST);

    Menadzer menadzer = menadzerService.findByKorisnickoIme(ulogovani.getKorisnickoIme());
    TipArtikla tipArtikla = TipArtikla.valueOf(artikalDto.getTipArtikla());
    Artikal artikal = new Artikal(artikalDto.getNaziv(),artikalDto.getCena(),tipArtikla,artikalDto.getKolicina(),artikalDto.getOpis());

    Restoran restoran = restoranService.findById(menadzer.getZaduzenRestoran().getId());
    menadzer.getZaduzenRestoran().getArtikli().add(artikal);
    artikalService.save(artikal);

    return new ResponseEntity("Uspesno ste dodali artikal ponudu restorana",HttpStatus.OK);


}

    }

