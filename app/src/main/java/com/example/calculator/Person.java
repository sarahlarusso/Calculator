package com.example.calculator;

public class Person {
    String name;
    String relationship;
    String imageData;

    public Person (String name, String relationship, String imageData){
        this.name = name;
        this.relationship = relationship;
        this.imageData = imageData;
    }

    public String getName() {
        return name;
    }

    public String getRelationship() {
        return relationship;
    }

    public String getImageData() {
        return imageData;
    }
}
