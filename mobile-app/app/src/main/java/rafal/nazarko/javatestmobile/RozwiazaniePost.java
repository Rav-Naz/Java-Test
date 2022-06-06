package rafal.nazarko.javatestmobile;

import com.google.gson.Gson;

import java.util.List;

class RozwiazaniePost {
    public int rozwiazanieId;
    public int nrAlbumu;
    public int testId;
    public List<PytanieR> rozwiazanie;

    public String toString() {
        return new Gson().toJson(this);
    }
}
