package rafal.nazarko.restapi.ROZWIAZANIA;

import java.util.List;

public class ROZWIAZANIAPostRequest {
    private Integer nr_albumu;
    private Integer rozwiazanieId;
    private List<PytaniaInteface> rozwiazanie;

    public Integer getNrAlbumu() {
        return this.nr_albumu;
    }
    public Integer getRozwiazanieId() {
        return this.rozwiazanieId;
    }
    public List<PytaniaInteface> getRozwiazanie() {
        return this.rozwiazanie;
    }

    public void setNrAlbumu(Integer nr_albumu) {
        this.nr_albumu = nr_albumu;
    }
    public void setRozwiazanieId(Integer rozwiazanieId) {
        this.rozwiazanieId = rozwiazanieId;
    }
    public void setRozwiazanie(List<PytaniaInteface> rozwiazanie) {
        this.rozwiazanie = rozwiazanie;
    }
}