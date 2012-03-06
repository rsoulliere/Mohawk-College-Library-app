package com.rs.MohawkLibrary;

import java.util.List;
import com.google.android.maps.*;
import com.rs.MohawkLibrary.R;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

public class MohawkLibraryMap extends MapActivity {
    /** Called when the activity is first created. */
	  MapController mc;
	  GeoPoint p;

	  
	  @Override
		protected boolean isRouteDisplayed() {
		    return false;
		}
	  
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
   
        MapView mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        mapView.setSatellite(false);
        
        List<Overlay> mapOverlays = mapView.getOverlays();
        Drawable drawable = this.getResources().getDrawable(R.drawable.library);
        drawable.setAlpha(128);
        MohawkItemizedOverlay itemizedoverlay = new MohawkItemizedOverlay(drawable, this); 
        
        mc = mapView.getController();
        String coordinates[] = {"43.238100", "-79.800000"};
        double lat = Double.parseDouble(coordinates[0]);
        double lng = Double.parseDouble(coordinates[1]);
 
        p = new GeoPoint(
            (int) (lat * 1E6), 
            (int) (lng * 1E6));
 
        mc.animateTo(p);
        mc.setZoom(10); 
        mapView.invalidate();
        
        GeoPoint point = new GeoPoint(43239100,-79884900);
        OverlayItem overlayitem = new OverlayItem(point, "Fennell Library", "135 Fennell Avenue West, Hamilton, ON L9C 1E9 ");
        GeoPoint point3 = new GeoPoint(43258130,-79919260);
        OverlayItem overlayitem3 = new OverlayItem(point3, "IAHS", "400 Main Street West, Hamilton, ON L8S 1C7");
        GeoPoint point4 = new GeoPoint(43226188,-79712891);
        OverlayItem overlayitem4 = new OverlayItem(point4, "STAART", "481 Barton Street East, Stoney Creek, ON L8E 2L7");
        
       itemizedoverlay.addOverlay(overlayitem);
       itemizedoverlay.addOverlay(overlayitem3);
       itemizedoverlay.addOverlay(overlayitem4);
       mapOverlays.add(itemizedoverlay);
  	 
	}


	
}