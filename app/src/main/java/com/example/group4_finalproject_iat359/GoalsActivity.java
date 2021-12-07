//this class will handle inputs from the user
//currently this class can set the amount of steps that the user wants to set up as a goal

package com.example.group4_finalproject_iat359;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

//pew pew test test
public class GoalsActivity extends AppCompatActivity {
    private Integer goal;
    private EditText stepGoalEditText, destinationEditText;
    private String locationString, latString, lngString;
    MyDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goals);
        stepGoalEditText = (EditText)findViewById(R.id.stepGoalEditText);
        destinationEditText = (EditText)findViewById(R.id.destinationEditText);
        db = new MyDatabase(this);


    }

    //searching for destination
//    public void setGoals(View v) {
//        Geocoder myGeocoder = new Geocoder(this);
//
//        hideSoftKeyboard(v);
//
//        if (v.getId() == R.id.setGoalsButton){
//            locationString = destinationEditText.getText().toString();
//            Toast.makeText(this, "Searching for " + locationString, Toast.LENGTH_SHORT).show();
//
//            List<Address> list = null;
//            try {
//                list = myGeocoder.getFromLocationName(locationString, 1);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            if (list.size() >0){
//                Address add = list.get(0);
//                String locality = add.getLocality();
//                Toast.makeText(this, "Found " + locality, Toast.LENGTH_SHORT).show();
//
//                double lat = add.getLatitude();
//                double lng = add.getLongitude();
//                gotoLocation(lat, lng, 15);
//
////                MarkerOptions options = new MarkerOptions()
////                        .title(locality)
////                        .position(new LatLng(lat, lng));
////                mMap.addMarker(options);
//            }
//        }

//        if (v.getId() == R.id.latLngButton){
//            latString = latEntry.getText().toString();
//            lngString = lngEntry.getText().toString();
//            Toast.makeText(this, "Searching for " + latString + " , " + lngString, Toast.LENGTH_SHORT).show();
//
//            List <Address> list = null;
//            try {
//                list = myGeocoder.getFromLocation(Double.parseDouble(latString), Double.parseDouble(lngString), 1);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            if (list.size() >0){
//                Address add = list.get(0);
//                String locality = add.getLocality();
//                Toast.makeText(this, "Found " + locality, Toast.LENGTH_SHORT).show();
//
//                double lat = add.getLatitude();
//                double lng = add.getLongitude();
//                gotoLocation(lat, lng, 15);
//
//                MarkerOptions options = new MarkerOptions()
//                        .title(locality)
//                        .position(new LatLng(lat, lng));
//                myMap.addMarker(options);
//            }
//        }
//    }

    //just lat lng
    private void gotoLocation(double lat, double lng) {
        LatLng latlng = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLng(latlng);
//        mMap.moveCamera(update);
    }

    //lan lng and zoom
    private void gotoLocation(double lat, double lng, float zoom) {
        LatLng latlng = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latlng, zoom);
//        mMap.moveCamera(update);
    }

    private void hideSoftKeyboard(View v) {
        InputMethodManager imm =
                (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    //this method will take the input from the user as a string and then parse it as an int
    //this will then be placed within a sharedpreference method which then be passed to the main activity
    public void setGoals (View view) {
//        Geocoder myGeocoder = new Geocoder(this);
//        hideSoftKeyboard(view);
//        if (view.getId() == R.id.setGoalsButton) {
//            locationString = destinationEditText.getText().toString();
//            Toast.makeText(this, "Searching for " + locationString, Toast.LENGTH_SHORT).show();
//            List<Address> list = null;
//            try {
//                list = myGeocoder.getFromLocationName(locationString, 1);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            if (list.size() > 0) {
//                Address add = list.get(0);
//                String locality = add.getLocality();
//                Toast.makeText(this, "Found " + locality, Toast.LENGTH_SHORT).show();
//
//                double lat = add.getLatitude();
//                double lng = add.getLongitude();
//                gotoLocation(lat, lng, 15);
//
////                MarkerOptions options = new MarkerOptions()
////                        .title(locality)
////                        .position(new LatLng(lat, lng));
////                mMap.addMarker(options);
//            }
//        }

        SharedPreferences sharedPrefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
//        editor.putString("inputtedStepGoal", stepGoalEditText.getText().toString());
        goal = Integer.parseInt(stepGoalEditText.getText().toString());
        editor.putInt("inputtedStepGoal", goal);
        editor.commit();

        //storing the goals in database
//        String name = plantName.getText().toString();
//        String type = plantType.getText().toString();
//        String plantLocation = location.getText().toString();
//        String latinName = plantLatinName.getText().toString();
//        Toast.makeText(this, name +  " " + type + " " + plantLocation + " " + latinName, Toast.LENGTH_SHORT).show();
//        long id = db.insertData(goal);
        long id = db.insertData(goal);

        if (id < 0)
        {
            Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(this, "Goals saved. Heading to the Tracking page.", Toast.LENGTH_LONG).show();
        Intent intent= new Intent(this, com.example.group4_finalproject_iat359.MainActivity.class);
        startActivity(intent);
    }

    public void goToHome(View v) {
        Intent intent = new Intent(this, com.example.group4_finalproject_iat359.MainActivity.class);
        startActivity(intent);
    }

    public void goToProfile(View v) {
        Intent intent = new Intent(this, com.example.group4_finalproject_iat359.ProfileStats.class);
        startActivity(intent);
    }

}
