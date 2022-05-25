package rafal.nazarko.restapi.ROZWIAZANIA;

public class OdpowiedzInterfaceResponse {
    private Integer odpowiedz_id;
    private Integer czy_zaznaczone;
    private Integer czy_poprawne;
    private String tresc;

    public OdpowiedzInterfaceResponse() {
        this.odpowiedz_id = 1;
        this.czy_zaznaczone = 1;
        this.czy_poprawne = 0;
        this.tresc = "tresc";
    }

    public OdpowiedzInterfaceResponse(Integer odpowiedz_id, Integer czy_zaznaczone, Integer czy_poprawne, String tresc) {
        this.odpowiedz_id = odpowiedz_id;
        this.czy_zaznaczone = czy_zaznaczone;
        this.czy_poprawne = czy_poprawne;
        this.tresc = tresc;
    }

    public Integer getOdpowiedzId() {
        return this.odpowiedz_id;
    }
    public Integer getCzyZaznaczone() {
        return this.czy_zaznaczone;
    }
    public Integer getCzyPoprawne() {
        return this.czy_poprawne;
    }
    public String getTresc() {
        return this.tresc;
    }

    public void setOdpowiedzId(Integer odpowiedz_id) {
        this.odpowiedz_id = odpowiedz_id;
    }
    public void setCzyZaznaczone(Integer czy_zaznaczone) {
        this.czy_zaznaczone = czy_zaznaczone;
    }
    public void setCzyPoprawne(Integer czy_poprawne) {
        this.czy_poprawne = czy_poprawne;
    }
    public void setTresc(String tresc) {
        this.tresc = tresc;
    }
}
