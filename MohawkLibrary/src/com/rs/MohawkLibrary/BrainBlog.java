package com.rs.MohawkLibrary;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class BrainBlog extends MenuActivity{

	WebView myWebView; 
	
	final Activity activity = this;
	
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
      this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
      setContentView(R.layout.webview);   
      myWebView  = (WebView) findViewById(R.id.webview);
      myWebView.setInitialScale(100);
      myWebView.getSettings().setJavaScriptEnabled(true);
      myWebView.setWebViewClient(new WebViewClient());
      myWebView.loadUrl("http://librarybrainblog.wordpress.com");
      myWebView.setWebChromeClient(new WebChromeClient() {
       	
 	   public void onProgressChanged(WebView view, int progress) {
 		  activity.setTitle("Loading...");
        activity.setProgress(progress * 100);

        if(progress == 100)
            activity.setTitle(R.string.brainblog);
 	   }
 	 });
  	
   }
 
  }
