package ru.destered.semestr3sem.services.impletentations;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
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

        user.setUsername(form.getUsername());
        user.setPhone(form.getPhone());
        phoneService.sendSms(form.getPhone(),"Номер успешно привязан");

        repository.save(user);
    }
}