package rafal.nazarko.javatestmobile;

import com.google.gson.Gson;

import java.util.List;

class WynikResponse {
   public int rozwiazanieId;
   public int testId;
   public int ocena;
   public double punkty;
   public double punktyMax;
   public int nrAlbumu;
   public List<PytanieW> rozwiazanie;

   public static WynikResponse fromJson(String s) {
      return new Gson().fromJson(s, WynikResponse.class);
   }

   public String toString() {
      return new Gson().toJson(this);
   }
}
