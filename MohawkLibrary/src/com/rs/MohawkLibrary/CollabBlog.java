package com.rs.MohawkLibrary;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CollabBlog extends MenuActivity{

	final Activity activity = this;
	WebView myWebView; 
	
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
      this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
      setContentView(R.layout.webview);   
      myWebView  = (WebView) findViewById(R.id.webview);
      myWebView.setInitialScale(80);
      myWebView.getSettings().setJavaScriptEnabled(true);
      myWebView.setWebViewClient(new WebViewClient());
      myWebView.loadUrl("http://mohawkcollaboratory.tumblr.com/");
      myWebView.setWebChromeClient(new WebChromeClient() {
         	
    	   public void onProgressChanged(WebView view, int progress) {
    		  activity.setTitle("Loading...");
           activity.setProgress(progress * 100);

           if(progress == 100)
               activity.setTitle(R.string.collabblog);
    	   }
    	 });
   }
 
 
 
  }
