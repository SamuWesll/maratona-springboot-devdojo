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
        studentsPost.setName("Alice");
        studentsPost.setEmail("alice@rocha.com");
        studentsPost.setId(74L);
        JavaClientDAO dao = new JavaClientDAO();

        System.out.println(dao.save(studentsPost));
//        dao.update(studentsPost);
        dao.delete(74);
    }

}
