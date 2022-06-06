package rafal.nazarko.javatestmobile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import rafal.nazarko.javatestmobile.databinding.ActivityTestBinding;

public class Test extends AppCompatActivity {

private ActivityTestBinding binding;

    String timeLeft = "??:??";
    String nrIndeksu;
    String kodDostepu;
    int timeToEnd = 60;
    CollapsingToolbarLayout toolBarLayout;
    boolean isTitleCollapsed = false;
    String jsonString;
    TestResponse dataFromApi;
    LinearLayout mainLinearLayout;
    FloatingActionButton sendButton;
    RozwiazaniePost odpowiedzi;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

     Bundle extras = getIntent().getExtras();
     if (extras != null) {
         nrIndeksu = extras.getString("indeks");
         kodDostepu = extras.getString("kod");
     }

     Toolbar toolbar = binding.toolbar;
     setSupportActionBar(toolbar);
     toolBarLayout = binding.toolbarLayout;
     toolBarLayout.setTitle("Ładowanie...");
     toolBarLayout.setMaxLines(3);

     sendButton = findViewById(R.id.fab);

     NestedScrollView nestedScrollView = findViewById(R.id.listView1);
     nestedScrollView.setPadding(50,70,50,100);

     mainLinearLayout = new LinearLayout(this);
     LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
     mainLinearLayout.setOrientation(LinearLayout.VERTICAL);
     mainLinearLayout.setLayoutParams(linearParams);
     nestedScrollView.addView(mainLinearLayout);

     ProgressBar loading = new ProgressBar(this);
     ViewGroup.LayoutParams loadingParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
     loading.setLayoutParams(loadingParams);
     loading.setPadding(10,10,10,10);
     mainLinearLayout.addView(loading);
     sendGetTestRequest();

