package com.example.shobhit.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/*import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;*/

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MainActivity extends Activity {
    Button btnGPSShowLocation;
    Button medical;
    AppLocationService appLocationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appLocationService = new AppLocationService(
                MainActivity.this);

        btnGPSShowLocation = (Button) findViewById(R.id.btnGPSShowLocation);
        btnGPSShowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Location gpsLocation = appLocationService
                        .getLocation(LocationManager.GPS_PROVIDER);


                if (gpsLocation != null) {
                    double latitude = gpsLocation.getLatitude();
                    double longitude = gpsLocation.getLongitude();
                    String latitudetxt= ""+latitude;
                    String longitudetxt = ""+longitude;
                    String Type = "Security";

                    new MyAsyncTask().execute(latitudetxt,longitudetxt,Type);

                    Toast.makeText(
                            getApplicationContext(),"Location Coordinates: ("+latitudetxt+" , "+longitudetxt+")",
                            Toast.LENGTH_LONG).show();


                    Toast.makeText(
                            getApplicationContext(),"Processing Your Security Request....",
                            Toast.LENGTH_LONG).show();
                } else {
                    showSettingsAlert("GPS");
                }

            }
        });

       medical = (Button) findViewById(R.id.medical);
       medical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Location gpsLocation = appLocationService
                        .getLocation(LocationManager.GPS_PROVIDER);


                if (gpsLocation != null) {
                    double latitude = gpsLocation.getLatitude();
                    double longitude = gpsLocation.getLongitude();
                    String latitudetxt= ""+latitude;
                    String longitudetxt = ""+longitude;
                    String Type = "Medical";

                    new MyAsyncTask().execute(latitudetxt,longitudetxt,Type);
                     Toast.makeText(
                            getApplicationContext(),"Location Coordinates: ("+latitudetxt+" , "+longitudetxt+")",
                            Toast.LENGTH_LONG).show();
                     Toast.makeText(
                            getApplicationContext(),"Processing Your Medical Request....",
                            Toast.LENGTH_LONG).show();
                } else {
                    showSettingsAlert("GPS");
                }

            }
        });

    }

    public void showSettingsAlert(String provider) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                MainActivity.this);

        alertDialog.setTitle(provider + " SETTINGS");

        alertDialog
                .setMessage(provider + " is not enabled! Want to go to settings menu?");

        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        MainActivity.this.startActivity(intent);
                    }
                });

        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
}
