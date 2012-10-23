package com.rs.MohawkLibrary;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient.CustomViewCallback;

public class MenuActivity extends Activity{

	
   
	 @Override
		public boolean onCreateOptionsMenu(Menu menu) {
		    MenuInflater inflater = getMenuInflater();
		    inflater.inflate(R.layout.main_menu, menu);
		    return true;
		}
		
	    @Override
		public boolean onOptionsItemSelected(MenuItem item) {
	    		
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
	    		intent = new Intent(this, LibraryChat.class);
	    		startActivity(intent);
		    	break;
		    	
	    	case R.id.hours:
	    		intent = new Intent(this,HoursView.class);
	    		startActivity(intent);
		    	break;  
		    	
	    	case R.id.about:
	    		intent = new Intent(this, MohawkLibrary.class);
	     		startActivity(intent);
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

		public void onShowCustomView(View view, CustomViewCallback callback) {
			// TODO Auto-generated method stub
			
		}
	    
	  
	
}
