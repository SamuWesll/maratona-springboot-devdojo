package br.com.devdojo.awesome;

import br.com.devdojo.awesome.model.Students;
import br.com.devdojo.awesome.repository.StudentRepository;
import org.assertj.core.api.Assertions;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void createShoulPersistData() {
        Students students = new Students("samuel", "samu@wesll.com");
        this.studentRepository.save(students);
        Assertions.assertThat(students.getId()).isNotNull();
        Assertions.assertThat(students.getName()).isEqualTo("samuel");
        Assertions.assertThat(students.getEmail()).isEqualTo("samu@wesll.com");
    };

//    @Test
//    public void deleteShouldRemoveData() {
//        Students students = new Students("samuel", "samu@wesll.com");
//        this.studentRepository.save(students);
//        this.studentRepository.delete(students);
//        Assertions.assertThat(studentRepository.findById(students.getId())).isNull();
//    };

    @Test
    public void updateShouldChangeAndPesistData() {
        Students students = new Students("samuel", "samu@wesll.com");
        this.studentRepository.save(students);
        students.setName("samuel222");
        students.setEmail("outro@email.com.br");
        students = this.studentRepository.save(students);
        Assertions.assertThat(students.getName()).isEqualTo("samuel222");
        Assertions.assertThat(students.getEmail()).isEqualTo("outro@email.com.br");
    }

//    @Test
//    public void createWhenNameIsNullShouldThrowConstraintViolationException() {
//        thrown.expect(ConstraintViolationException.class);
//        this.studentRepository.save(new Students());
//    }

}
