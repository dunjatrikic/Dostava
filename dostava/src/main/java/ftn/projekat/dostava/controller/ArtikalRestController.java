package ftn.projekat.dostava.controller;

import ftn.projekat.dostava.dto.ArtikalDto;
import ftn.projekat.dostava.entity.*;
import ftn.projekat.dostava.service.ArtikalService;
import ftn.projekat.dostava.service.MenadzerService;
import ftn.projekat.dostava.service.RestoranService;
import org.hibernate.service.spi.SessionFactoryServiceContributor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Locale;

@RestController
public class ArtikalRestController {
    @Autowired
    private ArtikalService artikalService;



    @Autowired
    private MenadzerService menadzerService;

    @Autowired
    private RestoranService restoranService;



    @PostMapping("/api/dodajArtikal")
    public ResponseEntity<String> dodavanjeArtikla(@RequestBody ArtikalDto artikalDto, HttpSession session) {
        Korisnik ulogovani = (Korisnik) session.getAttribute("korisnik");

        if (ulogovani == null)
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        if (ulogovani.getUloga() != Uloga.Menadzer)
            return new ResponseEntity("Funkcionalnost je dostupna samo menadzerima aplikacije", HttpStatus.BAD_REQUEST);


        Menadzer menadzer = menadzerService.findByKorisnickoIme(ulogovani.getKorisnickoIme());

            double kolicina = 0;
            if( artikalDto.getKolicina() != null && !artikalDto.getKolicina().isEmpty()){
                kolicina = Double.parseDouble(artikalDto.getKolicina());
            }

            double cena = 0;
            if(artikalDto.getCena()!= null && !artikalDto.getCena().isEmpty()){
                cena = Double.parseDouble(artikalDto.getCena());
            }


        TipArtikla tipArtikla = TipArtikla.valueOf(artikalDto.getTipArtikla());
        Artikal artikal = new Artikal(artikalDto.getNaziv(),cena,tipArtikla,kolicina,artikalDto.getOpis());

        Restoran restoran = restoranService.findById(menadzer.getZaduzenRestoran().getId());

        restoran.getArtikli().add(artikal);
        artikalService.save(artikal);

        return new ResponseEntity("Uspesno ste dodali artikal ponudu restorana",HttpStatus.OK);
    }

    @PutMapping("/api/artikli/updateArtikal/{id}")
    public ResponseEntity updateArtikal(@PathVariable(name = "id") Long id, @RequestBody ArtikalDto artikalDto, HttpSession session){
        Korisnik ulogovani = (Korisnik) session.getAttribute("korisnik");

        if (ulogovani == null)
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        if (ulogovani.getUloga() != Uloga.Menadzer)
            return new ResponseEntity("Funkcionalnost je dostupna samo administratorima aplikacije", HttpStatus.BAD_REQUEST);




        Menadzer menadzer = (Menadzer) session.getAttribute("korisnik");

        artikalService.update(id, artikalDto, menadzer);

        return ResponseEntity.ok("Uspesno updated!");
    }

    /*@DeleteMapping("/api/deleteArtikal/{id}")
    public ResponseEntity deleteArtikal(@PathVariable(name = "id") Long id, HttpSession session) {
        Korisnik ulogovani = (Korisnik) session.getAttribute("korisnik");

        if (ulogovani == null)
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        if (ulogovani.getUloga() != Uloga.Menadzer)
            return new ResponseEntity("Funkcionalnost je dostupna samo menadzerima aplikacije", HttpStatus.BAD_REQUEST);


        Menadzer menadzer = (Menadzer) ulogovani;
        Restoran restoran = menadzer.getZaduzenRestoran();



        for (Artikal artikal : restoran.getArtikli()) {

            if (artikal.getId() == id) {
                restoran.getArtikli().remove(artikal);
                this.restoranService.save(restoran);
                this.restoranService.deleteArtikal(artikal);
                return ResponseEntity.ok("Uspesno obrisan artikal!");
            }
        }
        return ResponseEntity.ok("Nepostojeci artikal!");
    }*/

    @DeleteMapping("/api/artikli/deleteArtikal/{id}")
    public ResponseEntity deleteArtikal(@PathVariable(name = "id") Long id, HttpSession session){

        Korisnik ulogovani = (Korisnik) session.getAttribute("korisnik");

        if (ulogovani == null)
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        if (ulogovani.getUloga() != Uloga.Menadzer)
            return new ResponseEntity("Funkcionalnost je dostupna samo menadzerima aplikacije", HttpStatus.BAD_REQUEST);

        Menadzer menadzer = (Menadzer) session.getAttribute("korisnik");
        Restoran restoran = menadzer.getZaduzenRestoran();

        if(!artikalService.deleteArtikal(id, restoran)){
            return ResponseEntity.badRequest().body("Neuspesno.");
        }

        return ResponseEntity.ok("Artikal je uspesno izbrisan");
    }

}
