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
        List<Person> list = personList(path);
        for(Person p:list){
            System.out.println(p);
        }
    }
    public static List<Person> personList(String path) throws InvalidLineFormatException {
        List<Person> l = new ArrayList<>();
        String name = "", town = "", line;
        int age = 0, i = 0;
        Path p = Paths.get(path);
        Stream<String> f = null;
        try {
            f = Files.lines(p);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<String> lines = f.toList();
        for (String s : lines) {
            name = "";
            i++;
            line = s;
            if (line.indexOf(":") == -1) {
                throw new InvalidLineFormatException("Format is not correct in line " + i + ". It should be -> name:town:age.");
            } else {
                name = line.substring(0, line.indexOf(":"));
                if (name == "" || name == " ") {
                    throw new InvalidLineFormatException("Format is not correct in line " + i + ". The name is necessary.");
                } else {
                    line = line.substring(line.indexOf(":") + 1);
                    if (line.indexOf(":") != -1) {
                        town = line.substring(0, line.indexOf(":"));
                        line = line.substring(line.indexOf(":") + 1);
                        if (line == "" || line == " ") {
                            age = 0;
                        } else {
                            age = Integer.parseInt(line);
                        }
                    } else {
                        town = line;
                        age = 0;
                    }
                }
                l.add(new Person(name, town, age));
            }
        }
        return l;
    }
}