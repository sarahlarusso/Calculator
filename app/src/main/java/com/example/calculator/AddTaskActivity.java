package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;


public class AddTaskActivity extends AppCompatActivity {
    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Intent intent = getIntent();
    }

    public void okaytasks(View v){
                Intent intent = new Intent(v.getContext(), ViewTaskActivity.class);
                v.getContext().startActivity(intent);
    }

    public void onCheckboxClicked(View v) {

        boolean checked = ((CheckBox) v).isChecked();


        switch(v.getId()) {
            case R.id.checkBox1:
                if (checked) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "You have to do this task",
                            Toast.LENGTH_LONG); 
                    toast.show();
                }
            case R.id.checkBox2:
                if (checked){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "You have to do this task",
                            Toast.LENGTH_LONG);
                toast.show();}

            case R.id.checkBox3:
                if (checked){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "You have to do this task",
                            Toast.LENGTH_LONG);
                    toast.show();}
            case R.id.checkBox4:
                if (checked){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "You have to do this task",
                            Toast.LENGTH_LONG);
                    toast.show();}

                break;

        }


    }


            }










