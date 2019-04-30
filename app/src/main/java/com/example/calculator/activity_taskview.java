package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.Toast;


public class activity_taskview extends AppCompatActivity {
    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskview);
        Button tasks=(Button)findViewById(R.id.tasksbutton);
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
        })}
    }



}




