package ftn.projekat.dostava.controller;

import ftn.projekat.dostava.entity.Menadzer;
import ftn.projekat.dostava.entity.Porudzbina;
import ftn.projekat.dostava.service.MenadzerService;
import ftn.projekat.dostava.service.PorudzbinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.UUID;

public class PorudzbinaRestController {
   @Autowired
   private PorudzbinaService porudzbinaService;

   @Autowired
    private MenadzerService menadzerService;

   @PostMapping("/api/porudzbine/pripremi")
    public ResponseEntity<Porudzbina> uPripremi(@RequestParam UUID id, HttpSession session)
   {
       Menadzer menadzer = (Menadzer) session.getAttribute("menadzer");
    if(menadzer == null)
    {
        return new ResponseEntity("Menadzer nije prijavljen!", HttpStatus.FORBIDDEN);
    }

    //Porudzbina porudzbina = porudzbinaService.promeni

       return ResponseEntity.ok(porudzbina);
   }

}
