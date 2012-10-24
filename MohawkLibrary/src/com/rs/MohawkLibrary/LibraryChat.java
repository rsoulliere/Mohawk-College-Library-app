package com.rs.MohawkLibrary;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LibraryChat extends MenuActivity{

	final Activity activity = this;
	WebView myWebView;
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.webview);
	        ActionBar actionBar = getActionBar();       
	        actionBar.setDisplayHomeAsUpEnabled(true);
	        myWebView = (WebView) findViewById(R.id.webview);
	        myWebView.setInitialScale(100);
	        myWebView.getSettings().setJavaScriptEnabled(true);
	        myWebView.setWebViewClient(new WebViewClient());
	        myWebView.loadUrl("http://lib-wind.mohawkcollege.ca/Chat/library_mobile.html");
	        myWebView.setWebChromeClient(new WebChromeClient() {
   	
	   public void onProgressChanged(WebView view, int progress) {
		  activity.setTitle("Loading...");
      activity.setProgress(progress * 100);

      if(progress == 100)
          activity.setTitle(R.string.chat);
	   }
	 });
	}
	  
	    
}
