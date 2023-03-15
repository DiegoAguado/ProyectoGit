package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String path = sc.next();
        try {
            List<Person> list = personList(path);
            for(Person p:list){
                System.out.println(p);
            }
        } catch (IOException e) {
            System.out.println(e.getCause());
            System.out.println(e.getLocalizedMessage());
        }
    }
    public static List<Person> personList(String path) throws IOException {
        List<Person> l = new ArrayList<>();
        String name,town, line;
        int age;
        Path p = Paths.get(path);
        Stream<String> f=Files.lines(p);
        List<String> lines = f.toList();
        for(int i=0;i<lines.size();i++){
            line=lines.get(i);
            name=line.substring(0,line.indexOf(":"));
            line=line.substring(line.indexOf(":")+1);
            town=line.substring(0, line.indexOf(":"));
            line=line.substring(line.indexOf(":")+1);
            if(line=="" || line==" "){
                age=0;
            }else{
                age=Integer.parseInt(line);
            }
            l.add(new Person(name));
        }
        return l;
    }
}