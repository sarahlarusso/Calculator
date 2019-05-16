package com.example.calculator;

import android.os.Parcel;
import android.os.Parcelable;

//The Person class holds the data for each individual contact the user adds/has
public class Person implements Parcelable {
    private String name;
    private String relationship;
    private String imageData;

    public Person (String name, String relationship, String imageData){
        this.name = name;
        this.relationship = relationship;
        this.imageData = imageData;
    }

    //Parcelable implementation allows objects of type Person to be sent between classes
    protected Person(Parcel in) {
        name = in.readString();
        relationship = in.readString();
        imageData = in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getRelationship() {
        return relationship;
    }

    public String getImageData() {
        return imageData;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(relationship);
        dest.writeString(imageData);
    }
}
