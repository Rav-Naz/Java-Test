package rafal.nazarko.javatestmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button nextButton;
    EditText editText;
    String numerIndeksu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextButton = findViewById(R.id.button);
        editText = findViewById(R.id.editTextPhone2);

        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() == 6) {
                    numerIndeksu = s.toString();
                    nextButton.setEnabled(true);
                } else {
                    nextButton.setEnabled(false);
                }

            }
        });

    }

    public void scan(View view) {
        Intent intent = new Intent(this, QrCode.class);
        intent.putExtra("indeks", numerIndeksu);
        startActivity(intent);
    }
}