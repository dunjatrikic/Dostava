package ftn.projekat.dostava.controller;

        import ftn.projekat.dostava.dto.LoginDto;
        import ftn.projekat.dostava.entity.Korisnik;
        import ftn.projekat.dostava.service.KorisnikService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RestController;

        import javax.servlet.http.HttpSession;

@RestController

public class KorisnikRestController {
    @Autowired
    private KorisnikService korisnikService;

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

}
