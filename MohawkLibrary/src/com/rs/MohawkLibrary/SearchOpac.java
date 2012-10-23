package com.rs.MohawkLibrary;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.webkit.WebViewClient;

public class SearchOpac  extends Activity {
	
	public final static String EXTRA_MESSAGE = "test";
	public final static String SEARCH_SCOPE = "keyword";
	public final static String SEARCH_LOCATION = "1";
	public final static String SEARCH_FORMAT = "";
	public WebView opacWebView;
	
	final Activity activity = this;
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.search_opac);        
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
    	EditText search_terms = (EditText) findViewById(R.id.edit_message);
    	Spinner scope_spinner = (Spinner) findViewById(R.id.scope_select);
    	Spinner location_spinner = (Spinner) findViewById(R.id.location);
    	Spinner format_spinner = (Spinner) findViewById(R.id.format);
    	
    	String message = search_terms.getText().toString();
    	String scope = String.valueOf(scope_spinner.getSelectedItem()).toString();
    	String location = getResources().getStringArray(R.array.location_codes)[location_spinner.getSelectedItemPosition()];
    	String format = getResources().getStringArray(R.array.format_codes)[format_spinner.getSelectedItemPosition()];
    	String searchURL = "http://libcat.mohawkcollege.ca/eg/android/results?query=";
    	String searchParams = message
 				.concat("&qtype=").concat(scope)
 				.concat("&fi%3Aitem_type=").concat(format)
 				.concat("&locg=").concat(location);
 		 
         searchURL = searchURL.concat(searchParams);
         opacWebView = (WebView) findViewById(R.id.webview);
         opacWebView.setInitialScale(100);
     //    myWebView.setWebViewClient(new InnerWebViewClient()); // forces it to open in app
       
         
         opacWebView.getSettings().setJavaScriptEnabled(true);
         opacWebView.setWebChromeClient(new WebChromeClient() {
           	
        	   public void onProgressChanged(WebView view, int progress) {
        		  activity.setTitle("Loading...");
               activity.setProgress(progress * 100);

               if(progress == 100)
                   activity.setTitle(R.string.opacSearch);
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
    
    /** Called when the user clicks the Send button */
    public void clearForm(View view) {
    	EditText search_terms = (EditText) findViewById(R.id.edit_message);	
    	Spinner scope_spinner = (Spinner) findViewById(R.id.scope_select);
    	Spinner location_spinner = (Spinner) findViewById(R.id.location);
    	Spinner format_spinner = (Spinner) findViewById(R.id.format); 
    	search_terms.setText("");
    	location_spinner.setSelection(0);
    	scope_spinner.setSelection(0);
    	format_spinner.setSelection(0); 
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
