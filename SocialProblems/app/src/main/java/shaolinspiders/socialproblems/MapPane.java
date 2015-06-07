package shaolinspiders.socialproblems;
//
//import android.app.Dialog;
//import android.view.Menu;
//
//import android.location.Criteria;
//import android.location.Location;
//import android.location.LocationListener;
//import android.location.LocationManager;
//import android.os.Bundle;
//import android.support.v4.app.FragmentActivity;
//import android.view.Menu;
//import android.widget.TextView;
//
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.GooglePlayServicesUtil;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.LatLng;
//
//public class MapPane extends FragmentActivity implements LocationListener {
//
//    GoogleMap googleMap;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.maps);
//        // Getting Google Play availability status
//        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
//
//        // Showing status
//        if(status!= ConnectionResult.SUCCESS){ // Google Play Services are not available
//
//            int requestCode = 10;
//            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
//            dialog.show();
//
//        }else { // Google Play Services are available
//
//            // Getting reference to the SupportMapFragment of activity_main.xml
//            SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//
//            // Getting GoogleMap object from the fragment
//            googleMap = fm.getMap();
//
//            // Enabling MyLocation Layer of Google Map
//            googleMap.setMyLocationEnabled(true);
//
//            // Getting LocationManager object from System Service LOCATION_SERVICE
//            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//
//            // Creating a criteria object to retrieve provider
//            Criteria criteria = new Criteria();
//
//            // Getting the name of the best provider
//            String provider = locationManager.getBestProvider(criteria, true);
//
//            // Getting Current Location
//            Location location = locationManager.getLastKnownLocation(provider);
//
//            if(location!=null){
//                onLocationChanged(location);
//            }
//            locationManager.requestLocationUpdates(provider, 20000, 0, this);
//        }
//    }
//    @Override
//    public void onLocationChanged(Location location) {
//
//        TextView tvLocation = (TextView) findViewById(R.id.tv_location);
//
//        // Getting latitude of the current location
//        double latitude = location.getLatitude();
//
//        // Getting longitude of the current location
//        double longitude = location.getLongitude();
//
//        // Creating a LatLng object for the current location
//        LatLng latLng = new LatLng(latitude, longitude);
//
//        // Showing the current location in Google Map
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//
//        // Zoom in the Google Map
//        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
//
//        // Setting latitude and longitude in the TextView tv_location
//        tvLocation.setText("Latitude:" +  latitude  + ", Longitude:"+ longitude );
//
//    }
//
//    @Override
//    public void onProviderDisabled(String provider) {
//        // TODO Auto-generated method stub
//    }
//
//    @Override
//    public void onProviderEnabled(String provider) {
//        // TODO Auto-generated method stub
//    }
//
//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {
//        // TODO Auto-generated method stub
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//}

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import android.app.ActionBar;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import http.HttpPostRequest;

/**
 * Created by dimmat97 on 6/6/15.
 */

public class MapPane extends Activity {

    String longitude;
    String latitude;
    Button getPosition;
    Button submit;
    EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps);
        setIds();
        submit.setOnClickListener(submit_listener);
    }

    View.OnClickListener submit_listener = new View.OnClickListener() {
        public void onClick(View v) {
            String url = getApplicationContext().getResources().getString(R.string.website_url_send_signal);
            new HttpRequest().execute(url);
        }
    };

    void setIds() {
        getPosition = (Button) findViewById(R.id.current_location);
        submit = (Button) findViewById(R.id.send_signal);
        description = (EditText) findViewById(R.id.description);
    }


    private String get_token() {
        return getSharedPreferences("token", MODE_PRIVATE).getString("token", "");
    }

    public class HttpRequest extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... uri) {
            JSONObject params = new JSONObject();
            try {
                params.put("description", description.getText().toString());
                params.put("lat_one", "12");
                params.put("lat_two", "22");
                params.put("token", get_token());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return HttpPostRequest.make_request(uri[0], params);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }

}
