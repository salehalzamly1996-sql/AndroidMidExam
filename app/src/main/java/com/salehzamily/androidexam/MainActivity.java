


package com.salehzamily.androidexam;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private EditText emailInput;
    private EditText passwordInput;
    private Button loginButton;
    private long lastBackPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailInput = findViewById(R.id.editTextEmail);
        passwordInput = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.buttonLogin);
        loginButton.setEnabled(false);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateLoginButton();
            }
        };

        emailInput.addTextChangedListener(textWatcher);
        passwordInput.addTextChangedListener(textWatcher);

        loginButton.setOnClickListener(view -> openModeActivity());
    }

    private void updateLoginButton() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString();
        boolean validEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches();
        boolean validPassword = password.length() >= 6;

        if (validEmail && validPassword) {
            loginButton.setEnabled(true);
        } else {
            loginButton.setEnabled(false);
        }
    }

    private void openModeActivity() {
        Intent intent = new Intent(MainActivity.this, MoodeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastBackPressedTime < 2000) {
            super.onBackPressed();
            return;
        }

        lastBackPressedTime = currentTime;
        Snackbar.make(findViewById(R.id.mainRoot),
                "Press back again to exit", Snackbar.LENGTH_SHORT).show();
    }
}

