package ru.destered.semestr3sem.services.impletentations;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.destered.semestr3sem.dto.SearchForm;
import ru.destered.semestr3sem.dto.UserDto;
import ru.destered.semestr3sem.models.User;
import ru.destered.semestr3sem.repositories.UsersRepository;
import ru.destered.semestr3sem.services.interfaces.UserSearchService;

@Service
@RequiredArgsConstructor
public class UserSearchServiceImpl implements UserSearchService {
    private final UsersRepository usersRepository;

    @Override
    public Page<UserDto> findAllByRequestBody(SearchForm searchForm) {

        int page = searchForm.getPage();
        if(page != 0) page -=1;
        int size = searchForm.getSize();
        if(size == 0) size = 10;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.unsorted());
        Page<User> userList = usersRepository.findAllByUsernameIgnoreCase(searchForm.getName(), pageRequest);
        return userList.map(UserDto::fromUser);
    }
}
