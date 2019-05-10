package com.example.calculator;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static android.media.MediaRecorder.VideoSource.CAMERA;

/*This page is the home page that opens when the user opens the app
From this page the user can add a person via the camera or the photo gallery,
Or choose to view the existing people in the apps storage if there are any
 */
public class home extends AppCompatActivity implements CameraDialog.CameraDialogListener {

    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        controller = (Controller) getApplicationContext();

        //Opens the dialog box to prompt the user for how they want to select an image
        Button btnCamera = findViewById(R.id.camerabutton);
        btnCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                openCameraDialog();
            }
        });
    }

    public void openCameraDialog(){
        CameraDialog dialog = new CameraDialog();
        dialog.show(getSupportFragmentManager(),"Dialog");
    }

    @Override
    public void onCameraClicked(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    @Override
    public void onGalleryClicked(){
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto , 1);
    }

    //Converts the image data to a String which can be stored in the app
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String imageData = data.getDataString();
        Log.d("IMGDATA",imageData);

        Intent intent = new Intent(this, AddPersonActivity.class);
        intent.putExtra("Image Data String", imageData);
        startActivity(intent);
    }

    //Can only view people if there are people
    public void viewPeople(View v) {
        if (controller.getPersonArrayList().size() != 0){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "You Have No People Yet",
                    Toast.LENGTH_LONG);
            toast.show();
        }
    }
}