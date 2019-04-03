package com.example.calculator;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class home extends AppCompatActivity {

    ArrayList<Person> personArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        personArrayList = new ArrayList<Person>();


        Button btnCamera = findViewById(R.id.camerabutton);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String imageData = data.getDataString();

        Intent intent = new Intent(this, AddPersonActivity.class);
        intent.putExtra("Image Data String", imageData);
        intent.putExtra("Person Array List", personArrayList);
        startActivity(intent);

    }
}
