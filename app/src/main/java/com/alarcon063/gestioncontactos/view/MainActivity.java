package com.alarcon063.gestioncontactos.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alarcon063.gestioncontactos.databinding.ActivityMainBinding;
import com.alarcon063.gestioncontactos.interactor.LoginInteractor;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private LoginInteractor loginInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Login Interactor
        loginInteractor = new LoginInteractor(this);

        // Login Button
        Button loginButton = binding.loginButton;
        loginButton.setOnClickListener(view -> onLoginClick());
    }

    private void onLoginClick() {
        String username = binding.editTextUsuarioLogin.getText().toString();
        String password = binding.editTextPasswordLogin.getText().toString();

        boolean isValid = loginInteractor.validateCredentials(username, password);

        if(isValid) {
            Intent i = new Intent(getApplicationContext(), ContactsActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(this, "Ingrese sus credenciales", Toast.LENGTH_SHORT).show();
        }

    }
}