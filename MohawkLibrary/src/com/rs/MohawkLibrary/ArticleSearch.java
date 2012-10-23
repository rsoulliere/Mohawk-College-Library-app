package com.rs.MohawkLibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ArticleSearch extends Activity{
	public final static String EXTRA_MESSAGE = "test";
	public final static String SEARCH_SCOPE = "keyword";
	public final static String SEARCH_LOCATION = "1";
	public final static String SEARCH_FORMAT = "";
	public WebView opacWebView;
	
	final Activity activity = this;
	
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
      this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
      setContentView(R.layout.search_articles);        
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
 	    
     	
     	
     	/** Called when the user clicks the Send button */
        public void sendMessage(View view) {
        	
        	Spinner article_db_spinner = (Spinner) findViewById(R.id.article_db_select);
        
        	String article_db = String.valueOf(article_db_spinner.getSelectedItem()).toString();
        	String searchURL = " ";
        	if (article_db.equals("Academic Search Premiere")) {
        	searchURL = "http://ezproxy.mohawkcollege.ca:2048/login?url=http://search.ebscohost.com/login.aspx?authtype=ip,uid&profile=mobsmart&defaultdb=aph";
        	}
        	if (article_db.equals("Business Source Premiere")) {
            	searchURL = "http://ezproxy.mohawkcollege.ca:2048/login?url=http://search.ebscohost.com/login.aspx?authtype=ip,uid&profile=mobsmart&defaultdb=buh";
            	}
        	if (article_db.equals("Health Source: Nursing/Academic Edition")) {
            	searchURL = "http://ezproxy.mohawkcollege.ca:2048/login?url=http://search.ebscohost.com/login.aspx?authtype=ip,uid&profile=mobsmart&defaultdb=hch";
            	}
        	if (article_db.equals("MasterFILE Elite")) {
            	searchURL = "http://ezproxy.mohawkcollege.ca:2048/login?url=http://search.ebscohost.com/login.aspx?authtype=ip,uid&profile=mobsmart&defaultdb=fth";
            	}
        	if (article_db.equals("Computers and Applied Sciences Complete")) {
            	searchURL = "http://ezproxy.mohawkcollege.ca:2048/login?url=http://search.ebscohost.com/login.aspx?authtype=ip,uid&profile=mobsmart&defaultdb=iih";
            	}
        	if (article_db.equals("Other Mobile databases")) {
            	searchURL = "http://ezproxy.mohawkcollege.ca:2048/login?url=http://search.ebscohost.com/login.aspx?authtype=ip,uid&profile=mobsmart";
            	}
        
        	
             opacWebView = (WebView) findViewById(R.id.webview);
             opacWebView.setInitialScale(100);
         //    myWebView.setWebViewClient(new InnerWebViewClient()); // forces it to open in app
           
             
             opacWebView.getSettings().setJavaScriptEnabled(true);
     	
	    opacWebView.setWebChromeClient(new WebChromeClient() {
      	   public void onProgressChanged(WebView view, int progress) {
      		  activity.setTitle("Loading...");
             activity.setProgress(progress * 100);

             if(progress == 100)
                 activity.setTitle(R.string.articleSearch);
      	   }
      	 });
       
	    /* To ensure links open within the application */
        opacWebView.setWebViewClient(new WebViewClient() {
      	  public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
      		Toast.makeText(activity, "Oh no! " + description, Toast.LENGTH_SHORT).show();
      	  }  
      	 @Override
         public boolean shouldOverrideUrlLoading(WebView view, String url)
         {
             view.loadUrl(url);
             return true;
         }
      		   });     
        
        opacWebView.loadUrl(searchURL); 
   }
  
  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
      // Check if the key event was the Back button and if there's history
      if ((keyCode == KeyEvent.KEYCODE_BACK) && opacWebView.canGoBack()) {
          opacWebView.goBack();
          return true;
      }
      // If it wasn't the Back key or there's no web page history, bubble up to the default
      // system behavior (probably exit the activity)
      return super.onKeyDown(keyCode, event);
  }
  }