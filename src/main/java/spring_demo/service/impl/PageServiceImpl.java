package spring_demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring_demo.dto.PageDto;
import spring_demo.model.one_to_many.Page;
import spring_demo.repository.PageRepository;
import spring_demo.service.PageService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PageServiceImpl implements PageService {
    private final PageRepository pageRepository;
    @Override
    public List<PageDto> getPagesByBookId(Long bookId) {
        List<Page> pages = pageRepository.findByBookId(bookId);
        return convertToDtoList(pages);
    }

    private List<PageDto> convertToDtoList(List<Page> pages) {
        List<PageDto> pageDtos = new ArrayList<>();
        for (Page page : pages) {
            PageDto pageDto = new PageDto(page.getNumber(), page.getContent(), page.getChapter());
            pageDtos.add(pageDto);
        }
        return pageDtos;
    }
}
