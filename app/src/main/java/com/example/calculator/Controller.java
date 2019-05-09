package com.example.calculator;

import android.app.Application;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Controller extends Application {
    private ArrayList<Person> personArrayList;
    String fileName = "memoryTextFile.txt";

    @Override
    public void onCreate(){
        super.onCreate();

        personArrayList = new ArrayList<Person>();

        FileInputStream inputStream = null;
        try {
            inputStream = openFileInput(fileName);
            InputStreamReader streamReader = new InputStreamReader(inputStream);

            BufferedReader bufferedReader = new BufferedReader(streamReader);
            String line = "";

            while ((line = bufferedReader.readLine()) != null){
                String[] fields = line.split(",");
                personArrayList.add(new Person(fields[0],fields[1],fields[2]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast toast = Toast.makeText(getApplicationContext(),
                    "File not found",
                    Toast.LENGTH_LONG);
            toast.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Person> getPersonArrayList() {
        return personArrayList;
    }

    public void deletePerson(int index) {
        personArrayList.remove(index);
    }

    public void addPerson(Person person) {
        personArrayList.add(person);
    }

}