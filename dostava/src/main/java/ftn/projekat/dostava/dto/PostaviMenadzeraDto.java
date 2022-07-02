package ftn.projekat.dostava.dto;

public class PostaviMenadzeraDto {
    private String korisnickoIme;

    private Long idRestorana;

    public  PostaviMenadzeraDto() { }

    public PostaviMenadzeraDto(String korinickoIme, Long idRestorana) {
        this.korisnickoIme = korinickoIme;
        this.idRestorana = idRestorana;
    }

    public String getKorisnickoIme() {  return korisnickoIme;  }

    public Long getIdRestorana() {  return idRestorana;  }

    public void setKorisnickoIme(String korisnickoIme) {  this.korisnickoIme = korisnickoIme;  }

    public void setIdRestorana(Long idRestorana) {  this.idRestorana = idRestorana; }
}