     FloatingActionButton fab = binding.fab;
     fab.setOnClickListener(view -> sendRozwiazaniePOSTRequest());

    }

    private void sendGetTestRequest() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.50.80:8080/TESTY/"+kodDostepu+"/"+nrIndeksu;
        TextView newTextview = new TextView(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    String str = "";
                    jsonString = new String(response.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                    mainLinearLayout.removeAllViewsInLayout();
                    setupTest();
                },
                error -> {
                    newTextview.setText(error.toString());
                });
        mainLinearLayout.addView(newTextview);
        queue.add(stringRequest);
    }

    private void setupTest() {
        dataFromApi = TestResponse.fromJson(jsonString);
        timeToEnd = dataFromApi.testy.czas;

        AppBarLayout appBarLayout = findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isTitleCollapsed = true;
                    toolBarLayout.setTitle(timeLeft);
                    isShow = true;
                } else if(isShow) {
                    isTitleCollapsed = false;
                    toolBarLayout.setTitle(dataFromApi.testy.nazwa);
                    isShow = false;
                }
            }
        });

        new CountDownTimer(timeToEnd*60*1000, 1000) {
            public void onTick(long millisUntilFinished) {
                timeLeft = getTimerText(millisUntilFinished);
                if(isTitleCollapsed) toolBarLayout.setTitle(timeLeft);
            }

            public void onFinish() {
                toolBarLayout.setTitle("Koniec czasu!");
            }
        }.start();

        TextView startInfo = new TextView(this);
        startInfo.setTextColor(Color.BLACK);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        startInfo.setLayoutParams(p);
        startInfo.setText("Witaj! Zanim rozpoczeniesz rozwiązywanie testu przeczytaj poniższe informacje: \n\u2460 Jesteś zalogowany jako student o indeksie " + nrIndeksu + "\n\u2461 Na ukończenie testu masz " + timeToEnd + " min \n\u2462 Po zaznaczeniu odpowiedzi, powróć na górę strony aby przesłać rozwiązanie za pomocą przycisku z ikoną wiadomości\n\u2463 Można zaznaczyć więcej niż jedną odpowiedź\n\nPowodzenia!\n");
        startInfo.setPadding(15, 15, 15, 15);
        startInfo.setTypeface(startInfo.getTypeface(), Typeface.BOLD);
        mainLinearLayout.addView(startInfo);
        odpowiedzi = new RozwiazaniePost();
        odpowiedzi.rozwiazanieId = dataFromApi.rozwiazanieId;
        odpowiedzi.testId = dataFromApi.testy.id;
        odpowiedzi.nrAlbumu = dataFromApi.nrAlbumu;
        odpowiedzi.rozwiazanie = new ArrayList<>();

        for (Pytanie pytanie : dataFromApi.listaPytan)
        {
            View divider = new View(this);
            ViewGroup.LayoutParams dividerParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2);
            divider.setBackgroundColor(Color.LTGRAY);
            divider.setPadding(0,0,0,100);
            divider.setLayoutParams(dividerParams);
            mainLinearLayout.addView(divider);


            TextView textView = new TextView(this);
            textView.setTextColor(Color.BLACK);
            LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textView.setLayoutParams(textParams);
            textView.setText("\n(" + pytanie.punkty + " pkt) "  + pytanie.tresc);
            textView.setPadding(15, 15, 15, 15);
            mainLinearLayout.addView(textView);

            PytanieR naPytanie = new PytanieR();
            naPytanie.pytanieId = pytanie.id;
            naPytanie.zaznaczone = new ArrayList<>();

            for (Odpowiedz odpowiedz : pytanie.odpowiedzi)
            {
                OdpowiedzR odpowiedzNaPytanie = new OdpowiedzR();
                odpowiedzNaPytanie.odpowiedzId = odpowiedz.id;
                odpowiedzNaPytanie.czyZaznaczone = 0;
                naPytanie.zaznaczone.add(odpowiedzNaPytanie);
                CheckBox checkbox = new CheckBox(this);
                checkbox.setText(odpowiedz.odpowiedz);
                checkbox.setTextColor(Color.DKGRAY);

                checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    onCheckboxChanged(buttonView,isChecked);
                });

                mainLinearLayout.addView(checkbox);
            }
            odpowiedzi.rozwiazanie.add(naPytanie);

            TextView a = new TextView(this);
            a.setText(" ");
            mainLinearLayout.addView(a);
            textView.setPadding(0, 0, 0, 0);
        }
        TextView b = new TextView(this);
        b.setText(odpowiedzi.toString());
        mainLinearLayout.addView(b);
    }

    private void onCheckboxChanged(CompoundButton buttonView, boolean isChecked) {
        int idOdpowiedzi = -1;
        for (Pytanie pytanie : dataFromApi.listaPytan)
        {
            for (Odpowiedz odpowiedz : pytanie.odpowiedzi)
            {
                if (odpowiedz.odpowiedz == buttonView.getText()) {
                    idOdpowiedzi = odpowiedz.id;
                    break;
                }
            }
            if (idOdpowiedzi != -1) break;
        }

        for (PytanieR pytanieR : odpowiedzi.rozwiazanie)
        {

            for (OdpowiedzR odpowiedzR : pytanieR.zaznaczone)
            {
                if (odpowiedzR.odpowiedzId == idOdpowiedzi) {
                    odpowiedzR.czyZaznaczone = isChecked ? 1:0;
                }
            }
        }

//        TextView b = new TextView(this);
//        b.setText(odpowiedzi.toString());
//        mainLinearLayout.removeViewAt(mainLinearLayout.getChildCount()-1);
//        mainLinearLayout.addView(b);
        checkIfEveryQuestionHaveAnswer();
    }

    private void sendRozwiazaniePOSTRequest() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.50.80:8080/ROZWIAZANIE";
        TextView newTextview = new TextView(this);
        final String requestBody = odpowiedzi.toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    String str = new String(response.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                    mainLinearLayout.removeAllViewsInLayout();
                    TextView asd = new TextView(this);
                    asd.setText(str);
                    mainLinearLayout.addView(asd);
                },
                error -> {
                    newTextview.setText(error.toString());
                }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };

        mainLinearLayout.addView(newTextview);
        queue.add(stringRequest);
    }

    private void checkIfEveryQuestionHaveAnswer() {
        boolean correct = true;
        if (timeLeft == "00:00" || timeLeft == "??:??") {
            correct = false;
        } else {
            for (PytanieR pytanieR : odpowiedzi.rozwiazanie)
            {
                boolean pytanieCorrect = false;
                for (OdpowiedzR odpowiedzR : pytanieR.zaznaczone)
                {
                    if (odpowiedzR.czyZaznaczone == 1) {
                        pytanieCorrect = true;
                        break;
                    }
                }
                if (!pytanieCorrect) {
                    correct = pytanieCorrect;
                    break;
                }
            }
        }
        sendButton.setEnabled(correct);
    }

    private String getTimerText(long  miliseconds) {
        int rounded = (int) Math.round(miliseconds/1000);

        int seconds = ((rounded % 86400) % 3600) % 60;
        int minutes = ((rounded % 86400) % 3600) / 60;

        return this.formatTime(seconds, minutes);
    }

    private String formatTime (int seconds, int minutes) {
        return String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
    }
}