package com.alarcon063.gestioncontactos.interactor;

import android.app.Activity;

public class LoginInteractor {
    private Activity actividad;
    public static final String USERNAME = "usuario";
    public static final String PASSWORD = "valido";

    public LoginInteractor(Activity actividad) {
        this.actividad = actividad;
    }

    public boolean validateCredentials(String user, String password) {
        return user.equals(USERNAME) && password.equals(PASSWORD);
    }

}
