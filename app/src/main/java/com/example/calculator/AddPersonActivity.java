package com.example.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddPersonActivity extends AppCompatActivity {
    //push git-nonnie

    private String imgData;
    ArrayList<Person> personArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        imgData = bundle.getString("Image Data String");
        personArrayList = bundle.getParcelableArrayList("Person Array List");
    }

    public void addPerson (View v) {
        EditText name = findViewById(R.id.apNametxt);
        EditText relation = findViewById(R.id.apRelationtxt);

        String nameStr = name.getText().toString();
        String relationStr = relation.getText().toString();

        personArrayList.add(new Person(nameStr,relationStr,imgData));

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Person Array List", personArrayList);
        startActivity(intent);

//        String TAG = "Add Person Page";
//        Log.d(TAG, "User input: " + nameStr);
//        Log.d(TAG, "User input: " + relationStr);

    }

    public void cancel (View v) {
        if (personArrayList!=null){
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("Person Array List", personArrayList);
            startActivity(intent);


        }

    }
}


