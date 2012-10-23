package com.rs.MohawkLibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class BrainBlog extends Activity{
	public final static String EXTRA_MESSAGE = "test";
	public final static String SEARCH_SCOPE = "keyword";
	public final static String SEARCH_LOCATION = "1";
	public final static String SEARCH_FORMAT = "";
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
            activity.setTitle(R.string.opacSearch);
 	   }
 	 });
  	
   }
 
  @Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.layout.main_menu, menu);
	    return true;
	}
  
  @Override
 	public boolean onOptionsItemSelected(MenuItem item) {  	
     	 WebView myWebView = (WebView) findViewById(R.id.webview);
          myWebView.getSettings().setJavaScriptEnabled(true);
          myWebView.setWebViewClient(new WebViewClient());	
          Intent intent;
     	switch (item.getItemId()) {
 	    	
     	case android.R.id.home:
     		intent = new Intent(this, MohawkLibrary.class);
     		startActivity(intent);
    	    	break;
 	    	
     	case R.id.opacSearch:
     		intent = new Intent(this, SearchOpac.class);
     		startActivity(intent);
 	    	break;
 	    	
     	case R.id.articleSearch:
     		intent = new Intent(this, ArticleSearch.class);
     		startActivity(intent);
 	    	break;
 	    	
     	case R.id.room_reserve:
     		intent = new Intent(this, RoomReserve.class);
    		startActivity(intent);
	    	break;
 	    	
     	case R.id.brainblog:
     		intent = new Intent(this, BrainBlog.class);
    		startActivity(intent);
	    	break; 
     		
     	case R.id.collabblog:
     		intent = new Intent(this, CollabBlog.class);
    		startActivity(intent);
	    	break; 
     		

     	case R.id.chat:
     		intent = new Intent(this,LibraryChat.class);
    		startActivity(intent);
	    	break;  
 	    	
     	case R.id.about:
     		intent = new Intent(this, MohawkLibrary.class);
     		startActivity(intent);
    	    	break;
     	}
		return false;
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
