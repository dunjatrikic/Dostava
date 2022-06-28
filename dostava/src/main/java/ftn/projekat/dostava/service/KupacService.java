package  ftn.projekat.dostava.service;


import ftn.projekat.dostava.dto.RegistrationDto;
import ftn.projekat.dostava.entity.*;
import ftn.projekat.dostava.repository.KupacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class KupacService {
    @Autowired
    private KupacRepository kupacRepository;
    @Autowired
    private  KorisnikService korisnikService;

    public void save(Kupac kupac){
        kupacRepository.save(kupac);
    }

    public void registracija(RegistrationDto registrationDto){


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate datum = LocalDate.parse(registrationDto.getDatumRodjenja(), formatter);
        TipKupca tip = new TipKupca("Novi kupac",0,0);
        Pol pol = Pol.valueOf(registrationDto.getPol());

        Kupac noviKorisnik = new Kupac();
        noviKorisnik.setKorisnickoIme(registrationDto.getKorisnickoIme());
        noviKorisnik.setIme(registrationDto.getIme());
        noviKorisnik.setPrezime(registrationDto.getPrezime());
        noviKorisnik.setLozinka(registrationDto.getLozinka());
        noviKorisnik.setDatumRodjenja(datum);
        noviKorisnik.setTip(tip);
        noviKorisnik.setPol(pol);



        kupacRepository.save(noviKorisnik);

    }
}
