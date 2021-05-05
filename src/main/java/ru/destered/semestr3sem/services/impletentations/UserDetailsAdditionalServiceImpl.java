package ru.destered.semestr3sem.services.impletentations;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.destered.semestr3sem.models.User;
import ru.destered.semestr3sem.repositories.UsersRepository;
import ru.destered.semestr3sem.security.details.UserDetailsImpl;
import ru.destered.semestr3sem.services.interfaces.UserDetailsAdditionalService;
import ru.destered.semestr3sem.services.interfaces.UsersService;

import java.util.Optional;

@Component("additionalUserDetails")
@RequiredArgsConstructor
public class UserDetailsAdditionalServiceImpl implements UserDetailsAdditionalService {
    private final UsersRepository usersRepository;

    @Override
    public boolean updateUserDetails(UserDetails userDetails) {
        if(userDetails == null) return false;
        else {
            Optional<User> optUser = usersRepository.findById(((UserDetailsImpl)userDetails).getUser().getId());
            if(optUser.isPresent()){
                User user = optUser.get();
                ((UserDetailsImpl) userDetails).setUser(user);
            } else{
                return false;
            }
        }
        return true;
    }
}
