package ftn.projekat.dostava.controller;

        import ftn.projekat.dostava.dto.*;
        import ftn.projekat.dostava.entity.*;
        import ftn.projekat.dostava.service.*;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import javax.servlet.http.HttpSession;
        import java.time.LocalDate;
        import java.time.format.DateTimeFormatter;
        import java.util.List;

@RestController

public class KorisnikRestController {
    @Autowired
    private KorisnikService korisnikService;
    @Autowired
    private PorudzbinaService porudzbinaService;
    @Autowired
    private KupacService kupacService;

    @Autowired
    private MenadzerService menadzerService;

    @Autowired
    private DostavljacService dostavljacService;

    @Autowired
    private AdminService adminService;

    @GetMapping("/api/")
    public String welcome()
    {
        return "Hello from api";
    }

    @PostMapping("api/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpSession session)
    {
        if(loginDto.getKorisnickoIme().isEmpty() || loginDto.getLozinka().isEmpty())
        {
            return new ResponseEntity<>("Niste uneli sve podatke.", HttpStatus.BAD_REQUEST);
        }
        Korisnik ulogovanKorisnik = korisnikService.login(loginDto.getKorisnickoIme(), loginDto.getLozinka());
        if(ulogovanKorisnik == null)
        {
            return new ResponseEntity<>("Korisnik ne postoji!", HttpStatus.NOT_FOUND);
        }
        session.setAttribute("korisnik", ulogovanKorisnik); //ukoliko je prosao sve na sesiju dodajemo korisnika
        return ResponseEntity.ok("Uspesno ste se ulogovali!");
    }

    @PostMapping("api/logout")
    public ResponseEntity Logout(HttpSession session){
        Korisnik loggedEmployee = (Korisnik) session.getAttribute("korisnik");

        if (loggedEmployee == null)
            return new ResponseEntity("Zabranjeno", HttpStatus.FORBIDDEN); //nemamo zakacenog korsnika za sesiju

        session.invalidate(); //unistavamo sesiju
        return new ResponseEntity("Uspesno ste se izlogovali!", HttpStatus.OK); //vracamo status ok
    }

    @GetMapping("/api/korisnici/ulogovanKorisnik")
    public ResponseEntity getUlogovan(HttpSession session){
        Korisnik ulogovanKorisnik = (Korisnik) session.getAttribute("korisnik");

        if(ulogovanKorisnik == null){
            return new ResponseEntity("invalid", HttpStatus.FORBIDDEN);
        }

        KorisnikDto korisnikdto = new KorisnikDto(ulogovanKorisnik);

        return ResponseEntity.ok(korisnikdto);
    }

    @PutMapping("/api/korisnici/ulogovanKorisnik/update")
    public ResponseEntity updateProfile(@RequestBody UpdateDto updateDto,HttpSession session){
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

        Pol pol = Pol.valueOf(updateDto.getPol());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate datum = LocalDate.parse(updateDto.getDatumRodjenja(), formatter);

        if(updateDto.getKorisnickoIme() != null)
            korisnik.setKorisnickoIme(updateDto.getKorisnickoIme());
        if(updateDto.getLozinka() != null)
            korisnik.setLozinka(updateDto.getLozinka());
        if(updateDto.getIme() != null)
            korisnik.setIme(updateDto.getIme());
        if(updateDto.getPrezime() != null)
            korisnik.setPrezime(updateDto.getPrezime( ));
        if(updateDto.getPol() != null)
            korisnik.setPol(pol);
        if(updateDto.getDatumRodjenja() != null)
            korisnik.setDatumRodjenja(datum);
        korisnikService.save(korisnik, korisnik.getUloga());

        return new ResponseEntity("Uspesne izmene.", HttpStatus.OK);
    }

    @GetMapping("/api/menadzer-pregled")
    public ResponseEntity<PregledMenadzerDto> prikaziPregledMenadzera(HttpSession session){
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("korisnik");

        if(ulogovaniKorisnik == null){
            return new ResponseEntity(
                    "Korisnik nije pronadjen",
                    HttpStatus.NOT_FOUND);
        }else{
            if(ulogovaniKorisnik.getUloga() == Uloga.Menadzer){
                Menadzer ulogovanMenadzer = (Menadzer) session.getAttribute("korisnik");
                PregledMenadzerDto pregledDto = new PregledMenadzerDto();

                pregledDto.setRestoran(ulogovanMenadzer.getZaduzenRestoran());

                List<Porudzbina> porudzbineRestorana = porudzbinaService.findAllByRestoran(ulogovanMenadzer.getZaduzenRestoran());
                pregledDto.setPorudzbineRestorana(porudzbineRestorana);

                return ResponseEntity.ok(pregledDto);
            }
            else{
                return new ResponseEntity(
                        "Funkcionalnost je dozvoljena samo MENADZERIMA.",
                        HttpStatus.UNAUTHORIZED);
            }
        }
    }

    @GetMapping("/api/admin-pregled")
    public ResponseEntity<PregledAdminDto> prikaziPregledAdmina(HttpSession session){
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("korisnik");

        if(ulogovaniKorisnik == null){
            return new ResponseEntity(
                    "Korisnik nije ulogovan",
                    HttpStatus.NOT_FOUND);
        }else{
            if(ulogovaniKorisnik.getUloga() == Uloga.Admin){
                Admin ulogovaniAdmin = (Admin) session.getAttribute("korisnik");
                PregledAdminDto pregledDto = new PregledAdminDto();

                List<Menadzer> menadzeriMedjuKorisnicima = menadzerService.findAll();
                pregledDto.setListaMenadzera(menadzeriMedjuKorisnicima);

                List<Dostavljac> dostavljaciMedjuKorisnicima = dostavljacService.findAll();
                pregledDto.setListaDostavljaca(dostavljaciMedjuKorisnicima);

                List<Kupac> kupciMedjuKorisnicima = kupacService.findAll();
                pregledDto.setListaKupaca(kupciMedjuKorisnicima);



                List<Admin> adminiMedjuKorisnicima = adminService.findAll();
                pregledDto.setListaAdmina(adminiMedjuKorisnicima);

                return ResponseEntity.ok(pregledDto);
            }
            else{
                return new ResponseEntity("Funkcionalnost je dozvoljena samo ADMINIMA.", HttpStatus.UNAUTHORIZED);
            }
        }
    }


}
