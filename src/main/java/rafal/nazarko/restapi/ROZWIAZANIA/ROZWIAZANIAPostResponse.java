package rafal.nazarko.restapi.ROZWIAZANIA;

import java.util.ArrayList;
import java.util.List;

public class ROZWIAZANIAPostResponse {
    private Integer nr_albumu;
    private Integer rozwiazanieId;
    private Integer testId;
    private Integer ocena;
    private float punkty;
    private float punktyMax;
    private List<PytaniaIntefaceResponse> rozwiazanie;

    ROZWIAZANIAPostResponse() {
        this.nr_albumu = 1;
        this.rozwiazanieId = 1;
        this.testId = 1;
        this.ocena = 1;
        this.punkty = 1;
        this.punktyMax = 1;
        this.rozwiazanie = new ArrayList<>();
    }

    public Integer getNrAlbumu() {
        return this.nr_albumu;
    }
    public Integer getRozwiazanieId() {
        return this.rozwiazanieId;
    }
    public Integer getTestId() {
        return this.testId;
    }
    public Integer getOcena() {
        return this.ocena;
    }
    public float getPunkty() {
        return this.punkty;
    }
    public float getPunktyMax() {
        return this.punktyMax;
    }
    public List<PytaniaIntefaceResponse> getRozwiazanie() {
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
    public void setOcena(Integer ocena) {
        this.ocena = ocena;
    }
    public void setPunkty(float punkty) {
        this.punkty = punkty;
    }
    public void setPunktyMax(float punktyMax) {
        this.punktyMax = punktyMax;
    }
    public void setRozwiazanie(List<PytaniaIntefaceResponse> rozwiazanie) {
        this.rozwiazanie = rozwiazanie;
    }
}
