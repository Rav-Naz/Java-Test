package rafal.nazarko.restapi.ROZWIAZANIA;

import java.util.List;

public class PytaniaInteface {
    private Integer pytanie_id;
    private List<OdpowiedzInterface> zaznaczone;

    public Integer getPytanieId() {
        return this.pytanie_id;
    }
    public List<OdpowiedzInterface> getZaznaczone() {
        return this.zaznaczone;
    }

    public void setPytanieId(Integer pytanie_id) {
        this.pytanie_id = pytanie_id;
    }
    public void setZaznaczone(List<OdpowiedzInterface> zaznaczone) {
        this.zaznaczone = zaznaczone;
    }
}
