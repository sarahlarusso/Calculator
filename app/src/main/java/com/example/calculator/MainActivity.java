package com.example.calculator;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private String imgData;
    private String name;
    private String relationship;
    ImageView imageView;
    TextView nameDisplay;
    TextView relationDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.personImg);
        nameDisplay = findViewById(R.id.nameText);
        relationDisplay = findViewById(R.id.relationText);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        imgData = bundle.getString("Image Data");
        name = bundle.getString("Name");
        relationship = bundle.getString("Relationship");

        nameDisplay.setText(name);
        relationDisplay.setText(relationship);

        Uri imageUri = Uri.parse(imgData);
        imageView.setImageURI(imageUri);

        Toast toast = Toast.makeText(getApplicationContext(),
                "AYAYYYYYAYA Yurrrrd",
                Toast.LENGTH_LONG);
        toast.show();

        Log.d(TAG, name);
        Log.d(TAG, relationship);
        Log.d(TAG, imgData);
    }
}