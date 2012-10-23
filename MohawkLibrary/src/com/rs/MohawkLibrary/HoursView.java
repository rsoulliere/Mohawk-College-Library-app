package com.rs.MohawkLibrary;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class HoursView extends Activity{
	
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
	    	 WebView myWebView = (WebView) findViewById(R.id.webview);
	        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
	            myWebView.goBack();
	            return true;
	        }
	        return super.onKeyDown(keyCode, event);
	    }
	    
	

}
