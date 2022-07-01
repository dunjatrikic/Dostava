package ftn.projekat.dostava.controller;

import ftn.projekat.dostava.dto.DodajMenadzeraDostavljacaDto;
import ftn.projekat.dostava.dto.KorisnikDto;
import ftn.projekat.dostava.dto.RestoranDto;
import ftn.projekat.dostava.entity.*;
import ftn.projekat.dostava.service.AdminService;
import ftn.projekat.dostava.service.KorisnikService;
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
public class AdminRestController {

    @Autowired
    public KorisnikService korisnikService;
    @Autowired
    public AdminService adminService;
    @Autowired
    public MenadzerService menadzerService;

    @Autowired
    public RestoranService restoranService;

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

        if (this.korisnikService.findByKorisnickoIme(noviMenadzer.getKorisnickoIme())!= null)
            return new ResponseEntity("Korisnicko ime je vec zauzeto.", HttpStatus.BAD_REQUEST);

        this.korisnikService.save(noviMenadzer,Uloga.Menadzer);

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

        if ((this.korisnikService.findByKorisnickoIme(noviDostavljac.getKorisnickoIme())!= null))
            return new ResponseEntity("Korisnicko ime je vec zauzeto.", HttpStatus.BAD_REQUEST);

        this.korisnikService.save(noviDostavljac,Uloga.Dostavljac);

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

    @GetMapping("/api/korisnici")
    public ResponseEntity<List<KorisnikDto>> getKorisnici(HttpSession session){
        Korisnik ulogovani = (Korisnik) session.getAttribute("korisnik");

        if (ulogovani == null)
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        if (ulogovani.getUloga() != Uloga.Admin)
            return new ResponseEntity("Funkcionalnost je dostupna samo administratorima aplikacije", HttpStatus.BAD_REQUEST);

        List<Korisnik> korisnici = this.korisnikService.findAll();

        List<KorisnikDto> dtos = new ArrayList<>();
        for(Korisnik k : korisnici){
            KorisnikDto dto = new KorisnikDto(k);
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);

    }

    @GetMapping("/api/korisnik/{ime}")
    @ResponseBody
    public ResponseEntity<KorisnikDto> getKorisnikIme(@PathVariable(name = "ime") String ime, HttpSession session){
        Korisnik ulogovani = (Korisnik) session.getAttribute("korisnik");

        if (ulogovani == null)
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        if (ulogovani.getUloga() != Uloga.Admin)
            return new ResponseEntity("Funkcionalnost je dostupna samo administratorima aplikacije", HttpStatus.BAD_REQUEST);

        Korisnik korisnik = this.korisnikService.findByIme(ime);

        KorisnikDto dto = new KorisnikDto(korisnik);
        if(korisnik == null){
            return new ResponseEntity("Ne postoji korisnik sa ovim imenom.",HttpStatus.BAD_REQUEST);
        }
        else {
            return ResponseEntity.ok(dto);
        }
    }

    @GetMapping("/api/korisnik/{prezime}")
    public ResponseEntity<KorisnikDto> getKorisnikPrezime(@PathVariable(name = "prezime") String prezime, HttpSession session){
        Korisnik ulogovani = (Korisnik) session.getAttribute("korisnik");

        if (ulogovani == null)
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        if (ulogovani.getUloga() != Uloga.Admin)
            return new ResponseEntity("Funkcionalnost je dostupna samo administratorima aplikacije", HttpStatus.BAD_REQUEST);

        Korisnik korisnik = this.korisnikService.findByPrezime(prezime);

        KorisnikDto dto = new KorisnikDto(korisnik);
        if(korisnik == null){
            return new ResponseEntity("Ne postoji korisnik sa ovim prezimenom.",HttpStatus.BAD_REQUEST);
        }
        else {
            return ResponseEntity.ok(dto);
        }
    }

    @GetMapping("/api/korisnik/{korisnickoIme}")
    public ResponseEntity<KorisnikDto> getKorisnik(@PathVariable(name = "korisnickoIme") String korisnickoIme, HttpSession session){
        Korisnik ulogovani = (Korisnik) session.getAttribute("korisnik");

        if (ulogovani == null)
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        if (ulogovani.getUloga() != Uloga.Admin)
            return new ResponseEntity("Funkcionalnost je dostupna samo administratorima aplikacije", HttpStatus.BAD_REQUEST);

        Korisnik korisnik = this.korisnikService.findByKorisnickoIme(korisnickoIme);

        KorisnikDto dto = new KorisnikDto(korisnik);
        if(korisnik == null){
            return new ResponseEntity("Ne postoji korisnik sa ovim korisnickim imenom.",HttpStatus.BAD_REQUEST);
        }
        else {
            return ResponseEntity.ok(dto);
        }
    }



}
