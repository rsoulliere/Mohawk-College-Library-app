package com.rs.MohawkLibrary;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Spinner;
import android.widget.Toast;

public class ArticleSearch extends MenuActivity{

	public WebView opacWebView;
	
	final Activity activity = this;
	
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
      this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
      setContentView(R.layout.search_articles);        
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
  
  
  }