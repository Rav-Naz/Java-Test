package rafal.nazarko.restapi.ROZWIAZANIA;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface ROZWIAZANIARepository extends CrudRepository<ROZWIAZANIA, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE ROZWIAZANIA r SET r.data_rozwiazania = CURRENT_TIMESTAMP(),r.ocena = :ocena WHERE r.id = :id")
    public void saveDegree(@Param(value = "ocena")Integer ocena, @Param(value = "id")Integer id_rozwiazania);
    
    @Transactional
    @Modifying
    @Query("DELETE FROM ODPOWIEDZIROZWIAZANIA o WHERE o.id_rozwiazania = :id_rozwiazania")
    public void flushThisRozwiazanie(@Param(value = "id_rozwiazania")Integer id_rozwiazania);

    @ExceptionHandler()
    public default void handleException() {
        //
    }
}