package rafal.nazarko.javatestmobile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
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
    String jsonString = "{\"rozwiazanieId\":29,\"nrAlbumu\":163982,\"koniecCzasu\":\"2022-06-03 21:52:21\",\"testy\":{\"id\":2,\"nazwa\":\"Test z JAVA Spring\",\"czas\":60,\"dataRozpoczecia\":\"2022-05-15 11:00:00\",\"dataZakonczenia\":\"2022-06-17 12:00:00\",\"kodDolaczenia\":\"jashdjaslk\"},\"listaPytan\":[{\"id\":2,\"tresc\":\"Czy JAVA Spring jest trudny?\",\"punkty\":2,\"odpowiedzi\":[{\"id\":2,\"odpowiedz\":\"NIE\",\"czyPoprawna\":0,\"idPytania\":2},{\"id\":1,\"odpowiedz\":\"TAK\",\"czyPoprawna\":1,\"idPytania\":2}],\"idTestu\":2},{\"id\":8,\"tresc\":\"Jaką ocenę dostanie Rafał za wykonanie tej aplikacji?\",\"punkty\":4,\"odpowiedzi\":[{\"id\":4,\"odpowiedz\":\"4.0\",\"czyPoprawna\":0,\"idPytania\":8},{\"id\":3,\"odpowiedz\":\"3.0\",\"czyPoprawna\":0,\"idPytania\":8},{\"id\":5,\"odpowiedz\":\"5.0\",\"czyPoprawna\":1,\"idPytania\":8}],\"idTestu\":2}]}";
    TestResponse dataFromApi;
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

     dataFromApi = TestResponse.fromJson(jsonString);
     timeToEnd = dataFromApi.testy.czas;

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setMaxLines(3);
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
                    toolBarLayout.setTitle("Test z języka Java 2022");
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

        NestedScrollView nestedScrollView = findViewById(R.id.listView1);
        nestedScrollView.setPadding(50,70,50,100);

        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(linearParams);
        nestedScrollView.addView(linearLayout);

        TextView startInfo = new TextView(this);
        startInfo.setTextColor(Color.BLACK);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        startInfo.setLayoutParams(p);
        startInfo.setText("Witaj! Zanim rozpoczeniesz rozwiązywanie testu przeczytaj poniższe informacje: \n\u2460 Jesteś zalogowany jako student o indeksie " + nrIndeksu + "\n\u2461 Na ukończenie testu masz " + timeToEnd + " min \n\u2462 Po zaznaczeniu odpowiedzi, powróć na górę strony aby przesłać odpowiedż za pomocą przycisku z ikoną wiadomości\n\u2463 Można zaznaczyć więcej niż jedną odpowiedź\n\nPowodzenia!\n");
        startInfo.setPadding(15, 15, 15, 15);
        startInfo.setTypeface(startInfo.getTypeface(), Typeface.BOLD);
        linearLayout.addView(startInfo);

        for (Pytanie pytanie : dataFromApi.listaPytan)
        {

            View divider = new View(this);
            ViewGroup.LayoutParams dividerParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2);
            divider.setBackgroundColor(Color.LTGRAY);
            divider.setPadding(0,0,0,100);
            divider.setLayoutParams(dividerParams);
            linearLayout.addView(divider);


            TextView textView = new TextView(this);
            textView.setTextColor(Color.BLACK);
            LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textView.setLayoutParams(textParams);
            textView.setText("\n(" + pytanie.punkty + " pkt) "  + pytanie.tresc);
            textView.setPadding(15, 15, 15, 15);
            linearLayout.addView(textView);

            for (Odpowiedz odpowiedz : pytanie.odpowiedzi)
            {
                CheckBox checkbox = new CheckBox(this);
                checkbox.setText(odpowiedz.odpowiedz);
                checkbox.setTextColor(Color.DKGRAY);
                linearLayout.addView(checkbox);
            }

            TextView a = new TextView(this);
            a.setText(" ");
            linearLayout.addView(a);
            textView.setPadding(0, 0, 0, 0);

        }

        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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