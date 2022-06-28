package ftn.projekat.dostava.controller;

import ftn.projekat.dostava.dto.DodajMenadzeraDostavljacaDto;
import ftn.projekat.dostava.dto.RestoranDto;
import ftn.projekat.dostava.entity.*;
import ftn.projekat.dostava.service.AdminService;
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
public class AdminRestController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private MenadzerService menadzerService;

    @Autowired
    private RestoranService restoranService;

    @PostMapping("/api/admin/dodavanje-menadzera")
    public ResponseEntity<String> dodavanjeMenadzera(@RequestBody DodajMenadzeraDostavljacaDto dodajMenadzeraDostavljacaDto, HttpSession session) {
        Korisnik ulogovani = (Korisnik) session.getAttribute("korisnik");

        if (ulogovani == null)
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        if (ulogovani.getUloga() != Uloga.Admin)
            return new ResponseEntity("Funkcionalnost je dostupna samo administratorima aplikacije", HttpStatus.BAD_REQUEST);

        Menadzer noviMenadzer = new Menadzer();

        noviMenadzer.setIme(DodajMenadzeraDostavljacaDto.getIme());
        noviMenadzer.setPrezime(DodajMenadzeraDostavljacaDto.getPrezime());
        noviMenadzer.setLozinka(DodajMenadzeraDostavljacaDto.getLozinka());
        noviMenadzer.setPol(DodajMenadzeraDostavljacaDto.getPol());
        noviMenadzer.setKorisnickoIme(DodajMenadzeraDostavljacaDto.getKorisnickoIme());
        noviMenadzer.setUloga(DodajMenadzeraDostavljacaDto.getUloga());

        if (noviMenadzer.getUloga() != Uloga.Menadzer)
            return new ResponseEntity("Izabrana uloga mora biti: Menadzer", HttpStatus.BAD_REQUEST);

        if (this.adminService.getByKorisnickoIme(noviMenadzer.getKorisnickoIme()) != null)
            return new ResponseEntity("Korisnicko ime je vec zauzeto.", HttpStatus.BAD_REQUEST);

        this.adminService.saveMenadzer(noviMenadzer);

        return ResponseEntity.ok("Dodavanje Menadzera: Uspesno");

    }

    @PostMapping("/api/admin/dodavanje-dostavljaca")
    public ResponseEntity<String> dodavanjeDostavljaca(@RequestBody DodajMenadzeraDostavljacaDto dodajMenadzeraDostavljacaDto, HttpSession session) {
        Korisnik ulogovani = (Korisnik) session.getAttribute("korisnik");

        if (ulogovani == null)
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        if (ulogovani.getUloga() != Uloga.Admin)
            return new ResponseEntity("Funkcionalnost je dostupna samo administratorima aplikacije", HttpStatus.BAD_REQUEST);

        Dostavljac noviDostavljac = new Dostavljac();

        noviDostavljac.setIme(DodajMenadzeraDostavljacaDto.getIme());
        noviDostavljac.setPrezime(DodajMenadzeraDostavljacaDto.getPrezime());
        noviDostavljac.setLozinka(DodajMenadzeraDostavljacaDto.getLozinka());
        noviDostavljac.setPol(DodajMenadzeraDostavljacaDto.getPol());
        noviDostavljac.setKorisnickoIme(DodajMenadzeraDostavljacaDto.getKorisnickoIme());
        noviDostavljac.setUloga(DodajMenadzeraDostavljacaDto.getUloga());

        if (noviDostavljac.getUloga() != Uloga.Dostavljac)
            return new ResponseEntity("Izabrana uloga mora biti: DOSTAVLJAC", HttpStatus.BAD_REQUEST);

        if (this.adminService.getByKorisnickoIme(noviDostavljac.getKorisnickoIme()) != null)
            return new ResponseEntity("Korisnicko ime je vec zauzeto.", HttpStatus.BAD_REQUEST);

        this.adminService.saveDostavljac(noviDostavljac);

        return ResponseEntity.ok("Dodavanje Dostavljaca: Uspesno");
    }


    @PostMapping("/api/admin/kreiranje-restorana")
    public ResponseEntity<String> dodavanjeRestorana(@RequestBody RestoranDto restoranDto, HttpSession session) {
        Korisnik ulogovani = (Korisnik) session.getAttribute("korisnik");

        if (ulogovani == null)
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        if (ulogovani.getUloga() != Uloga.Admin)
            return new ResponseEntity("Funkcionalnost je dostupna samo administratorima aplikacije", HttpStatus.BAD_REQUEST);

        Restoran noviRestoran = new Restoran();
        noviRestoran.setNaziv(restoranDto.getNazivRestorana());
        noviRestoran.setTipRestorana(restoranDto.getTipRestorana());

        Lokacija lokacija = this.adminService.getLokacijaById(restoranDto.getIdLokacije());

        if (this.adminService.getByLokacija(lokacija) != null) {
            return new ResponseEntity("Restoran na ovoj lokaciji vec postoji.", HttpStatus.BAD_REQUEST);
        }

        noviRestoran.setLokacija(lokacija);
        Menadzer menadzer = menadzerService.findByKorisnickoIme(restoranDto.getMenadzer());

        if (menadzer == null) {
            return ResponseEntity.badRequest().body("Ne postoji menadzer sa ovim korisnickim imenom.");
        } else {
            noviRestoran.setMenadzer(menadzer);
        }

        this.adminService.saveRestoran(noviRestoran);

        return ResponseEntity.ok("Dodavanje novog restorana: Uspesno");
    }

    @PostMapping("/api/postavljanjeMenadzera")
    public ResponseEntity<String> postaviMenadzera(String nazivRestorana, String nazivMenadzera, HttpSession session) {
        Korisnik ulogovani = (Korisnik) session.getAttribute("korisnik");

        if (ulogovani == null)
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        if (ulogovani.getUloga() != Uloga.Admin)
            return new ResponseEntity("Funkcionalnost je dostupna samo administratorima aplikacije", HttpStatus.BAD_REQUEST);


        Restoran restoran = this.adminService.getRestoranByNaziv(nazivRestorana);
        if (restoran == null) {
            return new ResponseEntity("Ne postoji u sistemu restoran sa ovim nazivom.", HttpStatus.BAD_REQUEST);
        }

        Menadzer menadzer = menadzerService.findByKorisnickoIme(nazivMenadzera);
        if (menadzer == null) {
            return new ResponseEntity("U sistemu e postoji menadzer sa ovim korisnickim imenom", HttpStatus.BAD_REQUEST);
        }

        restoran.setMenadzer(menadzer);
        return new ResponseEntity("Uspesno.", HttpStatus.OK);
    }
}
