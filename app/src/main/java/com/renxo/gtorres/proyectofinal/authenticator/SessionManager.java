package com.renxo.gtorres.proyectofinal.authenticator;

import java.util.HashMap;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.util.Log;

import com.renxo.gtorres.proyectofinal.network.NetworkUtils;

public class SessionManager {
    SharedPreferences pref;
    Editor editor;
    Context _context;

    int PRIVATE_MODE = 0;
    private static final String PREF_NAME 	= "PROD";
    public static final String BUNDLE_NAME = "BUNDLE_NAME";
    public static final String BUNDLE_EMAIL = "BUNDLE_EMAIL";
    public static final String BUNDLE_TOKEN = "BUNDLE_TOKEN";
    public static final String BUNDLE_PHOTO = "BUNDLE_PHOTO";
    public static final String BUNDLE_LOGGED = "BUNDLE_LOGGED";

    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     */
    public void createLoginSession(String name, String email, String token, String facebookUrlPhoto) {
        editor.putBoolean(BUNDLE_LOGGED, true);
        editor.putString(BUNDLE_NAME, name);
        editor.putString(BUNDLE_EMAIL, email);
        editor.putString(BUNDLE_TOKEN, token);
        editor.putString(BUNDLE_PHOTO, facebookUrlPhoto);
        LoginTask loginTask = new LoginTask();
        loginTask.execute(email,token);
        editor.commit();

    }

    public class LoginTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            NetworkUtils networkUtils = new NetworkUtils();
            String Email = strings[0];
            String Token = strings[1];
            Log.d("login",Email + " : " + Token);
            networkUtils.Login(Email,Token);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String session = networkUtils.getSession(Token);
            Log.d("login", session);
            return null;
        }
    }

    /**
     * Get Stored session data
     */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(BUNDLE_NAME, pref.getString(BUNDLE_NAME, null));
        user.put(BUNDLE_EMAIL, pref.getString(BUNDLE_EMAIL, null));
        user.put(BUNDLE_TOKEN, pref.getString(BUNDLE_TOKEN, null));
        user.put(BUNDLE_PHOTO, pref.getString(BUNDLE_PHOTO, null));
        return user;
    }

    /**
     * Simplify the Return of the name
     */
    public String getPrefName(){
        return pref.getString(BUNDLE_NAME, "Sin nombre");
    }

    public String getPrefFacebookUrlPhoto(){return pref.getString(BUNDLE_PHOTO, "http://tilventis.com/wp-content/uploads/2018/03/logo_tilventis.png");}

    /**
     * clear session
     */
    public void logoutUser(){
        editor.clear();
        editor.commit();
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(BUNDLE_LOGGED, false);
    }


    @Override
    public String toString() {
        return "SessionManager{" +
                "BUNDLE_NAME='" + pref.getString(BUNDLE_NAME,"") + '\'' +
                ", BUNDLE_EMAIL='" + pref.getString(BUNDLE_EMAIL,"") + '\'' +
                ", BUNDLE_TOKEN='" + pref.getString(BUNDLE_TOKEN,"") + '\'' +
                ", BUNDLE_PHOTO='" + pref.getString(BUNDLE_PHOTO,"") + '\'' +
                '}';
    }


}
