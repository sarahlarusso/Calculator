package com.example.calculator;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
    Uri imageUri;


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
        imageUri = Uri.parse(imgData);
        imageView.setImageURI(imageUri);


        ImageButton addPerson = findViewById(R.id.AddPersonButton);
        addPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });


//        Toast toast = Toast.makeText(getApplicationContext(),
//                "AYAYYYYYAYA Yurrrrd",
//                Toast.LENGTH_LONG);
//        toast.show();
//        Log.d(TAG, name);
//        Log.d(TAG, relationship);
//        Log.d(TAG, imgData);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imgData = data.getDataString();

        Intent intent = new Intent(this, AddPersonActivity.class);
        intent.putExtra("Image Data String", imgData);
        startActivity(intent);

    }
}