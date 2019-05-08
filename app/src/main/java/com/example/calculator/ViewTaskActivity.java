package com.example.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewTaskActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tasks);

        Intent intent = getIntent();
    }

    public void goHome(View v) {
            Intent intent = new Intent(this, home.class);
            startActivity(intent);
    }

    public void addTask(View v){
                Intent intent = new Intent(v.getContext(), AddTaskActivity.class);
                v.getContext().startActivity(intent);
    }
}