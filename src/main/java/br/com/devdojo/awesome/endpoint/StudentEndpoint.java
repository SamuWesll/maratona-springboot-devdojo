package br.com.devdojo.awesome.endpoint;

import br.com.devdojo.awesome.error.CustomErrorType;
import br.com.devdojo.awesome.error.ResourceNotFoundException;
import br.com.devdojo.awesome.model.Students;
import br.com.devdojo.awesome.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<?> listAll(Pageable pageable) {
//        System.out.println(dateUtil.formatLocalDateTimeToDateBaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(studentRepository.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "/findByName/{name}")
    public ResponseEntity<?> findStudentByName(@PathVariable String name) {
        return new ResponseEntity<>(studentRepository.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
    }
//
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id) {
        try {
            Students student = studentRepository.findById(id).get();
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Student not found for ID "+id);
        }
    };

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Students students) {
        return new ResponseEntity<>(studentRepository.save(students), HttpStatus.CREATED);
    };

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            Students studentDelete = studentRepository.findById(id).get();
            studentRepository.delete(studentDelete);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Student not found for ID "+id);
        }
    };

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Students students) {
        studentRepository.save(students);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void verifyIfStudentExists(Long id) {
       if(studentRepository.findById(id).get() == null) {
           throw new ResourceNotFoundException("Student not found for ID " + id);
       }
    }

}
