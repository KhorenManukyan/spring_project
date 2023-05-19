package spring_demo.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring_demo.dto.BookDto;
import spring_demo.model.one_to_many.Book;
import spring_demo.model.one_to_many.Page;
import spring_demo.repository.BookRepository;
import spring_demo.repository.PageRepository;
import spring_demo.service.BookService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final PageRepository pageRepository;
    @Override
    public BookDto getBookDetails(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book id: " + id));
        List<Page> pages = pageRepository.findByBookId(id);
        return convertToDto(book,pages);
    }

    @Override
    public BookDto convertToDto(Book book, List<Page> pages) {
        return new BookDto(book.getTitle(), book.getAuthor(), book.getIsbn());
    }
}
