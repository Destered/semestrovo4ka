package ru.destered.semestr3sem.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.destered.semestr3sem.dto.SearchForm;
import ru.destered.semestr3sem.dto.UserDto;
import ru.destered.semestr3sem.security.details.UserDetailsImpl;
import ru.destered.semestr3sem.services.interfaces.UserSearchService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/search")
@RequiredArgsConstructor
@Tag(name="Search controller", description="Search controller API")
public class SearchController {
    private final UserSearchService userSearchService;


    @Operation(
            summary = "search user",
            description = "search user by id"
    )
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<String> getUsersBySearchForm(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody SearchForm searchForm) {
        Page<UserDto> result = userSearchService.findAllByRequestBody(searchForm);
        List<String> answer = new ArrayList<>();

        result.getContent().forEach( user ->
                answer.add(user.toString())
        );
        return answer;
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping
    public String getSearchPage() {
        return "search";
    }
}
