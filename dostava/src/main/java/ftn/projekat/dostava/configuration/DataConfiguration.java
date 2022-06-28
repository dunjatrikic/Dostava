package ftn.projekat.dostava.configuration;


import ftn.projekat.dostava.entity.*;
import ftn.projekat.dostava.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


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

        TipKupca tip1 = new TipKupca("bronzani",0.10,10);
        TipKupca tip2 = new TipKupca("srebrni",0.20,20);
        TipKupca tip3 = new TipKupca("zlatni",0.40,40);

        Admin admin1 = new Admin("markomarkovic","marko123","Marko","Markovic",Pol.M, LocalDate.of(1998,4,15));
        adminRepository.save(admin1);
        Admin admin2 = new Admin("anaanic","ana123","Ana","Anic",Pol.Z,LocalDate.of(2000,5,17));
        adminRepository.save(admin2);



        Kupac kupac1 = new Kupac("mika","mika123","Mika","Mikic",Pol.Z,LocalDate.of(1989,12,11),10,tip1);
        kupacRepository.save(kupac1);
        Kupac kupac2 = new Kupac("zika","zika123","Zika","Zikic",Pol.M,LocalDate.of(1970,10,21),12,tip2);
        kupacRepository.save(kupac2);
        Kupac kupac3 = new Kupac("bojana","bojana123","Bojana","Bojanic",Pol.Z,LocalDate.of(2001,5,14),3,tip3);
        kupacRepository.save(kupac3);

        Artikal palacinka = new Artikal("Banana-split palacinka", 350, TipArtikla.jelo, 2, "Palacinka punjena sladoledom od vanile,bananama i plazmom");
        artikalRepository.save(palacinka);
        Artikal pizza = new Artikal("Pica", 500, TipArtikla.jelo, 3, "Margarita");
        artikalRepository.save(pizza);
        Artikal rizoto = new Artikal("Rizoto sa piletinom", 360, TipArtikla.jelo, 2, "Riza pomesana sa piletinom u sosu od sira i belog vina");
        artikalRepository.save(rizoto);
        Artikal giros = new Artikal("Pileci giros", 240, TipArtikla.jelo, 1, "Veliki pileci giros sa prilozima");
        artikalRepository.save(rizoto);



        Dostavljac dostavljac1 = new Dostavljac("balsa123","3244354","Balsa","Filipovic",Pol.M,LocalDate.of(2000,1,15));
        dostavljacRepository.save(dostavljac1);
        Dostavljac dostavljac2 = new Dostavljac("milica123","dsdsfs","Milica","Milic",Pol.Z,LocalDate.of(1998,8,16));
        dostavljacRepository.save(dostavljac2);

        Menadzer menadzer1 = new Menadzer("matej123","matejmatej","Matej","Matijevic");
        menadzerRepository.save(menadzer1);

        Lokacija lokacija1 = new Lokacija(24,89,"Veselina Maslese 13");
        lokacijaRepository.save(lokacija1);
        Lokacija lokacija2 = new Lokacija(44,85,"Strazilovska 11");
        lokacijaRepository.save(lokacija2);
        Lokacija lokacija3 = new Lokacija(25,79,"Janka Veselinovica 3");
        lokacijaRepository.save(lokacija3);

        Set<Artikal> jelovnikCiao = new HashSet<>();
        jelovnikCiao.add(pizza);
        jelovnikCiao.add(giros);
        Set<Artikal> jelovnikDizni = new HashSet<>();
        jelovnikDizni.add(palacinka);
        jelovnikDizni.add(rizoto);

        Restoran Dizni = new Restoran("Dizni","palacinkarnica",lokacija1);

        restoranRepository.save(Dizni);
        Dizni.setArtikli(jelovnikDizni);
        restoranRepository.save(Dizni);


        Restoran Ciao = new Restoran("Ciao","picerija",lokacija2);
        restoranRepository.save(Ciao);
        Ciao.setArtikli(jelovnikCiao);
        restoranRepository.save(Ciao);






        return true;

    }

}
