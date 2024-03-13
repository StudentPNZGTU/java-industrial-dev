package edu.penzgtu.pp.dockerexample.repository;

import edu.penzgtu.pp.dockerexample.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
