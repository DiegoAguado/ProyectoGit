package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String path = sc.next();

        List<Person> list = personList(readFile(path));
        for(Person p:list){
            System.out.println(p);
        }
    }

    public static Stream<String> readFile(String path){ //Lee un archivo según la ruta que le pases
        Stream<String> s;
        Path p= Paths.get(path); //Transforma el String path en un objeto de Path
        try {
            s = Files.lines(p); //Guarda las lineas del archivo con esa ruta
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return s; //Devuelve las lineas
    }

    public static List<Person> personList(Stream<String> st) throws InvalidLineFormatException { //Transforma las lineas del archivo en objetos Persona y los mete a la lista
        List<Person> l = new ArrayList();
        String name = "", town = "", line;
        int age = 0, i = 0;

        List<String> lines = st.toList();

        for (String s : lines) {
            name = "";
            i++;
            line = s;

            if (line.indexOf(":") == -1) {
                throw new InvalidLineFormatException("Format is not correct in line " + i + ". It should be -> name:town:age.");
            } else {
                name = line.substring(0, line.indexOf(":")); //Obtiene el nombre

                if (name == "" || name == " ") {
                    throw new InvalidLineFormatException("Format is not correct in line " + i + ". The name is necessary.");
                } else {
                    line = line.substring(line.indexOf(":") + 1); //Si el nombre no está vacío lo corta
                    if (line.indexOf(":") != -1) {
                        town = line.substring(0, line.indexOf(":")); //Si hay más campos obtiene la ciudad
                        line = line.substring(line.indexOf(":") + 1); //Corta la ciudad
                        if (line == "" || line == " ") {
                            age = 0; //Si lo que queda está vacío da valor 0 a la edad
                        } else {
                            age = Integer.parseInt(line); //Si no le da el valor que tenga
                        }
                    } else {
                        town = line; //Si no da valor a la ciudad
                        age = 0; //Y la edad se queda en 0
                    }
                }
                l.add(new Person(name, town, age)); //Añade la persona a la lista
            }
        }
        return l; //Devuelve la lista
    }
}