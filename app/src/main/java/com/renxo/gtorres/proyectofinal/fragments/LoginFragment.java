package com.renxo.gtorres.proyectofinal.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.renxo.gtorres.proyectofinal.MainActivity;
import com.renxo.gtorres.proyectofinal.R;
import com.renxo.gtorres.proyectofinal.authenticator.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class LoginFragment extends Fragment {

    public static final String PAGE = "page";
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private static final String EMAIL = "email, public_profile";
    private String sessionToken = "";
    private String facebookName = "";
    private String facebookEmail = "";
    private String facebookUrlPhoto = "";
    private LoginResult facebookResult;
    private SessionManager session;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
    }

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_login, container, false);

        session = new SessionManager(getActivity());
        checkLogin();
        loginButton = view.findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        loginButton.setFragment(this);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                facebookResult = loginResult;

                sessionToken = facebookResult.getAccessToken().getToken();
                // Facebook Email address
                GraphRequest request = GraphRequest.newMeRequest(
                        facebookResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                try {
                                    facebookUrlPhoto = "http://graph.facebook.com/"+object.getString("id")+"/picture?type=large";
                                    facebookName = object.getString("name");
                                    facebookEmail = object.getString("email");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                session.createLoginSession(
                                        facebookName,
                                        facebookEmail,
                                        sessionToken,
                                        facebookUrlPhoto
                                );
                                Log.d("login", "registro login:");
                                Log.d("login", session.toString());
                                checkLogin();
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Log.d("login","Login cancelado.");
            }
            @Override
            public void onError(FacebookException exception) {
                Log.d("login","ERR Login :" + exception.toString());
                exception.printStackTrace();
            }

        });

        return view;
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Check login method - direct user to login activity if fail to sign in
     */
    public void checkLogin(){
        if(session.isLoggedIn()){
            ((MainActivity) getActivity()).ordenarMenu();
            Fragment homeFragment = HomeFragment.newInstance();
            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
            ((MainActivity) getActivity()).inflateFragment(homeFragment);
        }
    }

}