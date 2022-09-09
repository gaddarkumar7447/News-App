package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebView extends AppCompatActivity {
    android.webkit.WebView webView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = findViewById(R.id.webView);
        //webView.getSettings().setBuiltInZoomControls(true);
        progressBar = findViewById(R.id.p);
        progressBar.setVisibility(View.VISIBLE);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
        progressBar.setVisibility(View.INVISIBLE);

    }
}