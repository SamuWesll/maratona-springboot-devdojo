package br.com.devdojo.awesome.endpoint;

import br.com.devdojo.awesome.error.CustomErrorType;
import br.com.devdojo.awesome.error.ResourceNotFoundException;
import br.com.devdojo.awesome.model.Students;
import br.com.devdojo.awesome.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1")
public class StudentEndpoint {

    private final StudentRepository studentRepository;
    @Autowired
    public StudentEndpoint(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
//
    @GetMapping(path = "protected/students")
    public ResponseEntity<?> listAll(Pageable pageable) {
//        System.out.println(dateUtil.formatLocalDateTimeToDateBaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "protected/students/findByName/{name}")
    public ResponseEntity<?> findStudentByName(@PathVariable String name) {
        return new ResponseEntity<>(studentRepository.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
    }
//
    @GetMapping(path = "protected/students/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            Students student = studentRepository.findById(id).get();
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Student not found for ID "+id);
        }
    };

    @PostMapping(path = "admin/students")
    public ResponseEntity<?> save(@Valid @RequestBody Students students) {
        return new ResponseEntity<>(studentRepository.save(students), HttpStatus.CREATED);
    };

    @DeleteMapping(path = "admin/students/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            Students studentDelete = studentRepository.findById(id).get();
            studentRepository.delete(studentDelete);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Student not found for ID "+id);
        }
    };

    @PutMapping(path = "admin/students")
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
