package ru.destered.semestr3sem.services.implementations;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.destered.semestr3sem.dto.UserDto;
import ru.destered.semestr3sem.dto.forms.SignUpForm;
import ru.destered.semestr3sem.models.User;
import ru.destered.semestr3sem.repositories.UsersRepository;
import ru.destered.semestr3sem.services.interfaces.EditProfileService;
import ru.destered.semestr3sem.services.interfaces.SmsConfirmService;

import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class EditProfileServiceImpl implements EditProfileService {
    private final UsersRepository repository;
    private final SmsConfirmService phoneService;

    @SneakyThrows
    @Override
    public void updateProfile(Long userId, SignUpForm form) {
        User user = repository.findById(userId)
                .orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("user not found"));
        if(form.getPhone() != null && !form.getPhone().equals("")){
            user.setPhone(form.getPhone());
            phoneService.sendSms(form.getPhone(),"Номер успешно привязан");
        }
        if(form.getUsername() != null && !form.getUsername().equals("")){
            user.setUsername(form.getUsername());
        }

        repository.save(user);
    }

    @SneakyThrows
    @Override
    public void updateProfileFromDto(Long userId, UserDto userDto) {
        User user = repository.findById(userId)
                .orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("user not found"));
        user.setAvatarImageName(userDto.getAvatarImageName());
        repository.save(user);
    }
}