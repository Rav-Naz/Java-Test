package rafal.nazarko.javatestmobile;

import com.google.gson.Gson;

import java.util.List;

class TestResponse {
    public int rozwiazanieId;
    public int nrAlbumu;
    public String koniecCzasu;
    public Testy testy;
    public List<Pytanie> listaPytan;

    public static TestResponse fromJson(String s) {
        return new Gson().fromJson(s, TestResponse.class);
    }
    public String toString() {
        return new Gson().toJson(this);
    }
}
