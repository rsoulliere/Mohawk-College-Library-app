package com.rs.MohawkLibrary;

import com.flurry.android.FlurryAgent;
import android.os.Bundle;
import android.webkit.WebView;
import android.app.ActionBar;

public class MohawkLibrary extends MenuActivity {
	
    /** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();       
        actionBar.setDisplayShowHomeEnabled(true);
        setContentView(R.layout.main);
        setContentView(R.layout.webview);
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setInitialScale(100);
        myWebView.loadUrl("file:///android_asset/welcome.html");
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
 
    
}
