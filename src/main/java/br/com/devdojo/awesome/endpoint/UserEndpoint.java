package br.com.devdojo.awesome.endpoint;

import br.com.devdojo.awesome.PasswordEncoder;
import br.com.devdojo.awesome.model.User;
import br.com.devdojo.awesome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserEndpoint {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder encoder = new PasswordEncoder();

    @GetMapping(path = "user")
    public ResponseEntity<?> listAll(Pageable pageable) {
        return new ResponseEntity<>(userRepository.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping(path = "user")
    public ResponseEntity<?> save(@Valid @RequestBody User user) {
//        user.setPassword(encoder.cryptPasswordEncoder(user.getPassword()));
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }

}
