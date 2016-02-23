package com.example.sandeepbalramsatone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MyAppWebViewClient extends WebViewClient {
    ProgressDialog mProgress;
    public MyAppWebViewClient( ProgressDialog progress){

        this.mProgress = progress;

    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        /*if (Uri.parse(url).getHost().endsWith("sandeepbalramsatone.com")) {
            return false;
        }*/

        if (Uri.parse(url).getHost().endsWith("sandeepbalramsatone.com")) {
            return false;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        view.getContext().startActivity(intent);
        return true;
    }

    // when finish loading page
    public void onPageFinished(WebView view, String url) {
        if (mProgress.isShowing()) {
            mProgress.dismiss();
        }
    }





}