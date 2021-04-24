package ru.destered.semestr3sem.services.impletentations;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.destered.semestr3sem.dto.UserDto;
import ru.destered.semestr3sem.dto.forms.SignUpForm;
import ru.destered.semestr3sem.dto.mapper.FormUserMapper;
import ru.destered.semestr3sem.dto.mapper.UserDtoMapper;
import ru.destered.semestr3sem.models.User;
import ru.destered.semestr3sem.repositories.UsersRepository;
import ru.destered.semestr3sem.services.interfaces.UsersService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepository userRepository;
    private final UserDtoMapper dtoMapper;
    private final FormUserMapper formMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<UserDto> getUsers(int number) {
        Pageable pageable = PageRequest.of(number, 2);
        Page<User> page = userRepository.findAll(pageable);

        return page.map(dtoMapper::userToDto);
    }

    @Override
    public UserDto createUser(SignUpForm form) {
        User user = formMapper.signUpToUser(form);
        user.setRole(User.Role.USER);
        user.setState(User.State.ACTIVE);
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        user.setProved(false);
        user.setCurrentConfirmationCode(UUID.randomUUID().toString());
        userRepository.save(user);
        return dtoMapper.userToDto(user);
    }

    @Override
    public UserDto updateUser(Long id, SignUpForm form) {
        User userForUpdate = userRepository.findById(id)
                .orElseThrow(IllegalStateException::new);

        userForUpdate.setUsername(form.getUsername());
        userForUpdate.setEmail(form.getEmail());

        userRepository.save(userForUpdate);
        return dtoMapper.userToDto(userForUpdate);
    }

    @Override
    public void deleteUser(Long id) {
        User userForDelete = userRepository.findById(id)
                .orElseThrow(IllegalStateException::new);

        userRepository.delete(userForDelete);
    }
}

