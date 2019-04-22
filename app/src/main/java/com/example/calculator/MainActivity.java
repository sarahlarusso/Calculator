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
    int index = 0;
    ImageButton rightButton;
    ImageButton leftButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.personImg);
        nameDisplay = findViewById(R.id.nameText);
        relationDisplay = findViewById(R.id.relationText);
        rightButton = findViewById(R.id.rightButton);
        leftButton = findViewById(R.id.leftButton);



        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        personArrayList = bundle.getParcelableArrayList("Person Array List");

        index = personArrayList.size() - 1;
        Toast toast = Toast.makeText(getApplicationContext(),
                "Array List size: " + personArrayList.size(),
                Toast.LENGTH_LONG);
        toast.show();


        imgData = personArrayList.get(index).getImageData();
        name = personArrayList.get(index).getName();
        relationship = personArrayList.get(index).getRelationship();

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

    public void leftButton(View v){
        index--;
        if (index == -1){
            index = personArrayList.size()-1;
        }

        imgData = personArrayList.get(index).getImageData();
        name = personArrayList.get(index).getName();
        relationship = personArrayList.get(index).getRelationship();


        nameDisplay.setText(name);
        relationDisplay.setText(relationship);

        imageUri = Uri.parse(imgData);
        imageView.setImageURI(imageUri);
    }

    public void rightButton(View v){
        index++;
        if (index == personArrayList.size()){
            index = 0;
        }

        imgData = personArrayList.get(index).getImageData();
        name = personArrayList.get(index).getName();
        relationship = personArrayList.get(index).getRelationship();

        nameDisplay.setText(name);
        relationDisplay.setText(relationship);

        imageUri = Uri.parse(imgData);
        imageView.setImageURI(imageUri);
    }
}