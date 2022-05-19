package rafal.nazarko.restapi.ROZWIAZANIA;

import java.util.List;

public class ROZWIAZANIAPostRequest {
    private Integer nr_albumu;
    private Integer rozwiazanieId;
    private Integer testId;
    private List<PytaniaInteface> rozwiazanie;

    public Integer getNrAlbumu() {
        return this.nr_albumu;
    }
    public Integer getRozwiazanieId() {
        return this.rozwiazanieId;
    }
    public Integer getTestId() {
        return this.testId;
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
    public void setTestId(Integer testId) {
        this.testId = testId;
    }
    public void setRozwiazanie(List<PytaniaInteface> rozwiazanie) {
        this.rozwiazanie = rozwiazanie;
    }
}