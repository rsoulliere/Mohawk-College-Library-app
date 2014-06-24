package com.rs.MohawkLibrary;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.VideoView;
import android.webkit.WebViewClient;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;

public class SearchOpac  extends MenuActivity {
	
	public final static String EXTRA_MESSAGE = "test";
	public final static String SEARCH_SCOPE = "keyword";
	public final static String SEARCH_LOCATION = "1";
	public final static String SEARCH_FORMAT = "";

	 WebView myWebView;
	
	final Activity activity = this;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.search_opac);   
       
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
    	String searchURL = "http://libcat.mohawkcollege.ca/eg/opac/results?query=";
    	String searchParams = message
 				.concat("&qtype=").concat(scope)
 				.concat("&fi%3Aitem_type=").concat(format)
 				.concat("&locg=").concat(location);
 		 
         searchURL = searchURL.concat(searchParams);
       
         myWebView = (WebView) findViewById(R.id.webview);
         myWebView.setInitialScale(100);
     //    myWebView.setWebViewClient(new InnerWebViewClient()); // forces it to open in app
       
         
         myWebView.getSettings().setJavaScriptEnabled(true);
         myWebView.getSettings().setPluginsEnabled(true);
         myWebView.setWebChromeClient(new WebChromeClient() {
        	 
        	 
        	   public void onProgressChanged(WebView view, int progress) {
        		  activity.setTitle("Loading...");
               activity.setProgress(progress * 100);

               if(progress == 100)
                   activity.setTitle(R.string.opacSearch);
        	   }
        	 });
         

         /* To ensure links open within the application */
         myWebView.setWebViewClient(new WebViewClient() {
       	  public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
       		Toast.makeText(activity, "Oh no! " + description, Toast.LENGTH_SHORT).show();
       	  }  
       	 @Override
          public boolean shouldOverrideUrlLoading(WebView view, String url)
          {
              view.loadUrl(url);
              return true;
          }
       	 
         @Override
         public void onReceivedSslError (WebView view, SslErrorHandler handler, SslError error) {
             handler.proceed();
         }
       	

       		   });     
         
         myWebView.loadUrl(searchURL); 
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

}