package spring_demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import spring_demo.dto.BookDto;
import spring_demo.dto.PageDto;
import spring_demo.model.one_to_many.Book;
import spring_demo.model.one_to_many.Page;
import spring_demo.repository.BookRepository;
import spring_demo.repository.PageRepository;
import spring_demo.service.BookService;
import spring_demo.service.PageService;

import java.util.List;

@Controller
//@RequestMapping("/")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final PageService pageService;

    @GetMapping("/book/{id}")
    public String getBookDetails(@PathVariable Long id, ModelMap modelMap){
        BookDto bookDto = bookService.getBookDetails(id);
        List<PageDto> pageDtos = pageService.getPagesByBookId(id);

        modelMap.addAttribute("book", bookDto);
        modelMap.addAttribute("pages", pageDtos);

        return "book-details";
    }


//    private final BookRepository bookRepository;
//    private final PageRepository pageRepository;
//
//    @GetMapping("/book/{id}")
//    public String getBookDetails(@PathVariable Long id, ModelMap modelMap) {
//        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book id: " + id));
//        List<Page> pages = pageRepository.findByBook(book);
//
//        modelMap.addAttribute("book", book);
//        modelMap.addAttribute("pages", pages);
//        return "book-details";
//
//    }


}
