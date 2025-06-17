package lab.cahcet.edu.gpstracker;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TrackLocation extends Activity implements LocationListener {
    private TextView latitude, latitudeField;
    private TextView longitude, longitudeField;
    private LocationManager locationManager;
    private String provider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_location);

        // Initializing UI components
        latitude = findViewById(R.id.Latitude);
        longitude = findViewById(R.id.Longitude);
        latitudeField = findViewById(R.id.Latitude_degree);
        longitudeField = findViewById(R.id.Longitude_degree);

        latitude.setVisibility(View.INVISIBLE);
        longitude.setVisibility(View.INVISIBLE);
        latitudeField.setVisibility(View.INVISIBLE);
        longitudeField.setVisibility(View.INVISIBLE);

        // Getting location manager and provider
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);

        try {
            Location location = locationManager.getLastKnownLocation(provider);
            if (location != null) {
                onLocationChanged(location);
            } else {
                latitudeField.setText("Location not available");
                longitudeField.setText("Location not available");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onclickTrack(View view) {
        latitude.setVisibility(View.VISIBLE);
        longitude.setVisibility(View.VISIBLE);
        latitudeField.setVisibility(View.VISIBLE);
        longitudeField.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            locationManager.requestLocationUpdates(provider, 10, 1, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            locationManager.removeUpdates(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        double lat = location.getLatitude();
        double lng = location.getLongitude();
        latitudeField.setText(String.valueOf(lat));
        longitudeField.setText(String.valueOf(lng));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Toast.makeText(this, provider + " Status: " + status, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Enabled: " + provider, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Disabled: " + provider, Toast.LENGTH_SHORT).show();
    }
}
