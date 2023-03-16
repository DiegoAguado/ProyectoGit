package org.example;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Name: "+this.name+". "+"Town: "+this.town+". "+"Age: "+this.age;
    }

    public boolean equals(Object o){
        Person p = (Person)o;
        if(this.name==p.getName() && this.town==p.getTown() && this.age==p.getAge())return true;
        else return false;
    }
}
