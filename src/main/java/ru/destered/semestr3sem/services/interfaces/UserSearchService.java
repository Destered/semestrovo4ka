package ru.destered.semestr3sem.services.interfaces;

import org.springframework.data.domain.Page;
import ru.destered.semestr3sem.dto.SearchForm;
import ru.destered.semestr3sem.dto.UserDto;

public interface UserSearchService {
    Page<UserDto> findAllByRequestBody(SearchForm searchForm);
}
