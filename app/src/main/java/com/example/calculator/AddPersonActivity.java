package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AddPersonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
    }

    public void addPerson (View v) {
        EditText name = findViewById(R.id.txtName);
        EditText relation = findViewById(R.id.txtRelation);

        String nameStr = name.getText().toString();
        String relationStr = relation.getText().toString();

        String TAG = "Add Person Page";
        Log.d(TAG, "User input: " + nameStr);
        Log.d(TAG, "User input: " + relationStr);

    }



    }
