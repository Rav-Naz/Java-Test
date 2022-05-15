package rafal.nazarko.restapi.TESTY;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TESTYRepository extends CrudRepository<TESTY, Integer> {
    @Query("SELECT t FROM TESTY t WHERE t.kod_dolaczenia = ?1")
    public TESTY findByInvitationCode(String kod_dolaczenia);

    @Modifying
    @Query(value = "INSERT INTO ROZWIAZANIA (id_testu,nr_albumu) VALUES (:id_testu,:nr_albumu)", nativeQuery = true)
    @Transactional
    public void saveAttemp(@Param("id_testu") Integer id_testu, @Param("nr_albumu") Integer nr_albumu);
}