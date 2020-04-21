package br.com.devdojo.awesome.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Arrays.asList;


public class Students {

    private int id;
    private String name;
    public static List<Students> studentsList;

    static {
        studentRepository();
    }


    public Students(int id, String name) {
        this(name);
        this.id = id;
    }

    public Students() {
    }

    public Students(String name) {
        this.name = name;
    }

    private static void studentRepository() {
        studentsList = new ArrayList<>(asList(new Students(1,"Deku"), new Students(2,"Todoroki")));
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Students students = (Students) o;
        return id == students.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
