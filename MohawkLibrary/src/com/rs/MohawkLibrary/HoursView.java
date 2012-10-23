package com.rs.MohawkLibrary;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class HoursView extends MenuActivity{
	
	final Activity activity = this;
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.webview);
	        ActionBar actionBar = getActionBar();       
	        actionBar.setDisplayHomeAsUpEnabled(true);
	        WebView myWebView = (WebView) findViewById(R.id.webview);
	        myWebView.setInitialScale(100);
	        myWebView.setWebViewClient(new WebViewClient());
	        myWebView.loadUrl("http://lib-drupal.mohawkcollege.ca/mobile_data/hours.html");
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
	    	 WebView myWebView = (WebView) findViewById(R.id.webview);
	        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
	            myWebView.goBack();
	            return true;
	        }
	        return super.onKeyDown(keyCode, event);
	    }


}
