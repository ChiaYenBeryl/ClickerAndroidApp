package com.example.clicker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class SelectPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        WebView mWebView;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_page);
        mWebView =findViewById(R.id.activity_main_webview);

        Intent intent = getIntent();

        String url = intent.getStringExtra("url");
        mWebView.loadUrl(url);

        SelectPage.BtnListener listener = new SelectPage.BtnListener();
        (findViewById(R.id.NextQuest)).setOnClickListener(listener);
        
    }

    private class BtnListener implements View.OnClickListener {
        // On-click event handler for all the buttons
        @Override
        public void onClick(View view) {

                    Intent i = new Intent();
                    i.setClass(SelectPage.this, MainActivity.class);
                    startActivity(i);
            }

        }
    }

