package ftn.projekat.dostava.controller;

        import ftn.projekat.dostava.dto.KorisnikDto;
        import ftn.projekat.dostava.dto.LoginDto;
        import ftn.projekat.dostava.dto.RegistrationDto;
        import ftn.projekat.dostava.dto.UpdateDto;
        import ftn.projekat.dostava.entity.Korisnik;
        import ftn.projekat.dostava.service.KorisnikService;
        import ftn.projekat.dostava.service.KupacService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import javax.servlet.http.HttpSession;

@RestController

public class KorisnikRestController {
    @Autowired
    private KorisnikService korisnikService;
    @Autowired
    private KupacService kupacService;

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
    public ResponseEntity updateProfile(HttpSession session,@RequestBody UpdateDto updateDto){
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

        if(updateDto.getKorisnickoIme() != null)
            korisnik.setKorisnickoIme(updateDto.getKorisnickoIme());
        if(updateDto.getLozinka() != null)
            korisnik.setLozinka(updateDto.getLozinka());
        if(updateDto.getIme() != null)
            korisnik.setIme(updateDto.getIme());
        if(updateDto.getPrezime() != null)
            korisnik.setPrezime(updateDto.getPrezime( ));
        if(updateDto.getPol() != null)
            korisnik.setPol(updateDto.getPol());
        if(updateDto.getDatumRodjenja() != null)
            korisnik.setDatumRodjenja(updateDto.getDatumRodjenja());

        return new ResponseEntity(korisnikService.save(korisnik, korisnik.getUloga()), HttpStatus.OK);
    }
}
