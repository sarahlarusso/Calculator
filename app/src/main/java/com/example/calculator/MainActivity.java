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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    ArrayList<Person> personArrayList;
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
        personArrayList = bundle.getParcelableArrayList("Person Array List");

        int size = personArrayList.size();
        imgData = personArrayList.get(size-1).getImageData();
        name = personArrayList.get(size-1).getName();
        relationship = personArrayList.get(size-1).getRelationship();
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
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imgData = data.getDataString();

        Intent intent = new Intent(this, AddPersonActivity.class);
        intent.putExtra("Person Array List", personArrayList);
        intent.putExtra("Image Data String", imgData);
        startActivity(intent);

    }
}