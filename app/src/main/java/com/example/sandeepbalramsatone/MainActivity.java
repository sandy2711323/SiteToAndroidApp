package com.example.sandeepbalramsatone;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity {

    private WebView mWebView;
    ProgressDialog mProgress;

    // flag for Internet connection status
    Boolean isInternetPresent = false;

    // Connection detector class
    ConnectionDetector cd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cd = new ConnectionDetector(getApplicationContext());

        mWebView = (WebView) findViewById(R.id.activity_main_webview);


        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent) {
            // Internet Connection is Present
            // make HTTP requests

            mProgress = ProgressDialog.show(this, "Loading", "Please wait for a moment...");
            mWebView.setWebViewClient(new MyAppWebViewClient(mProgress));
            mWebView.loadUrl("http://sandeepbalramsatone.com/");
        } else {
            // Internet connection is not present
            // Ask user to connect to Internet
           mWebView.loadUrl("file:///android_asset/www/index.html");
           Toast.makeText(getApplicationContext(), "Please Connect to Internet ....! ", Toast.LENGTH_SHORT).show();
            /*new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    //    mWebView.reload();
                   // System.out.println("####");
                   // System.out.println(mWebView.getUrl());
                   // if (cd.isConnectingToInternet())
                   // mWebView.loadUrl("http://sandeepbalramsatone.com/");

                }
            }, 1000, 1000);*/
        }




        // Use remote resource
       //  mWebView.loadUrl("http://sandeepbalramsatone.com/");


        // Stop local links and redirects from opening in browser instead of WebView
        // mWebView.setWebViewClient(new MyAppWebViewClient());

        // Use local resource
        // mWebView.loadUrl("file:///android_asset/www/index.html");
    }

    // Prevent the back-button from closing the app
    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}