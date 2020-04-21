package br.com.devdojo.awesome.endpoint;

import br.com.devdojo.awesome.error.CustomErrorType;
import br.com.devdojo.awesome.model.Students;
import br.com.devdojo.awesome.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("student")
public class StudentEndpoint {

    private final StudentRepository studentRepository;
    @Autowired
    public StudentEndpoint(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
//
    @GetMapping
    public ResponseEntity<?> listAll() {
//        System.out.println(dateUtil.formatLocalDateTimeToDateBaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/findByName/{name}")
    public ResponseEntity<?> findStudentByName(@PathVariable String name) {
        return new ResponseEntity<>(studentRepository.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
    }
//
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id) {
        Students student = studentRepository.findById(id).get();
        if(student == null)
            return new ResponseEntity<>(new CustomErrorType("Student not found"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(student, HttpStatus.OK);
    };
//
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Students students) {
        return new ResponseEntity<>(studentRepository.save(students), HttpStatus.CREATED);
    };

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Students studentDelete = studentRepository.findById(id).get();
        studentRepository.delete(studentDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    };

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Students students) {
        studentRepository.save(students);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
