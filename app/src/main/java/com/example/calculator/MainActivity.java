package com.example.calculator;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.CountDownTimer;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

//This activity holds methods for the Main Activity (View People) Screen.
//It includes displaying the most recently added person, and the
//person directly before and after that index.
//It can also open the camera from this screen, to add another person
public class MainActivity extends AppCompatActivity implements CameraDialog.CameraDialogListener{

    private final String TAG = "MainActivity";
    Controller controller;

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

    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = (Controller)getApplicationContext();

        //Creates Image Displays
        imageView = (ImageView) findViewById(R.id.personImg);
        nameDisplay = findViewById(R.id.nameText);
        relationDisplay = findViewById(R.id.relationText);
        rightButton = findViewById(R.id.rightButton);
        leftButton = findViewById(R.id.leftButton);


        //Gets the ArrayList<Person>
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        index = controller.getPersonArrayList().size() - 1;
        Toast toast = Toast.makeText(getApplicationContext(),
                "Amount of People: " + controller.getPersonArrayList().size(),
                Toast.LENGTH_LONG);

        toast.show();

        //Displays the Information of the most recently added Person
        imgData = controller.getPersonArrayList().get(index).getImageData();
        name = controller.getPersonArrayList().get(index).getName();
        relationship = controller.getPersonArrayList().get(index).getRelationship();

        nameDisplay.setText(name);
        relationDisplay.setText(relationship);

        imageUri = Uri.parse(imgData);
        imageView.setImageURI(imageUri);

        //Opens Camera
        ImageButton addPerson = findViewById(R.id.AddPersonButton);
        addPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCameraDialog();
            }
        });

        startTimer();
    }

    public void startTimer(){
        timer = new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                View v = new View(getApplicationContext());
                rightButton(v);
            }
        }.start();
    }

    public void stopTimer(){
        timer.cancel();
    }

    public void openCameraDialog(){
        CameraDialog dialog = new CameraDialog();
        dialog.show(getSupportFragmentManager(),"Dialog");
    }

    @Override
    public void onCameraClicked(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    @Override
    public void onGalleryClicked(){
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto , 1);
    }

    //Opens Camera (Continued)
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imgData = data.getDataString();

        Intent intent = new Intent(this, AddPersonActivity.class);
//        intent.putExtra("Person Array List", personArrayList);
        intent.putExtra("Image Data String", imgData);
        startActivity(intent);
    }

    //Displays the Person at the current index - 1
    public void leftButton(View v) {

        stopTimer();

        index--;
        if (index == -1) {
            index = controller.getPersonArrayList().size() - 1;
        }

        imgData = controller.getPersonArrayList().get(index).getImageData();
        name = controller.getPersonArrayList().get(index).getName();
        relationship = controller.getPersonArrayList().get(index).getRelationship();

        nameDisplay.setText(name);
        relationDisplay.setText(relationship);

        imageUri = Uri.parse(imgData);
        imageView.setImageURI(imageUri);

        startTimer();
    }


    //Displays the Person at the current index + 1
    public void rightButton(View v) {

        stopTimer();

        index++;
        if (index == controller.getPersonArrayList().size()) {
            index = 0;
        }

        imgData = controller.getPersonArrayList().get(index).getImageData();
        name = controller.getPersonArrayList().get(index).getName();
        relationship = controller.getPersonArrayList().get(index).getRelationship();

        nameDisplay.setText(name);
        relationDisplay.setText(relationship);

        imageUri = Uri.parse(imgData);
        imageView.setImageURI(imageUri);

        startTimer();
    }

    public void deleteButton(View v) {
        if (controller.getPersonArrayList().size() == 1) {
            controller.deletePerson(index);
            stopTimer();

            Intent intent = new Intent(this, home.class);
            startActivity(intent);
        }
        if (controller.getPersonArrayList().size() > 1) {
            controller.deletePerson(index);
            leftButton(v);
        }
    }

    //TEXT FILE IMAGE SAVE CODE
    protected void onStop() {

        String saveText = "";
        for (Person p : controller.getPersonArrayList()) {
            saveText += p.getName() + "," + p.getRelationship() + "," + p.getImageData();
            saveText += "\n";
        }

        FileOutputStream fileOutput = null;
        String outputFilename = "memoryTextFile.txt";
        try {
            fileOutput = openFileOutput(outputFilename, MODE_PRIVATE);
            fileOutput.write(saveText.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast toast = Toast.makeText(getApplicationContext(),
                    "File not found",
                    Toast.LENGTH_LONG);
            toast.show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Error",
                    Toast.LENGTH_LONG);
            toast.show();
        } finally {
            if (fileOutput != null) {
                try {
                    fileOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        super.onStop();
    }
}




