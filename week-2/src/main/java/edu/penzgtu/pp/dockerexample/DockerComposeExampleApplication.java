package edu.penzgtu.pp.dockerexample;

import edu.penzgtu.pp.dockerexample.entity.Book;
import edu.penzgtu.pp.dockerexample.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/books")
public class DockerComposeExampleApplication {

	@Autowired
	private BookRepository bookRepository;

	@PostMapping
	public Book saveBook(@RequestBody Book book) {
		return bookRepository.save(book);
	}
	@GetMapping
	public List<Book> getBook() {
		return bookRepository.findAll();
	}

	public static void main(String[] args) {
		SpringApplication.run(DockerComposeExampleApplication.class, args);
	}

}
