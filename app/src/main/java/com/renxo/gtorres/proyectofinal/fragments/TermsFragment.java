package com.renxo.gtorres.proyectofinal.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.renxo.gtorres.proyectofinal.R;

public class TermsFragment extends Fragment {

    public static final String PAGE = "page";
    private static final String URL = "http://173.233.86.183:8080/CursoAndroidWebApp/condiciones.html";
    private WebView myWebView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static TermsFragment newInstance() {

        Bundle args = new Bundle();

        TermsFragment fragment = new TermsFragment();
        args.putString(PAGE, "Terms");
        fragment.setArguments(args);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_terms, container, false);
        myWebView = v.findViewById(R.id.webview);
        myWebView.loadUrl(URL);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        myWebView.addJavascriptInterface(new WebAppInterface(getActivity()), "Android");
        myWebView.setWebViewClient(new MyWebViewClient());
        myWebView.setWebContentsDebuggingEnabled(true);

        /**
         * Usamos el navegador de chrome para sacar el log de Javascript.
         */
        myWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.d("jslog", consoleMessage.message() + " -- From line "
                        + consoleMessage.lineNumber() + " of "
                        + consoleMessage.sourceId());
                return super.onConsoleMessage(consoleMessage);
            }
        });

        /*
         *  Sacamos el boton de ver en la web en el navegador y que no se nos valla de la app cuando lo apreta.
         */

        /**
         * NO FUCIONA, LO HACE PERO SE VUELVE A CARGAR LA PÁGINA.
         */

        String script = "document.getElementsByClassName['menu_toggle'][0].style.display = 'none';";

        myWebView.evaluateJavascript(script, new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                Log.d("jslog", value);
            }
        });

        return v;
    }

    public class WebAppInterface {
        Context mContext;
        WebAppInterface(Context c) {
            mContext = c;
        }
        @JavascriptInterface
        public void showDialog(String msg) {
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
        }
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().equals("mathematica.stackexchange.com")) {
                // la webview carga esta url porque retornamos false
                return false;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }


    }

}