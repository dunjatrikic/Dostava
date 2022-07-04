package ftn.projekat.dostava.controller;

import ftn.projekat.dostava.entity.*;
import ftn.projekat.dostava.service.ArtikalService;
import ftn.projekat.dostava.service.PorudzbinaService;
import ftn.projekat.dostava.service.RestoranService;
import ftn.projekat.dostava.service.StavkaPorudzbineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class StavkaPorudzbineRestController {

    @Autowired
    private RestoranService restoranService;
    @Autowired
    private PorudzbinaService porudzbinaService;

    @Autowired
    private StavkaPorudzbineService stavkaPorudzbineService;

    @Autowired
    private ArtikalService artikalService;


    @PostMapping("/api/dodaj-stavku/{id}")
    public ResponseEntity<String> dodajUKorpu(@PathVariable(name = "id") Long artikalID, HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("korisnik");

        if (ulogovaniKorisnik == null) {
            return new ResponseEntity(
                    "Korisnik nije pronadjen",
                    HttpStatus.NOT_FOUND);
        } else {
            if (ulogovaniKorisnik.getUloga() == Uloga.Kupac) {
                Kupac ulogovaniKupac = (Kupac) session.getAttribute("korisnik");
                Porudzbina korpa = porudzbinaService.findFirstbyStatus(StatusPorudzbine.U_pripremi, ulogovaniKupac.getId());
                Artikal artikal = artikalService.findById(artikalID);

                if (korpa.getRestoranporuceno() == artikal.getRestoran()) {

                    StavkaPorudzbine noviArtikal = new StavkaPorudzbine();
                    noviArtikal.setArtikal(artikalService.findById(artikalID));
                    noviArtikal.setPorucenaKolicina(1);
                    noviArtikal.setPorudzbina(korpa);

                    korpa.dodajStavku(noviArtikal);
                    stavkaPorudzbineService.save(noviArtikal);
                    return ResponseEntity.ok("Stavka  je uspesno dodata!");
                } else {
                    return new ResponseEntity<>("Artikal nije u ponudi restorana", HttpStatus.BAD_REQUEST);
                }
            }else {
                return new ResponseEntity(
                        "Ulogovani korisnik nije kupac",
                        HttpStatus.UNAUTHORIZED);
            }
        }


    }

    //Brisanje stavke
    @DeleteMapping("/api/izbrisi-stavku/{id}")
    public ResponseEntity<String> izbrisiStavkuPorudzbine(@PathVariable(name = "id") Long stavkaId, HttpSession session){
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("korisnik");

        if(ulogovaniKorisnik == null){
            return new ResponseEntity(
                    "Korisnik nije pronadjen",
                    HttpStatus.NOT_FOUND);
        }else{
            if(ulogovaniKorisnik.getUloga() == Uloga.Kupac){
                Kupac ulogovaniKupac = (Kupac) session.getAttribute("korisnik");
                Porudzbina korpa = porudzbinaService.findFirstbyStatus(StatusPorudzbine.Obrada, ulogovaniKupac.getId());

                StavkaPorudzbine stavkaKojaSeUklanja = stavkaPorudzbineService.getById(stavkaId);
                korpa.ukloniStavku(stavkaKojaSeUklanja);
                stavkaPorudzbineService.deleteById(stavkaId);

                return ResponseEntity.ok("Stavka uspesno uklonjena");
            }
            else{
                return new ResponseEntity(
                        "Ulogovani korisnik nije kupac",
                        HttpStatus.UNAUTHORIZED);
            }
        }
    }


}
