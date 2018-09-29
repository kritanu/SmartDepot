package com.example.shivendra.barcodescanner;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.ClientCertRequest;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

    private WebView webview;
    final Handler handler = new Handler();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getSupportActionBar().hide();

        setContentView(R.layout.activity_webview);

        webview = (WebView) findViewById(R.id.webView);

        Bundle data = getIntent().getExtras();
        



            final String FNAME = (String) data.getString("fName");
            final String LNAME = (String) data.getString("lName");
            final String ADDRESS = (String) data.getString("address");




            webview.setWebViewClient(new WebViewClient());
            webview.getSettings().setJavaScriptEnabled(true);
            webview.getSettings().setDomStorageEnabled(true);
            webview.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
            webview.setWebChromeClient(new WebChromeClient());
            webview.getSettings().setAppCacheEnabled(false);

            webview.loadUrl("http://192.168.0.112:3000");





            webview.setWebViewClient(new WebViewClient() {
                public void onPageFinished(WebView view, String url) {



                    webview.loadUrl("javascript: (function() {document.getElementById('fName').value='" + FNAME +"';}) ();");
                    webview.loadUrl("javascript: (function() {document.getElementById('lName').value='" + LNAME +"';}) ();");
                    webview.loadUrl("javascript: (function() {document.getElementById('age').value='" + ADDRESS +"';}) ();");
                    Log.d("jsquery",FNAME+" "+ LNAME+ " "+ADDRESS);

//                    webview.loadUrl("javascript:var x = document.getElementById('fName').value = '"+FNAME + "';");
//                    webview.loadUrl("javascript:var y = document.getElementById('lName').value = '"+LNAME + "';");
//                    webview.loadUrl("javascript:var z = document.getElementById('age').value = '"+ADDRESS + "';");

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            webview.loadUrl("javascript: (function() {document.getElementById('fName').focus();}) ();");

                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        InputMethodManager imm1 = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                                        imm1.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                                        Instrumentation inst = new Instrumentation();
                                        inst.sendKeyDownUpSync(KeyEvent.KEYCODE_SPACE);
                                        Thread.sleep(100);
                                        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                                        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                                    }
                                    catch(InterruptedException e){
                                    }
                                }
                            }).start();
                        }
                    },300);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            webview.loadUrl("javascript: (function() {document.getElementById('lName').focus();}) ();");

                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        InputMethodManager imm1 = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                                        imm1.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                                        Instrumentation inst = new Instrumentation();
                                        inst.sendKeyDownUpSync(KeyEvent.KEYCODE_SPACE);
                                        Thread.sleep(100);
                                        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                                        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                                    }
                                    catch(InterruptedException e){
                                    }
                                }
                            }).start();
                        }
                    },500);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            webview.loadUrl("javascript: (function() {document.getElementById('age').focus();}) ();");

                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        InputMethodManager imm1 = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                                        imm1.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                                        Instrumentation inst = new Instrumentation();
                                        inst.sendKeyDownUpSync(KeyEvent.KEYCODE_SPACE);
                                        Thread.sleep(100);

                                        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                                        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                                    }
                                    catch(InterruptedException e){
                                    }
                                }
                            }).start();
                        }
                    },700);
                }

            });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();

    }
}