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

//This activity holds methods for the Main Activity (View People) Screen.
//It includes displaying the most recently added person, and the
//person directly before and after that index.
//It can also open the camera from this screen, to add another person
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
    Button deleteButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creates Image Displays
        imageView = (ImageView) findViewById(R.id.personImg);
        nameDisplay = findViewById(R.id.nameText);
        relationDisplay = findViewById(R.id.relationText);
        rightButton = findViewById(R.id.rightButton);
        leftButton = findViewById(R.id.leftButton);
        deleteButton=findViewById(R.id.delete1);


        //Gets the ArrayList<Person>
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        personArrayList = bundle.getParcelableArrayList("Person Array List");

        index = personArrayList.size() - 1;
        Toast toast = Toast.makeText(getApplicationContext(),
                "Amount of People:" + personArrayList.size(),
                Toast.LENGTH_LONG);

        toast.show();

        //Displays the Information of the most recently added Person
        imgData = personArrayList.get(index).getImageData();
        name = personArrayList.get(index).getName();
        relationship = personArrayList.get(index).getRelationship();

        nameDisplay.setText(name);
        relationDisplay.setText(relationship);

        imageUri = Uri.parse(imgData);
        imageView.setImageURI(imageUri);

        //Opens Camera
        ImageButton addPerson = findViewById(R.id.AddPersonButton);
        addPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });
    }

    //Opens Camera (Continued)
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imgData = data.getDataString();

        Intent intent = new Intent(this, AddPersonActivity.class);
        intent.putExtra("Person Array List", personArrayList);
        intent.putExtra("Image Data String", imgData);
        startActivity(intent);

    }


    //Displays the Person at the current index - 1
    public void leftButton(View v){
        index--;
        if (index == -1){
            index = personArrayList.size()-1;
            //go through the array list from the end, displaying people
        }

        imgData = personArrayList.get(index).getImageData();
        name = personArrayList.get(index).getName();
        relationship = personArrayList.get(index).getRelationship();


        nameDisplay.setText(name);
        relationDisplay.setText(relationship);

        imageUri = Uri.parse(imgData);
        imageView.setImageURI(imageUri);
    }

    //Displays the Person at the current index + 1
    public void rightButton(View v){
        index++;
        if (index == personArrayList.size()){
            index = 0;
        }

        imgData = personArrayList.get(index).getImageData();
        name = personArrayList.get(index).getName();
        relationship = personArrayList.get(index).getRelationship();
        //retrieve the information on the text

        nameDisplay.setText(name);
        relationDisplay.setText(relationship);

        imageUri = Uri.parse(imgData);
        imageView.setImageURI(imageUri);
    }


    //Deletes the person from the arraylist
    public void deleteButton (View v) {
        if(personArrayList.size()==1) {
            personArrayList.remove(index);
            Intent intent = new Intent(this, home.class);
            startActivity(intent);
        }
            if ( personArrayList.size()>1) {

            personArrayList.remove(index);
            leftButton(v);

        }

            }
    public void gotoTasks(View v){

        Button addT = findViewById(R.id.addtasks8);
        addT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddTaskActivity.class);
                intent.putExtra("Person Array List", personArrayList);
                v.getContext().startActivity(intent);
            }
        });
    }

}





