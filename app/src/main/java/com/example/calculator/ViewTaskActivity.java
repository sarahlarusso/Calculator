package com.example.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewTaskActivity extends AppCompatActivity {
    ArrayList<Person> personArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tasks);

        Intent intent = getIntent();
    }

    public void viewPeople(View v) {
        if (personArrayList.size() != 0){
            //Brings to View People
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("Person Array List", personArrayList);
            startActivity(intent);
        }
        else {
            //Says that there are no people yet
            Toast toast = Toast.makeText(getApplicationContext(),
                    "You Have No People Yet",
                    Toast.LENGTH_LONG);
            toast.show();
        }

    }

    public void addTask(View v){
        Button addtasks3 = findViewById(R.id.addtaskbutton);
        addtasks3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddTaskActivity.class);
                intent.putExtra("Person Array List", personArrayList);
                v.getContext().startActivity(intent);
            }

        });

    }}