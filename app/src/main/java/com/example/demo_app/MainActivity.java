package com.example.demo_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextSurname;
    private EditText editTextPassLen;
    private Spinner spinnerPosition;
    private CheckBox checkBoxCapital;
    private CheckBox checkBoxNumbers;
    private CheckBox checkBoxSpecial;
    private Button buttonGeneratePass;
    private Button buttonConfirm;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /* Grab all the components from the main activity */
        editTextName = findViewById(R.id.editTextName);
        editTextSurname = findViewById(R.id.editTextSurname);
        editTextPassLen = findViewById(R.id.editTextPassLen);
        spinnerPosition = findViewById(R.id.spinnerPosition);
        checkBoxCapital = findViewById(R.id.checkBoxCapital);
        checkBoxNumbers = findViewById(R.id.checkBoxNum);
        checkBoxSpecial = findViewById(R.id.checkBoxSpecial);
        buttonGeneratePass = findViewById(R.id.buttonGenPass);
        buttonConfirm = findViewById(R.id.buttonConfirm);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.positions,
                android.R.layout.simple_spinner_item
        );
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        spinnerPosition.setAdapter(adapter);
        /* Add onclick listeners to the buttons */
        buttonGeneratePass.setOnClickListener(v -> {
            int passLen = (byte) Integer.parseInt(String.valueOf(editTextPassLen.getText()));
            boolean capital = checkBoxCapital.isChecked();
            boolean numbers = checkBoxNumbers.isChecked();
            boolean special = checkBoxSpecial.isChecked();
            /* Build a list of permitted chars based on the checkbox states */
            String availableChars = "abcdefghijklmnouprstwyzxv" +
                    (capital ? "ABCDEFGHIJKLMNOPRSTUWZYXV" : "") +
                    (numbers ? "1234567890" : "") +
                    (special ? "!@#$%^&*()_-+=" : "");
            StringBuilder stringBuilderPass = new StringBuilder(passLen);
            for(int i = 0; i < passLen; i++) {
                /* Get a character at random position from list of available chars */
                stringBuilderPass.append(availableChars.charAt((new Random().nextInt(availableChars.length()))));
            }
            password = stringBuilderPass.toString();
            Toast.makeText(this, password, Toast.LENGTH_SHORT).show();
        });
        buttonConfirm.setOnClickListener(v -> {
            String message = "Dane pracownika: " + editTextName.getText() + " " +
                    editTextSurname.getText() + " " +
                    spinnerPosition.getSelectedItem().toString() + " " + password;
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        });
    }
}