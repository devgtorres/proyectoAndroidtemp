package com.renxo.gtorres.proyectofinal;

import android.media.Image;
import android.nfc.tech.NfcA;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.AccessTokenTracker;
import com.facebook.login.LoginManager;
import com.renxo.gtorres.proyectofinal.authenticator.SessionManager;
import com.renxo.gtorres.proyectofinal.fragments.ConfigFragment;
import com.renxo.gtorres.proyectofinal.fragments.FavoritesFragment;
import com.renxo.gtorres.proyectofinal.fragments.HelpFragment;
import com.renxo.gtorres.proyectofinal.fragments.HomeFragment;
import com.renxo.gtorres.proyectofinal.fragments.LoginFragment;
import com.renxo.gtorres.proyectofinal.fragments.SearchFragment;
import com.renxo.gtorres.proyectofinal.fragments.TermsFragment;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public DrawerLayout drawer;
    private SessionManager session;
    public NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session = new SessionManager(getApplicationContext());
        Log.d("login",session.toString());
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        /**
         * Sí existen... levantamos los datos de la instancia cerrada previamente.
         */

        /*
        if(savedInstanceState != null) {
            session.createLoginSession(
                    savedInstanceState.getString(session.BUNDLE_NAME),
                    savedInstanceState.getString(session.BUNDLE_EMAIL),
                    savedInstanceState.getString(session.BUNDLE_TOKEN),
                    savedInstanceState.getString(session.BUNDLE_PHOTO)
            );
        }*/

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open_navigation_drawer, R.string.close_navigation_drawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ordenarMenu();

        //Init Home Fragment
        Fragment newFragment = HomeFragment.newInstance();
        inflateFragment(newFragment);


    }

    public void ordenarMenu() {
        Menu navMenu = navigationView.getMenu();
        View v = navigationView.getHeaderView(0);
        TextView facebookName = (TextView) v.findViewById(R.id.facebookName);
        ImageView facebookImage = (ImageView) v.findViewById(R.id.facebookImage);
        Log.d("login", "TEXTO PREVIO AL UPDATE " + facebookName.getText().toString());

        if(session.isLoggedIn()){
            facebookName.setText(session.getPrefName());
            Picasso.get().load(session.getPrefFacebookUrlPhoto()).into(facebookImage);
            Log.d("login", "TEXTO POST AL LOGIN " + facebookName.getText().toString());
            navMenu.findItem(R.id.nav_ingresar).setVisible(false);
            navMenu.findItem(R.id.nav_favoritos).setVisible(true);
            navMenu.findItem(R.id.nav_quit).setVisible(true);
        } else {
            facebookName.setText("Usuario");
            Picasso.get().load(session.getPrefFacebookUrlPhoto()).into(facebookImage);
            Log.d("login", "TEXTO POST AL UNLOGIN " + facebookName.getText().toString());
            navMenu.findItem(R.id.nav_ingresar).setVisible(true);
            navMenu.findItem(R.id.nav_favoritos).setVisible(false);
            navMenu.findItem(R.id.nav_quit).setVisible(false);
        }
    }

    public void inflateFragment(Fragment newFragment) {
        Bundle bundle = new Bundle();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_layout, newFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id) {
            case R.id.nav_inicio:
                Fragment homeFragment = HomeFragment.newInstance();
                inflateFragment(homeFragment);
                break;
            case R.id.nav_favoritos:
                Fragment favoritesFragment = FavoritesFragment.newInstance();
                inflateFragment(favoritesFragment);
                break;
            case R.id.nav_ingresar:
                Fragment loginFragment = LoginFragment.newInstance();
                inflateFragment(loginFragment);
                break;
            case R.id.nav_buscar:
                Fragment searchFragment = SearchFragment.newInstance();
                inflateFragment(searchFragment);
                break;
            case R.id.nav_configuracion:
                Fragment configFragment = ConfigFragment.newInstance();
                inflateFragment(configFragment);
                break;
            case R.id.nav_terminos_y_condiciones:
                Fragment termsFragment = TermsFragment.newInstance();
                inflateFragment(termsFragment);
                break;
            case R.id.nav_ayuda:
                Fragment helpFragment = HelpFragment.newInstance();
                inflateFragment(helpFragment);
                break;
            case R.id.nav_quit:
                LoginManager.getInstance().logOut();
                session.logoutUser();
                ordenarMenu();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(session.isLoggedIn()) {
            /*
            HashMap<String, String> sessionData = session.getUserDetails();
            String Name = sessionData.get(session.BUNDLE_NAME);
            String Email = sessionData.get(session.BUNDLE_EMAIL);
            String Token = sessionData.get(session.BUNDLE_TOKEN);
            String Photo = sessionData.get(session.BUNDLE_PHOTO);
            outState.putString(session.BUNDLE_NAME, Name);
            outState.putString(session.BUNDLE_EMAIL, Email);
            outState.putString(session.BUNDLE_TOKEN, Token);
            outState.putString(session.BUNDLE_PHOTO, Photo);*/
        }
    }
}