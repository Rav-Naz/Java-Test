package rafal.nazarko.restapi.ROZWIAZANIA;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ROZWIAZANIARepository extends CrudRepository<ROZWIAZANIA, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE ROZWIAZANIA r SET r.data_rozwiazania = CURRENT_TIMESTAMP(),r.ocena = :ocena WHERE r.id = :id")
    public void saveDegree(@Param(value = "ocena")Integer ocena, @Param(value = "id")Integer id_rozwiazania);
}