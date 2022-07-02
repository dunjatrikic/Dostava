package ftn.projekat.dostava.controller;

import ftn.projekat.dostava.dto.DodajMenadzeraDostavljacaDto;
import ftn.projekat.dostava.dto.KorisnikDto;
import ftn.projekat.dostava.dto.PostaviMenadzeraDto;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

        Pol pol = Pol.valueOf(dodajMenadzeraDostavljacaDto.getPol());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate datum = LocalDate.parse(dodajMenadzeraDostavljacaDto.getDatumRodjenja(), formatter);
        Uloga uloga = Uloga.valueOf(dodajMenadzeraDostavljacaDto.getUloga());
        noviMenadzer.setIme(dodajMenadzeraDostavljacaDto.getIme());
        noviMenadzer.setPrezime(dodajMenadzeraDostavljacaDto.getPrezime());
        noviMenadzer.setLozinka(dodajMenadzeraDostavljacaDto.getLozinka());
        noviMenadzer.setDatumRodjenja(datum);
        noviMenadzer.setPol(pol);
        noviMenadzer.setKorisnickoIme(dodajMenadzeraDostavljacaDto.getKorisnickoIme());
        noviMenadzer.setUloga(uloga);

        if (noviMenadzer.getUloga() != Uloga.Menadzer)
            return new ResponseEntity("Izabrana uloga mora biti: Menadzer", HttpStatus.BAD_REQUEST);

        if (this.korisnikService.findByKorisnickoIme(noviMenadzer.getKorisnickoIme())!= null)
            return new ResponseEntity("Korisnicko ime je vec zauzeto.", HttpStatus.BAD_REQUEST);

        this.korisnikService.save(noviMenadzer,Uloga.Menadzer);

        return ResponseEntity.ok("Dodavanje Menadzera: Uspesno");

    }

   /* @PostMapping("/api/admin/dodavanje-dostavljaca")
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
    }*/


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

   /* @PutMapping ("/api/postavljanjeMenadzera")
    public ResponseEntity<String> postaviMenadzera(@RequestBody PostaviMenadzeraDto postaviMenadzeraDto, HttpSession session) {
        Korisnik ulogovani = (Korisnik) session.getAttribute("korisnik");

        if (ulogovani == null)
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        if (ulogovani.getUloga() != Uloga.Admin)
            return new ResponseEntity("Funkcionalnost je dostupna samo administratorima aplikacije", HttpStatus.BAD_REQUEST);


        Restoran restoran = this.restoranService.findById(postaviMenadzeraDto.getIdRestorana());
        if (restoran != null) {
            Menadzer menadzer = menadzerService.findByKorisnickoIme(postaviMenadzeraDto.getKorisnickoIme());
            if (menadzer == null) {
                return new ResponseEntity("U sistemu ne postoji menadzer sa ovim korisnickim imenom", HttpStatus.BAD_REQUEST);
            }

            restoran.setMenadzer(menadzer);
            return new ResponseEntity("Uspesno.", HttpStatus.OK);
        } else {
            return new ResponseEntity("Ne postoji u sistemu restoran sa ovim nazivom.", HttpStatus.BAD_REQUEST);
        }

    }*/
   @PutMapping("/api/admin/postavljanjeMenadzera")
   public ResponseEntity<Menadzer> addRestoranMenadzer(@RequestBody PostaviMenadzeraDto dto, HttpSession session) {

       Korisnik uk = (Korisnik) session.getAttribute("korisnik");

       if(uk == null)
           return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
       if(uk.getUloga() != Uloga.Admin)
           return new ResponseEntity("Funkcionalnost je dostupna samo administratorima aplikacije", HttpStatus.BAD_REQUEST);

       //pronalazenje restorana po izabranom id-u
       Restoran restoran = new Restoran();
       restoran = this.menadzerService.findRestoranById(dto.getIdRestorana());

       if(restoran == null){
           return new ResponseEntity("Izabrani restoran ne postoji.", HttpStatus.BAD_REQUEST);
       }

       if(this.menadzerService.findByRestoran(restoran) != null){
           return new ResponseEntity("Izabrani restoran vec ima menadzera.", HttpStatus.BAD_REQUEST);
       }

       //pronalazenje menadzera po izabranom korisnickom imenu
       Menadzer menadzer = new Menadzer();
       menadzer = this.menadzerService.findByKorisnickoIme(dto.getKorisnickoIme());

       if(menadzer == null || menadzer.getUloga() != Uloga.Menadzer){
           return new ResponseEntity("Ne postoji menadzer sa izabranim korisnickim imenom.", HttpStatus.BAD_REQUEST);
       }

       menadzer.setZaduzenRestoran(restoran);
       final Menadzer updatedMenadzer = menadzerService.save(menadzer);

       return ResponseEntity.ok(updatedMenadzer);
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
            return new ResponseEntity("Ne postoji korisnik sa ovim imenom.",HttpStatus.BAD_REQUEST);}

            return ResponseEntity.ok(dto);

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
