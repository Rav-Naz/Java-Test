package rafal.nazarko.restapi.ODPOWIEDZIROZWIAZANIA;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ODPOWIEDZIROZWIAZANIARepository extends CrudRepository<ODPOWIEDZIROZWIAZANIA, Integer> {

    @Modifying
    @Query(value = "INSERT INTO ODPOWIEDZIROZWIAZANIA (id_rozwiazania, id_odpowiedzi, czy_zaznaczone) VALUES (:id_rozwiazania, :id_odpowiedzi, :czy_zaznaczone)", nativeQuery = true)
    @Transactional
    public void saveNowaOdpowiedz(@Param("id_rozwiazania") Integer id_rozwiazania, @Param("id_odpowiedzi") Integer id_odpowiedzi, @Param("czy_zaznaczone") Integer czy_zaznaczone);
}