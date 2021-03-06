package br.com.devdojo.awesome.model;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Arrays.asList;

@Entity
public class Students extends AbstractEntity{

    @NotEmpty
    private String name;

    @NotEmpty
    @Email
    private String email;

    public Students() {
    }

    public Students(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Students(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Students{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    //    Getters e Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
