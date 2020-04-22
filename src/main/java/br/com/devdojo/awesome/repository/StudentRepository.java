package br.com.devdojo.awesome.repository;

import br.com.devdojo.awesome.model.Students;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface StudentRepository extends PagingAndSortingRepository<Students, Long> {

    List<Students> findByNameIgnoreCaseContaining(String name);

}
