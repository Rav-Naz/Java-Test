package rafal.nazarko.javatestmobile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;

import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
    int timeToEnd = 60;
    CollapsingToolbarLayout toolBarLayout;
    boolean isTitleCollapsed = false;
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

        String cars[] = {"Mercedes", "Fiat", "Ferrari", "Aston Martin", "Lamborghini", "Skoda", "Volkswagen", "Audi", "Citroen"};

        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(linearParams);
        nestedScrollView.addView(linearLayout);

        for (String car : cars)
        {
            TextView textView = new TextView(this);
            LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textView.setLayoutParams(textParams);
            textView.setText(car);
            textView.setPadding(15, 15, 15, 15);
            linearLayout.addView(textView);
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