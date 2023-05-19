package spring_demo.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import spring_demo.model.one_to_many.Book;
import spring_demo.model.one_to_many.Page;

import java.util.List;

public interface PageRepository extends CrudRepository<Page, Long> {
    List<Page> findByBookId (Long bookId);
}
