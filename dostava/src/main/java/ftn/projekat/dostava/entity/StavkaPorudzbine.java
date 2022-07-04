package ftn.projekat.dostava.entity;

import javax.persistence.*;
import java.io.Serializable;

    @Entity
    public class StavkaPorudzbine implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;


        @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        @JoinColumn(name = "id_artikla", referencedColumnName = "id")
        private Artikal artikal;

        @Column
        private int porucenaKolicina;

        @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        @JoinColumn(name = "porudzbina_id")
        private Porudzbina porudzbina;

        public StavkaPorudzbine(){}

        public Long getId() {
            return id;
        }

       // public void setId(long id) {
          //  this.id = id;
       // }

        public Artikal getArtikal() {
            return artikal;
        }

        public void setArtikal(Artikal artikal) {
            this.artikal = artikal;
        }

        public int getPorucenaKolicina() {
            return porucenaKolicina;
        }

        public void setPorucenaKolicina(int porucenaKolicina) {
            this.porucenaKolicina = porucenaKolicina;
        }

        public Porudzbina getPorudzbina() {
            return porudzbina;
        }

        public void setPorudzbina(Porudzbina porudzbina) {
            this.porudzbina = porudzbina;
        }

        @Override
        public String toString() {
            return "StavkaPorudzbine{" +
                    "id=" + id +
                    ", artikal=" + artikal +
                    ", porucenaKolicina=" + porucenaKolicina +
                    ", porudzbina=" + porudzbina +
                    '}';
        }
    }
