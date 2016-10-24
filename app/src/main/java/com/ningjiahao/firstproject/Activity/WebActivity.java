package com.ningjiahao.firstproject.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ningjiahao.firstproject.IntroduceFragment.News;
import com.ningjiahao.firstproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebActivity extends AppCompatActivity {
    @BindView(R.id.web_news)
    WebView webNews;
    private Toolbar tool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        tool= (Toolbar) findViewById(R.id.web_tool);
        tool.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        //setSupportActionBar(tool);
        Intent intent = getIntent();
        WebSettings webset=webNews.getSettings();
        webset.setJavaScriptEnabled(true);
        String s= intent.getStringExtra("Key");
        setSupportActionBar(tool);
        webNews.loadUrl(s);
        webNews.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webNews.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webNews.canGoBack()) {
                webNews.goBack();
                return true;
            } else {
                finish();
                overridePendingTransition(R.anim.a_b_back_in, R.anim.a_b_back_out);
            }
        }
        return true;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_web, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.share:
                break;
            case R.id.collect:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
