package br.com.devdojo.awesome.javaClient;

import br.com.devdojo.awesome.model.Students;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class JavaSpringClientTest {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri("http://localhost:8080/v1/protected/students")
                .basicAuthentication("samuel","123").build();
        Students student = restTemplate.getForObject("/{id}", Students.class, 1);
        ResponseEntity<Students> forEntity = restTemplate.getForEntity("/{id}", Students.class, 1);
//        System.out.println(students);
        System.out.println(forEntity.getBody());
//        Students[] students = restTemplate.getForObject("/", Students[].class);
//        System.out.println(Arrays.toString(students));
        ResponseEntity<List<Students>> exchange = restTemplate.exchange("/", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Students>>() {
        });
        System.out.println(exchange.getBody());
    }

}
