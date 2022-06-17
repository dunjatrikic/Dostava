package ftn.projekat.dostava.controller;

import ftn.projekat.dostava.dto.PregledKorpeDto;
import ftn.projekat.dostava.entity.*;
import ftn.projekat.dostava.service.KupacService;
import ftn.projekat.dostava.service.MenadzerService;
import ftn.projekat.dostava.service.PorudzbinaService;
import ftn.projekat.dostava.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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


   //Menadzer pregleda porudzbine restorana za koji je zaduzen
    @GetMapping("/api/menadzer-pregled-porudzbina")
   public ResponseEntity<List<Porudzbina>> pregledPorudzbinaMenadzerovogRestorana(HttpSession session){
       Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("korisnik");

       if(ulogovaniKorisnik == null){
           return new ResponseEntity(
                   "Korisnik nije pronadjen",
                   HttpStatus.NOT_FOUND);
       }else{
           if(ulogovaniKorisnik.getUloga() == Uloga.Menadzer){
               Menadzer ulogovaniMenadzer = (Menadzer) session.getAttribute("korisnik");

               List<Porudzbina> porudzbineMenadzerovogRestorana = porudzbinaService.findAllByRestoran(ulogovaniMenadzer.getZaduzenRestoran());
               return ResponseEntity.ok(porudzbineMenadzerovogRestorana);
           }
           else{
               return new ResponseEntity(
                       "Ulogovani korisnik nije dostavljac",
                       HttpStatus.UNAUTHORIZED);
           }
       }
   }

    //Pregled porudzbina dostavljaca
    @GetMapping("/api/dostavljac-pregled-porudzbina")
    public ResponseEntity<List<Porudzbina>> pregledPorudzbinaDostavljaca(HttpSession session){
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("korisnik");

        if(ulogovaniKorisnik == null){
            return new ResponseEntity(
                    "Korisnik nije pronadjen",
                    HttpStatus.NOT_FOUND);
        }else{
            if(ulogovaniKorisnik.getUloga() == Uloga.Dostavljac){
                Dostavljac ulogovaniDostavljac = (Dostavljac) session.getAttribute("korisnik");

                List<Porudzbina> listaVidljivihPorudzbina = new ArrayList<>();  //lista koju vracamo

                List<Porudzbina> listaSvihPorudzbina = porudzbinaService.findAll();     //sve porudzbine
                Set<Porudzbina> listaDostavljacevihPorudzbina =  ulogovaniDostavljac.getPorudzbine();   //porudzbine koje raznosi ulogovani dostavljac

                //Trazenje porudzbina koje cekaju dostavu
                for(Porudzbina p: listaSvihPorudzbina){
                    if(p.getStatus() == Status.Ceka_dostavljaca){
                        listaVidljivihPorudzbina.add(p);
                    }
                }

                //Dodavanje porudzbina dostavljaca
                for(Porudzbina p: listaDostavljacevihPorudzbina){
                    listaVidljivihPorudzbina.add(p);
                }

                return ResponseEntity.ok(listaVidljivihPorudzbina);
            }
            else{
                return new ResponseEntity(
                        "Ulogovani korisnik nije dostavljac",
                        HttpStatus.UNAUTHORIZED);
            }
        }
    }


    //Kreiranje porudzbine
    @GetMapping("/api/kreiranje-porudzbine/{id}")
    public ResponseEntity<String> kreiranjePorudzbine(@PathVariable(name = "id") long idRestorana, HttpSession session){
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("korisnik");

        if(ulogovaniKorisnik == null){
            return new ResponseEntity(
                    "Korisnik nije pronadjen",
                    HttpStatus.NOT_FOUND);
        }else{
            if(ulogovaniKorisnik.getUloga() == Uloga.Kupac){
                Kupac ulogovaniKupac = (Kupac) session.getAttribute("korisnik");

                Restoran trazeniRestoran = restoranService.findOneById(idRestorana);
                if(trazeniRestoran == null)
                    return new ResponseEntity(
                            "Restoran nije pronadjen",
                            HttpStatus.NOT_FOUND);

                if(trazeniRestoran.getStatusRestorana() == StatusRestorana.Ne_radi){
                    return new ResponseEntity("Restoran ne radi", HttpStatus.FORBIDDEN);
                }else{
                    Porudzbina kreiranaPorudzbina = new Porudzbina();
                    kreiranaPorudzbina.setStatus(Status.U_pripremi);
                    kreiranaPorudzbina.setKupac(ulogovaniKupac);
                    porudzbinaService.save(kreiranaPorudzbina);
                    return ResponseEntity.ok("Porudzbina je kreirana!\n");
                }


            }
            else{
                return new ResponseEntity(
                        "Ulogovani korisnik nije kupac",
                        HttpStatus.UNAUTHORIZED);
            }
        }
    }

   



}
