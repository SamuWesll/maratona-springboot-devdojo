package br.com.devdojo.awesome;

import br.com.devdojo.awesome.model.Students;
import br.com.devdojo.awesome.repository.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.util.Arrays.asList;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class StudentEndpointTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private MockMvc mockMvc;

    @TestConfiguration
    static class Config {
        @Bean
        public RestTemplateBuilder restTemplateBuilder() {
            return new RestTemplateBuilder().basicAuthentication("admin", "123");
        }
    }

    @Test
    public void listStudentsWhenUsernameAndPasswordAreIncorrectShouldReturnStatusCode401() {
        restTemplate = restTemplate.withBasicAuth("1","1");
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/v1/protected/students/",String.class);
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(401);
    }

    @Test
    public void getStudentByIdWhenUsernameAndPasswordAreIncorrectShouldReturnStatusCode401() {
        restTemplate = restTemplate.withBasicAuth("1","1");
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/v1/protected/students/1",String.class);
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(401);
    }

    @Test
    public void listStudentsWhenUsernameAndPasswordAreCorrectShouldReturnStatusCode200() {
        List<Students> students = asList(new Students(1L,"Legolas", "legolas@lego.com"),
                new Students(2L,"Arogorn", "aragorn@ara.com")
        );
        BDDMockito.when(studentRepository.findAll()).thenReturn(students);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/v1/protected/students/",String.class);
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    };

//    @Test
//    public void getStudentByIdWhenUsernameAndPasswordAreCorrectShouldReturnStatusCode200() {
//        Students student = new Students(1L,"samuel","samuel@weslley.com.br");
//        BDDMockito.when(studentRepository.findById(student.getId()).get()).thenReturn(student);
//        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/v1/protected/students/{id}",String.class, student.getId());
//        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
//    };

    @Test
    public void getStudentByIdWhenUsernameAndPasswordAreCorrectAndStudentDoesNotExistShouldReturnStatusCode200() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/v1/protected/students/{id}",String.class, -1);
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(404);
    }

}
