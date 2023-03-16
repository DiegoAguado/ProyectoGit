package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String path = sc.next();

        List<Person> list = personList(path);
        //filterNames(list);
        //list = filterAges(list);
        //System.out.println(filterMadrid(list));
        //System.out.println(filterBarcelona(list));
        for (Person p : list) System.out.println(p);
    }

    //Método que pasada una ruta de un fichero, lee el fichero y devuelve una lista de personas
    public static List<Person> personList(String path) throws InvalidLineFormatException {
        List<Person> l = new ArrayList();
        String name = "", town = "", line;
        int age = 0, i = 0;
        Path p = Paths.get(path);

        try {
            for (String s : Files.readAllLines(p)) {
                name = "";
                i++;
                line = s;

                if (line.indexOf(":") == -1)
                    throw new InvalidLineFormatException("Format is not correct in line " + i + ". It should be -> name:town:age.");

                name = line.substring(0, line.indexOf(":")); //Obtiene el nombre

                if (name == "" || name == " ")
                    throw new InvalidLineFormatException("Format is not correct in line " + i + ". The name is necessary.");

                line = line.substring(line.indexOf(":") + 1); //Si el nombre no está vacío lo corta
                if (line.indexOf(":") != -1) {
                    town = line.substring(0, line.indexOf(":")); //Si hay más campos obtiene la ciudad
                    line = line.substring(line.indexOf(":") + 1); //Corta la ciudad
                    age = (line == "" || line == " ") ? 0 : Integer.parseInt(line);
                } else {
                    town = line; //Si no da valor a la ciudad
                    age = 0; //Y la edad se queda en 0
                }

                l.add(new Person(name, town, age)); //Añade la persona a la lista
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return l; //Devuelve la lista de personas del fichero
    }

    //Método que devuelve una lista pasada por parámetro pero las ciudades vacías pasan a contener 'unknown'
    public static List<Person> unknownTown(List<Person> list) {
        for (Person p : list) {
            if (p.getTown() == "" || p.getTown() == " ")
                p.setTown("unknown"); //Si la ciudad está vacía la cambia a 'unknown'
        }

        return list; //Devuelve la misma lista pero sin ciudades vacías
    }

    //Método que dada una lista, devuelve otra con personas con edad introducida y menores de 25 años
    public static List<Person> filterAges(List<Person> list) {
        return unknownTown(list.stream().filter(u -> u.getAge() < 25 && u.getAge() != 0).toList());
        //Convierte la List a Stream, filtra por la edad y lo convierte a List otra vez y se la pasa a unknownTown() para cambiar las ciudades vacías a 'unknown' y la devuelve
    }

    //Método que dada una lista, la filtra eliminando aquellas personas que su nombre empieza por 'A' y escribe la lista por pantalla
    public static void filterNames(List<Person> list) {
        List<Person> newList = list.stream().filter(u -> u.getName().indexOf("A") == 0).toList();
        //Convierte la List a Stream, filtra por nombres que empiecen por 'A', lo convierte a List de nuevo y lo almacena en una lista auxiliar

        for (Person p : newList) { //Recorre la lista auxiliar
            for (Person e : list) { //Recorre la lista original
                if (e.equals(p)) { //Compara las personas de la original con las de la auxiliar
                    list.remove(p); //Si coincide se borra de la lista
                    break;
                }
            }
        }

        list = unknownTown(list); //Lista

        for (Person p : list) System.out.println(p); //Recorre la lista e imprime las personas
    }

    //Método que filtra la lista de personas por edad (<25) y luego por la ciudad de Madrid y devuelve la primera persona
    public static Person filterMadrid(List<Person> list) {
        Stream<Person> l = filterAges(list).stream(); //Filtra primero por la edad
        Optional<Person> s = l.filter(u -> u.getTown().equals("Madrid")).findFirst(); //De la lista restante filtra por la ciudad Madrid y se queda con el primero

        if (s.isPresent()) return s.get(); //Si hay personas de Madrid devuelve la persona
        else return null; //Sino devuelve null
    }

    //Método que filtra la lista de personas por edad (<25) y luego por la ciudad de Barcelona y devuelve la primera persona
    public static Person filterBarcelona(List<Person> list) {
        Stream<Person> l = filterAges(list).stream(); //Filtra primero por la edad
        Optional<Person> s = l.filter(u -> u.getTown().equals("Barcelona")).findFirst(); //De la lista restante filtra por la ciudad Barcelona y se queda con el primero

        if (s.isPresent()) return s.get(); //Si hay personas de Barcelona devuelve la persona
        else return null; //Sino devuelve null
    }
}