package spring_demo.service;

import spring_demo.dto.BookDto;
import spring_demo.model.one_to_many.Book;
import spring_demo.model.one_to_many.Page;

import java.util.List;

public interface BookService {
    BookDto getBookDetails(Long id);

    BookDto convertToDto(Book book, List<Page> pages);
}
