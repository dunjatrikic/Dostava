package ftn.projekat.dostava.controller;

import ftn.projekat.dostava.dto.RegistrationDto;
import ftn.projekat.dostava.entity.Korisnik;
import ftn.projekat.dostava.service.KorisnikService;
import ftn.projekat.dostava.service.KupacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterRestController {
    @Autowired
    private KorisnikService korisnikService;
    @Autowired
    private KupacService kupacService;


    @PostMapping("/api/registration")
    public ResponseEntity<String> registracija(@RequestBody RegistrationDto registrationDto) {
        Korisnik korisnik = korisnikService.findByKorisnickoIme(registrationDto.getKorisnickoIme());
        if (korisnik != null) {
            return new ResponseEntity<>("Korisnicko ime vec postoji.", HttpStatus.BAD_REQUEST);

        }

        if (registrationDto.getKorisnickoIme().isEmpty() || registrationDto.getIme().isEmpty() || registrationDto.getPrezime().isEmpty() || registrationDto.getDatumRodjenja().isEmpty()) {
            return new ResponseEntity<>("Niste uneli odgovarajuce podatke.",HttpStatus.BAD_REQUEST);

        }


        if (!(registrationDto.getPol().equals("Z")) && !(registrationDto.getPol().equals("M"))) {
            return new ResponseEntity<>("Niste uneli validne podatke za pol.",HttpStatus.BAD_REQUEST);
        }

        kupacService.registracija(registrationDto);

        return new ResponseEntity<>("Uspesna registracija",HttpStatus.OK);



    }
}
