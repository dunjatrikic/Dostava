package ftn.projekat.dostava.controller;

import ftn.projekat.dostava.entity.*;
import ftn.projekat.dostava.service.KupacService;
import ftn.projekat.dostava.service.MenadzerService;
import ftn.projekat.dostava.service.PorudzbinaService;
import ftn.projekat.dostava.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpSession;
import java.sql.ClientInfoStatus;
import java.util.List;
import java.util.UUID;
@RestController
public class PorudzbinaRestController {

   @Autowired
   private PorudzbinaService porudzbinaService;

   @Autowired
    private RestoranService restoranService;

   @Autowired
    KupacService kupacService;

    //Ppregled porudzbina za kupca
   @GetMapping("/api/pregled-porudzbina-kupac")
    public ResponseEntity<List<Porudzbina>> pregledPorudzbina(HttpSession session)
   {
       Korisnik ulogovanKorisnik = (Korisnik) session.getAttribute("korisnik");

       if (ulogovanKorisnik == null)
       {
           return new ResponseEntity("Korisnik nije pronadjen!", HttpStatus.NOT_FOUND);
       }
       else
       {
           if(ulogovanKorisnik.getUloga() == Uloga.Kupac)
           {
               Kupac ulogovanKupac = (Kupac) session.getAttribute("korisnik");
              List<Porudzbina> listaPorudzbina = porudzbinaService.findAllbyKupac(ulogovanKupac);
              return ResponseEntity.ok(listaPorudzbina);
           }
           else
           {
               return new ResponseEntity("Ulogovani korisnik nije kupac", HttpStatus.UNAUTHORIZED);
           }
       }
   }


   //Pregled porudzbina za dostavljaca
}
