package br.com.devdojo.awesome.javaClient;

import br.com.devdojo.awesome.handler.RestResponseExceptionHandler;
import br.com.devdojo.awesome.model.Students;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class JavaClientDAO {

    private RestTemplate restTemplate = new RestTemplateBuilder()
            .rootUri("http://localhost:8080/v1/protected/students")
            .basicAuthentication("samuel","123")
            .errorHandler(new RestResponseExceptionHandler())
            .build();

    private RestTemplate restTemplateAdmin = new RestTemplateBuilder()
            .rootUri("http://localhost:8080/v1/admin/students")
            .basicAuthentication("admin","123")
            .errorHandler(new RestResponseExceptionHandler())
            .build();

    public Students findById(long id) {
        Students student = restTemplate.getForObject("/{id}", Students.class, 1);
//        ResponseEntity<Students> forEntity = restTemplate.getForEntity("/{id}", Students.class, 1);
        return student;
    };

    public List<Students> listAll() {
        ResponseEntity<List<Students>> exchange = restTemplate.exchange("/", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Students>>() {
                });
        return exchange.getBody();
    };

    public Students save(Students studentsPost) {
//        ResponseEntity<Students> exchangePost = restTemplateAdmin.exchange("/",
//                HttpMethod.POST,new HttpEntity<>(studentsPost, createJSONHeader()), Students.class);
        Students studentsPostObject = restTemplateAdmin.postForObject("/", studentsPost, Students.class);
        return studentsPostObject;
    };

    public void update(Students student) {
        restTemplateAdmin.put("/",student);
    }

    public void delete(long id) {
        restTemplateAdmin.delete("/{id}",id);
    }

    private static HttpHeaders createJSONHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

}
