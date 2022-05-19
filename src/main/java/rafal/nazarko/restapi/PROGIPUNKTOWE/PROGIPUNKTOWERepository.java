package rafal.nazarko.restapi.PROGIPUNKTOWE;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PROGIPUNKTOWERepository extends CrudRepository<PROGIPUNKTOWE, Integer> {
    @Query("SELECT p FROM PROGIPUNKTOWE p WHERE p.prog_procentowy_dolny <= ?1 AND p.prog_procentowy_gorny >= ?1")
    public PROGIPUNKTOWE findDegree(double d);
}