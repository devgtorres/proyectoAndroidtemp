package com.renxo.gtorres.proyectofinal.network;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.content.AsyncTaskLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {
    HttpURLConnection urlConnection = null;
    BufferedReader reader = null;
    String response = null;
    private static final String LOGIN_URL = "http://173.233.86.183:8080/CursoAndroidWebApp/rest/login";
    private static final String BUSCAR_INMUEBLE_URL = "http://173.233.86.183:8080/CursoAndroidWebApp/rest/buscarInmueble";
    private static final String OBTENER_SESION_URL = "http://173.233.86.183:8080/CursoAndroidWebApp/rest/obtenerSesion";
    private static final String LISTADO_FAVORITOS_URL = "http://173.233.86.183:8080/CursoAndroidWebApp/rest/listadoFavoritos";
    private static final String GUARDAR_FAVORITOS_URL = "http://173.233.86.183:8080/CursoAndroidWebApp/rest/guardarFavorito";


    public String getSession(String sessionToken) {
        try {
            String finalURL = String.format(OBTENER_SESION_URL);
            Uri builtURI = Uri.parse(finalURL)
                    .buildUpon()
                    .build();

            URL requestURL = new URL(builtURI.toString());
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.addRequestProperty("Authorization", sessionToken);
            urlConnection.addRequestProperty("Content-Type","application/json");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder builder = new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
            if (builder.length() == 0) {
                return null;
            }
            response = builder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }

    public String getInmuebles(
                    String sessionToken,
                    String MaxResults,
                    String Titulo,
                    String Barrio,
                    String Precio,
                    String CantDormitorio,
                    String TieneParrillero,
                    String TieneGarage,
                    String TieneBalcon,
                    String TienePatio) throws JSONException {
        try{
            String finalURL = String.format(BUSCAR_INMUEBLE_URL, sessionToken);
            Uri builtURI = Uri.parse(finalURL)
                    .buildUpon()
                    .build();

            URL requestURL = new URL(builtURI.toString());
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.addRequestProperty("authorization",sessionToken);
            urlConnection.addRequestProperty("Content-Type","application/json");
            urlConnection.addRequestProperty("Titulo",Titulo);
            urlConnection.addRequestProperty("Barrio",Barrio);
            urlConnection.addRequestProperty("Precio",Precio);
            urlConnection.addRequestProperty("CantDormitorio",CantDormitorio);
            urlConnection.addRequestProperty("TieneParrillero",TieneParrillero);
            urlConnection.addRequestProperty("TieneGarage",TieneGarage);
            urlConnection.addRequestProperty("TieneBalcon",TieneBalcon);
            urlConnection.addRequestProperty("TienePatio",TienePatio);

            JSONObject object = new JSONObject();
            object.put("MaxResults", MaxResults);
            object.put("Titulo", Titulo);
            object.put("Barrio", Barrio);
            object.put("Precio", Precio);
            object.put("CantDormitorio", CantDormitorio);
            object.put("TieneParrillero", TieneParrillero);
            object.put("TieneGarage", TieneGarage);
            object.put("TieneBalcon", TieneBalcon);
            object.put("TienePatio", TienePatio);

            urlConnection.getOutputStream().write(object.toString().getBytes());

            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder builder = new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
            if (builder.length() == 0) {
                return null;
            }
            response = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
            return response;
    }

    public void Login(String email, String sessionToken)  {
        try {
            String finalURL = String.format(LOGIN_URL);
            Uri builtURI = Uri.parse(finalURL)
                    .buildUpon()
                    .build();

            URL requestURL = new URL(builtURI.toString());
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.addRequestProperty("Authorization", sessionToken);
            urlConnection.addRequestProperty("Content-Type","application/json");
            JSONObject object = new JSONObject();
            object.put("email", email);
            urlConnection.getOutputStream().write(object.toString().getBytes());
            urlConnection.connect();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {

            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
