package ftn.projekat.dostava.controller;

import ftn.projekat.dostava.dto.PregledKorpeDto;
import ftn.projekat.dostava.dto.PregledStavkePorudzbineDto;
import ftn.projekat.dostava.entity.*;
import ftn.projekat.dostava.service.KupacService;
import ftn.projekat.dostava.service.PorudzbinaService;
import ftn.projekat.dostava.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//PORUDZBINA

@RestController
public class PorudzbinaRestController {

   @Autowired
   private PorudzbinaService porudzbinaService;

   @Autowired
    private RestoranService restoranService;

   @Autowired
    KupacService kupacService;

    //1. Kupac pregleda porudzbine
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


   //3. Menadzer pregleda porudzbine restorana za koji je zaduzen
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

    //2. Dostavljac pregleda porudzbine koje su u statusu "Ceka dostavljaca"
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

                List<Porudzbina> listaVidljivihPorudzbina = new ArrayList<>();

                List<Porudzbina> listaSvihPorudzbina = porudzbinaService.findAll();
                Set<Porudzbina> listaDostavljacevihPorudzbina =  ulogovaniDostavljac.getPorudzbine();

                for(Porudzbina p: listaSvihPorudzbina){
                    if(p.getStatus() == StatusPorudzbine.Ceka_dostavljaca){
                        listaVidljivihPorudzbina.add(p);
                    }
                }

                listaVidljivihPorudzbina.addAll(listaDostavljacevihPorudzbina);

                return ResponseEntity.ok(listaVidljivihPorudzbina);
            }
            else{
                return new ResponseEntity(
                        "Ulogovani korisnik nije dostavljac",
                        HttpStatus.UNAUTHORIZED);
            }
        }
    }


    //4. Kupac kreira porudzbinu
    @GetMapping("/api/kreiranje-porudzbine/{id}")
    public ResponseEntity<String> kreiranjePorudzbine(@PathVariable(name = "id") Long idRestorana, HttpSession session){
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
                    kreiranaPorudzbina.setStatus(StatusPorudzbine.U_pripremi);
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

    //Pregled porucenih artikala
    @GetMapping("/api/pregled-korpe")
    public ResponseEntity<PregledKorpeDto> pregledKorpe(HttpSession session){
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("korisnik");

        if(ulogovaniKorisnik == null){
            return new ResponseEntity(
                    "Korisnik nije pronadjen",
                    HttpStatus.NOT_FOUND);
        }else{
            if(ulogovaniKorisnik.getUloga() == Uloga.Kupac){
                Kupac ulogovaniKupac = (Kupac) session.getAttribute("korisnik");
                Porudzbina korpa = porudzbinaService.findFirstbyStatus(StatusPorudzbine.U_pripremi, ulogovaniKupac.getId());
                korpa.setUkCena(korpa.izracunajCenu());

                List<PregledStavkePorudzbineDto> listaP = new ArrayList<>();
                PregledStavkePorudzbineDto pregledArtikla;
                PregledKorpeDto pregledKorpe = new PregledKorpeDto();

                for(StavkaPorudzbine st: korpa.getStavkaPorudzbine()){
                    pregledArtikla = new PregledStavkePorudzbineDto();
                    pregledArtikla.setNazivArtikla(st.getArtikal().getNaziv());
                    pregledArtikla.setCenaArtikla(st.getArtikal().getCena());
                    pregledArtikla.setPorucenaKolicina(st.getPorucenaKolicina());
                    pregledArtikla.setKolicinaArtikla(st.getArtikal().getKolicina());

                    listaP.add(pregledArtikla);
                }
                pregledKorpe.setUkupnaCenaPorudzbine(korpa.izracunajCenu());
                pregledKorpe.setPregledArtikala(listaP);

                return ResponseEntity.ok(pregledKorpe);
            }
            else{
                return new ResponseEntity(
                        "Ulogovani korisnik nije kupac",
                        HttpStatus.UNAUTHORIZED);
            }
        }
    }

    //Porucivanje
    @PutMapping("/api/poruci/{id}")
    public ResponseEntity<String> porucivanje(@PathVariable(name = "id") Long idRestorana, HttpSession session){
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("korisnik");

        if(ulogovaniKorisnik == null){
            return new ResponseEntity(
                    "Korisnik nije pronadjen",
                    HttpStatus.NOT_FOUND);
        }else{
            if(ulogovaniKorisnik.getUloga() == Uloga.Kupac){
                Kupac ulogovaniKupac = (Kupac) session.getAttribute("korisnik");
                Porudzbina korpa = porudzbinaService.findFirstbyStatus(StatusPorudzbine.U_pripremi, ulogovaniKupac.getId());
                korpa.setStatus(StatusPorudzbine.Obrada);
                korpa.setUkCena(korpa.izracunajCenu());

                Restoran trazeniRestoran = restoranService.findOneById(idRestorana);
                korpa.setRestoranporuceno(trazeniRestoran);

                porudzbinaService.save(korpa);
                return ResponseEntity.ok("Kraj narucivanja. Porudzbina se obradjuje!");
            }
            else{
                return new ResponseEntity(
                        "Ulogovani korisnik nije kupac",
                        HttpStatus.UNAUTHORIZED);
            }
        }
    }



}
