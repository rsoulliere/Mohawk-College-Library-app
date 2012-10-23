package com.rs.MohawkLibrary;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class RoomReserve extends MenuActivity{

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
      myWebView.loadUrl("http://lib-drupal.mohawkcollege.ca/forms/Work/BookRoomMobile/mobile.php");
      myWebView.setWebChromeClient(new WebChromeClient() {
       	
 	   public void onProgressChanged(WebView view, int progress) {
 		  activity.setTitle("Loading...");
        activity.setProgress(progress * 100);

        if(progress == 100)
            activity.setTitle(R.string.opacSearch);
 	   }
 	 });
   }
 
     
  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
      // Check if the key event was the Back button and if there's history
      if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
          myWebView.goBack();
          return true;
      }
      // If it wasn't the Back key or there's no web page history, bubble up to the default
      // system behavior (probably exit the activity)
      return super.onKeyDown(keyCode, event);
  }
  }