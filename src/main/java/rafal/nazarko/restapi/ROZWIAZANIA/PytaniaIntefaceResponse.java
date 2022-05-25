package rafal.nazarko.restapi.ROZWIAZANIA;

import java.util.ArrayList;
import java.util.List;

public class PytaniaIntefaceResponse {
    private Integer pytanie_id;
    private List<OdpowiedzInterfaceResponse> odpowiedzi;
    private String tresc;
    private Integer uzyskanePunkty;

    public PytaniaIntefaceResponse() {
        this.pytanie_id = 1;
        this.odpowiedzi = new ArrayList<>();
        this.tresc = "tresc";
        this.uzyskanePunkty = 1;
    }

    public PytaniaIntefaceResponse(Integer pytanie_id, List<OdpowiedzInterfaceResponse> odpowiedzi, String tresc, Integer uzyskanePunkty) {
        this.pytanie_id = pytanie_id;
        this.odpowiedzi = odpowiedzi;
        this.tresc = tresc;
        this.uzyskanePunkty = uzyskanePunkty;
    }

    public Integer getPytanieId() {
        return this.pytanie_id;
    }
    public List<OdpowiedzInterfaceResponse> getOdpowiedzi() {
        return this.odpowiedzi;
    }
    public String getTresc() {
        return this.tresc;
    }
    public Integer getUzyskanePunkty() {
        return this.uzyskanePunkty;
    }

    public void setPytanieId(Integer pytanie_id) {
        this.pytanie_id = pytanie_id;
    }
    public void setZaznaczone(List<OdpowiedzInterfaceResponse> odpowiedzi) {
        this.odpowiedzi = odpowiedzi;
    }
    public void setTresc(String tresc) {
        this.tresc = tresc;
    }
    public void setgetUzyskanePunkty(Integer punkty) {
        this.uzyskanePunkty = punkty;
    }
}
