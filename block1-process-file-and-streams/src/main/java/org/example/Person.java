package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Person {
    private String name;
    private String town;
    private int age;

    public Person(String name){
        this.name=name;
        this.town="";
        this.age=0;
    }
    public Person(String name, String town){
        this.name=name;
        this.town=town;
        this.age=0;
    }
    public Person(String name, int age){
        this.name=name;
        this.town="";
        this.age=age;
    }
    public Person(String name, String town, int age) {
        this.name = name;
        this.town = town;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", town='" + town + '\'' +
                ", age=" + age +
                '}';
    }
}
