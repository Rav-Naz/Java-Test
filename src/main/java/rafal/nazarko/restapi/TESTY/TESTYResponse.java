package rafal.nazarko.restapi.TESTY;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import rafal.nazarko.restapi.PYTANIA.PYTANIA;

public class TESTYResponse {

    private Integer rozwiazanie_id;
    private Integer nr_albumu;
    private String koniec_czasu;
	private TESTY test;
	private Iterable<PYTANIA> lista_pytan;

    public TESTYResponse() {
        this.rozwiazanie_id = 1;
        this.test = new TESTY();
        this.nr_albumu = 1;
        this.lista_pytan = new ArrayList<>();
    }

    public TESTYResponse(Integer nr_albumu, TESTY test, Iterable<PYTANIA> lista_pytan) {
        this.nr_albumu = nr_albumu;
        this.test = test;
        this.lista_pytan = lista_pytan;
        this.rozwiazanie_id = 1;
    }

    public Integer getRozwiazanieId() {
        return this.rozwiazanie_id;
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
    public String getKoniecCzasu() {
        return this.koniec_czasu;
    }

    public void setId(Integer rozwiazanie_id) {
        this.rozwiazanie_id = rozwiazanie_id;
    }

    public void setKoniecCzasu(Integer czas) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, czas);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        this.koniec_czasu = dateFormat.format(cal.getTime());
    }
}
