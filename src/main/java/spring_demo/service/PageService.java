package spring_demo.service;

import spring_demo.dto.PageDto;

import java.util.List;

public interface PageService {
    List<PageDto> getPagesByBookId(Long bookId);
}
