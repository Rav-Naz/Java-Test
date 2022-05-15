package rafal.nazarko.restapi.PYTANIA;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PYTANIARepository extends CrudRepository<PYTANIA, Integer> {

    @Query("SELECT p FROM PYTANIA p WHERE p.id_testu = ?1")
    public List<PYTANIA> findAllTestAnswers(Integer id_testu);
}