package com.example.group4_finalproject_iat359;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

//page for displaying the first page of the tutorial
public class TutorialThree extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial_3);
    }

    public void tutButton (View view) {
        Intent intent= new Intent(this, com.example.group4_finalproject_iat359.TutorialFour.class);
        startActivity(intent);
    }

    public void goBack (View view) {
        Intent intent = new Intent(this,com.example.group4_finalproject_iat359.TutorialTwo.class );
        startActivity(intent);
    }
}