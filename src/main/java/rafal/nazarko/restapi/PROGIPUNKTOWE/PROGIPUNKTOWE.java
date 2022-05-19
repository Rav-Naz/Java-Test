package rafal.nazarko.restapi.PROGIPUNKTOWE;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PROGIPUNKTOWE {
        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private final Integer id;
        private final Integer ocena;
        private final double prog_procentowy_dolny;
        private final double prog_procentowy_gorny;

        public PROGIPUNKTOWE(Integer id, Integer ocena, Integer prog_procentowy_dolny, Integer prog_procentowy_gorny) {
            this.id = id;
            this.ocena = ocena;
            this.prog_procentowy_dolny = prog_procentowy_dolny;
            this.prog_procentowy_gorny = prog_procentowy_gorny;
        }

        public PROGIPUNKTOWE(){
            this.id = 1;
            this.ocena = 0;
            this.prog_procentowy_dolny = 0;
            this.prog_procentowy_gorny = 0;
        }

        public Integer getId() {
            return this.id;
        }
        public Integer getOcena() {
            return this.ocena;
        }

        public double getProgDolny() {
            return this.prog_procentowy_dolny;
        }

        public double getProgGorny() {
            return this.prog_procentowy_gorny;
        }
}
