package com.rs.MohawkLibrary;

import com.flurry.android.FlurryAgent;

import android.os.Bundle;
//import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
//import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.app.Activity;
//import android.app.ActionBar;



public class MohawkLibrary extends Activity{
	
//	 LinearLayout mLinearLayout;

//	  WebView myWebView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setContentView(R.layout.webview);
      WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setInitialScale(100);
        myWebView.loadUrl("file:///android_asset/welcome.html");
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
    	switch (item.getItemId()) {
	    	
    	case android.R.id.home:
   		myWebView.setInitialScale(100);
        	myWebView.loadUrl("file:///android_asset/welcome.html");
	    	break;
	    	
    	case R.id.opacSearch:
    		myWebView.setInitialScale(100);
        	myWebView.loadUrl("http://libcat.mohawkcollege.ca/opac/en-CA/extras/slimpac/mobile2.html");
	    	break;
	    	
    	case R.id.articleSearch:
    		myWebView.setInitialScale(100);
        	myWebView.loadUrl("http://lib-wind.mohawkcollege.ca/mobile/databases.html");
	    	break;
	    	
    	case R.id.room_reserve:
    		myWebView.setInitialScale(50);
    		myWebView.loadUrl("http://lib-drupal.mohawkcollege.ca/forms/Work/BookRoomMobile/mobile.php");
    		break; 
	    	
    	case R.id.brainblog:
    		myWebView.setInitialScale(100);
    		myWebView.loadUrl("http://librarybrainblog.wordpress.com");
    		break; 
    		
    	case R.id.collabblog:
    		myWebView.setInitialScale(75);
    		myWebView.loadUrl("http://mohawkcollaboratory.tumblr.com/");
    		break; 
    		

    	case R.id.chat:
    		myWebView.setInitialScale(100);
    		 myWebView.loadUrl("http://lib-wind.mohawkcollege.ca/Chat/library_mobile.html");
	    	break;  	
	    	
    	case R.id.about:
    		myWebView.setInitialScale(100);
    		 myWebView.loadUrl("file:///android_asset/welcome.html");
	    	break;  
 
	    
  //  	case R.id.viewMap:
	//    	Intent intent2 = new Intent(this, MapTest.class); 
	//    	startActivity(intent2);
	   // 	break;
	    	
	    	
	    default:
	        return super.onOptionsItemSelected(item);
	    }
    	return true;
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
    
    public void onStart()
    {
       super.onStart();
       FlurryAgent.onStartSession(this, "HMCFZISWNNVAD1QI2RV3");
       // your code
    }
    
    public void onStop()
    {
       super.onStop();
       FlurryAgent.onEndSession(this);
       // your code
    }
    
  //  private class MohawkWebViewClient extends WebViewClient {
 //       @Override
   //     public boolean shouldOverrideUrlLoading(WebView view, String url) {
    //        view.loadUrl(url);
    //        return true;
    //    }
   // }
    
    
    
}
