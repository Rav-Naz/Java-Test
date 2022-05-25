package rafal.nazarko.restapi.TESTY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TESTY {
        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        private final Integer id;
        private final String nazwa;
        private final String kod_dolaczenia;
        private final String data_rozpoczecia;
        private final String data_zakonczenia;
        private final Integer czas;

        public TESTY(Integer id, String nazwa, String kod_dolaczenia, String data_rozpoczecia,String data_zakonczenia, Integer czas) {
            this.id = id;
            this.nazwa = nazwa;
            this.kod_dolaczenia = kod_dolaczenia;
            this.data_rozpoczecia = data_rozpoczecia;
            this.data_zakonczenia = data_zakonczenia;
            this.czas = czas;
        }

        public TESTY(){
            this.id = 1;
            this.nazwa = "nazwa";
            this.kod_dolaczenia = "kod_dolaczenia";
            this.data_rozpoczecia = "data_rozpoczecia";
            this.data_zakonczenia = "data_zakonczenia";
            this.czas = 60;
        }

        public Integer getId() {
            return this.id;
        }
        public String getNazwa() {
            return this.nazwa;
        }
        public String getKodDolaczenia() {
            return this.kod_dolaczenia;
        }
        public String getDataRozpoczecia() {
            return this.data_rozpoczecia;
        }
        public String getDataZakonczenia() {
            return this.data_zakonczenia;
        }
        public Integer getCzas() {
            return this.czas;
        }
}
