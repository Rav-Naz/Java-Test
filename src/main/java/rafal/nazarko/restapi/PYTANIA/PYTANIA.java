package rafal.nazarko.restapi.PYTANIA;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import rafal.nazarko.restapi.ODPOWIEDZI.ODPOWIEDZI;

@Entity
public class PYTANIA {
        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        private final Integer id;
        private final Integer id_testu;
        private final String tresc;
        private final Integer punkty;

        @OneToMany(targetEntity=ODPOWIEDZI.class, mappedBy="id_pytania", fetch=FetchType.EAGER)
        private final List<ODPOWIEDZI> odpowiedzi;

        public PYTANIA(Integer id, Integer id_testu, String tresc, Integer punkty, List<ODPOWIEDZI> odpowiedzi) {
            this.id = id;
            this.id_testu = id_testu;
            this.tresc = tresc;
            this.punkty = punkty;
            this.odpowiedzi = odpowiedzi;
        }

        public PYTANIA(){
            this.id = 1;
            this.id_testu = 0;
            this.tresc = "tresc";
            this.punkty = 0;
            this.odpowiedzi = new ArrayList<>();
        }

        public Integer getId() {
            return this.id;
        }
        public Integer getIdTestu() {
            return this.id_testu;
        }

        public String getTresc() {
            return this.tresc;
        }

        public Integer getPunkty() {
            return this.punkty;
        }

        public List<ODPOWIEDZI> getOdpowiedzi() {
            return this.odpowiedzi;
        }
}
