package rafal.nazarko.restapi.ROZWIAZANIA;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ROZWIAZANIA {
        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private final Integer id;
        private final Integer id_testu;
        private final Integer nr_albumu;
        private final String data_rozpoczecia;
        private final String data_zakonczenia;
        private final Integer ocena;

        public ROZWIAZANIA(Integer id, Integer id_testu, Integer nr_albumu, String data_rozpoczecia,String data_zakonczenia, Integer ocena) {
            this.id = id;
            this.id_testu = id_testu;
            this.nr_albumu = nr_albumu;
            this.data_rozpoczecia = data_rozpoczecia;
            this.data_zakonczenia = data_zakonczenia;
            this.ocena = ocena;
        }

        public ROZWIAZANIA(){
            this.id = 1;
            this.id_testu = 0;
            this.nr_albumu = 0;
            this.data_rozpoczecia = "data_rozpoczecia";
            this.data_zakonczenia = "data_zakonczenia";
            this.ocena = 0;
        }

        public Integer getId() {
            return this.id;
        }
        public Integer getIdTestu() {
            return this.id_testu;
        }
        public Integer getNrAlbumu() {
            return this.nr_albumu;
        }
        public String getDataRozpoczecia() {
            return this.data_rozpoczecia;
        }
        public String getDataZakonczenia() {
            return this.data_zakonczenia;
        }
        public Integer getOcena() {
            return this.ocena;
        }
}
