package spring_demo.repository;

import org.springframework.data.repository.CrudRepository;
import spring_demo.model.one_to_many.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
    Book findByIsbn(String isbn);
}
