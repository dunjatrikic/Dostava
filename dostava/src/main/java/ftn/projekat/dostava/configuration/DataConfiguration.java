package ftn.projekat.dostava.configuration;

import ftn.projekat.dostava.entity.*;
import ftn.projekat.dostava.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;





@Configuration
public class DataConfiguration {

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private ArtikalRepository artikalRepository;

    @Autowired
    private DostavljacRepository dostavljacRepository;

    @Autowired
    private KomentarRepository komentarRepository;

    @Autowired
    private KupacRepository kupacRepository;

    @Autowired
    private LokacijaRepository lokacijaRepository;

    @Autowired
    private MenadzerRepository menadzerRepository;

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @Autowired
    private RestoranRepository restoranRepository;

    @Autowired
    private TipKupcaRepository tipKupcaRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Bean
    public boolean instantiate(){
        /*Kupac kupac = new Kupac("andrea123","mitic123","Andrea","Mitic");
        korisnikRepository.save(kupac);
        Menadzer menadzer = new Menadzer("dunja","lozinka","Dunja","Trikic");
        menadzerRepository.save(menadzer);*/

        Admin admin1 = new Admin("markomarkovic","marko123","Marko","Markovic");
        adminRepository.save(admin1);
        Admin admin2 = new Admin("anaanic","ana123","Ana","Anic");
        adminRepository.save(admin2);
        Admin admin3 = new Admin("petarpetrovic","petar123","Petar","Petrovic");
        adminRepository.save(admin3);

        Kupac kupac1 = new Kupac("mika","mika123","Mika","Mikic");
        kupacRepository.save(kupac1);
        Kupac kupac2 = new Kupac("zika","zika123","Zika","Zikic");
        kupacRepository.save(kupac2);
        Kupac kupac3 = new Kupac("bojana","bojana123","Bojana","Bojanic");
        kupacRepository.save(kupac3);

        return true;

    }

}
