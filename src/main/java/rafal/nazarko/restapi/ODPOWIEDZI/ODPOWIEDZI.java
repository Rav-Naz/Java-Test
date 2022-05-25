package rafal.nazarko.restapi.ODPOWIEDZI;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ODPOWIEDZI {
        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        private final Integer id;
        private final Integer id_pytania;
        private final String odpowiedz;
        private final Integer czy_poprawna;

        public ODPOWIEDZI(Integer id, Integer id_pytania, String odpowiedz, Integer czy_poprawna) {
            this.id = id;
            this.id_pytania = id_pytania;
            this.odpowiedz = odpowiedz;
            this.czy_poprawna = czy_poprawna;
        }

        public ODPOWIEDZI(){
            this.id = 1;
            this.id_pytania = 0;
            this.odpowiedz = "odpowiedz";
            this.czy_poprawna = 0;
        }

        public Integer getId() {
            return this.id;
        }
        public Integer getIdPytania() {
            return this.id_pytania;
        }

        public String getOdpowiedz() {
            return this.odpowiedz;
        }

        public Integer getCzyPoprawna() {
            return this.czy_poprawna;
        }
}
