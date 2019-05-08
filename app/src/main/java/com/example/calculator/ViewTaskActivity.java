package com.example.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewTaskActivity extends AppCompatActivity {

    ArrayList<String> tasksArrayList;
    String tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tasks);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        tasksArrayList = bundle.getStringArrayList("Tasks Array List");

        if (tasksArrayList.size() != 0){
            for (String s: tasksArrayList){
                tasks += s;
                tasks += "\n";
            }
        }

        TextView textView = (TextView) findViewById(R.id.textView4);
        textView.setText(tasks);
    }

    public void goHome(View v) {
            Intent intent = new Intent(this, home.class);
            startActivity(intent);
    }

    public void addTask(View v){
                Intent intent = new Intent(v.getContext(), AddTaskActivity.class);
                intent.putExtra("Tasks Array List", tasksArrayList);
                v.getContext().startActivity(intent);
    }
}