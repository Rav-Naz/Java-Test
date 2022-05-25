package rafal.nazarko.restapi.ROZWIAZANIA;

public class OdpowiedzInterface {
    private Integer odpowiedz_id;
    private Integer czy_zaznaczone;

    public Integer getOdpowiedzId() {
        return this.odpowiedz_id;
    }
    public Integer getCzyZaznaczone() {
        return this.czy_zaznaczone;
    }

    public void setOdpowiedzId(Integer odpowiedz_id) {
        this.odpowiedz_id = odpowiedz_id;
    }
    public void setCzyZaznaczone(Integer czy_zaznaczone) {
        this.czy_zaznaczone = czy_zaznaczone;
    }
}
