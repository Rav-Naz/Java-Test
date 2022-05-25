package rafal.nazarko.restapi.ODPOWIEDZIROZWIAZANIA;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ODPOWIEDZIROZWIAZANIA {
        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        private final Integer id;
        private final Integer id_rozwiazania;
        private final Integer id_odpowiedzi;
        private final Integer czy_zaznaczone;

        public ODPOWIEDZIROZWIAZANIA(Integer id, Integer id_rozwiazania, Integer id_odpowiedzi, Integer czy_zaznaczone) {
            this.id = id;
            this.id_rozwiazania = id_rozwiazania;
            this.id_odpowiedzi = id_odpowiedzi;
            this.czy_zaznaczone = czy_zaznaczone;
        }

        public ODPOWIEDZIROZWIAZANIA(){
            this.id = 1;
            this.id_rozwiazania = 0;
            this.id_odpowiedzi = 0;
            this.czy_zaznaczone = 0;
        }

        public Integer getId() {
            return this.id;
        }
        public Integer getOcena() {
            return this.id_rozwiazania;
        }

        public Integer getProgDolny() {
            return this.id_odpowiedzi;
        }

        public Integer getProgGorny() {
            return this.czy_zaznaczone;
        }
}
