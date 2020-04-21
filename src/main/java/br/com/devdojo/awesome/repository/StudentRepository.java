package br.com.devdojo.awesome.repository;

import br.com.devdojo.awesome.model.Students;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Students, Long> {

    List<Students> findByName(String name);

}
