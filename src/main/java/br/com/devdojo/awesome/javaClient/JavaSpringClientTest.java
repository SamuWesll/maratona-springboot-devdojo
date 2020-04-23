package br.com.devdojo.awesome.javaClient;

import br.com.devdojo.awesome.model.Students;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class JavaSpringClientTest {

    public static void main(String[] args) {

        Students studentsPost = new Students();
        studentsPost.setName("Alice Gata");
        studentsPost.setEmail("alice@rocha.com");
        JavaClientDAO dao = new JavaClientDAO();

        System.out.println(dao.save(studentsPost));
    }

}
