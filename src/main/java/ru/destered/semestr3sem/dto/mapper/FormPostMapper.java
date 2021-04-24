package ru.destered.semestr3sem.dto.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.destered.semestr3sem.dto.forms.PostForm;
import ru.destered.semestr3sem.models.Post;

@Mapper(componentModel = "spring")
public interface FormPostMapper {
    @Mappings({
            @Mapping(target = "name", source = "form.name"),
            @Mapping(target = "text", source = "form.text")
    })
    Post formToPost(PostForm form);

    @InheritInverseConfiguration
    PostForm postToForm(Post user);
}
