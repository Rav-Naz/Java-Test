package rafal.nazarko.restapi.TESTY;

import java.util.ArrayList;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;

import rafal.nazarko.restapi.PYTANIA.PYTANIA;

public class TESTYResponse {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Integer id;
    Integer nr_albumu;
	TESTY test;
	Iterable<PYTANIA> lista_pytan;

    public TESTYResponse() {
        this.id = 1;
        this.test = new TESTY();
        this.nr_albumu = 1;
        this.lista_pytan = new ArrayList<>();
    }

    public TESTYResponse(Integer nr_albumu, TESTY test, Iterable<PYTANIA> lista_pytan) {
        this.nr_albumu = nr_albumu;
        this.test = test;
        this.lista_pytan = lista_pytan;
        this.id = 1;
    }

    public Integer getNrAlbumu() {
        return this.nr_albumu;
    }
    public TESTY getTesty() {
        return this.test;
    }
    public Iterable<PYTANIA> getListaPytan() {
        return this.lista_pytan;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
