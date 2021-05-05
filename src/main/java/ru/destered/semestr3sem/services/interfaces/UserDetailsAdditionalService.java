package ru.destered.semestr3sem.services.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsAdditionalService {

    boolean updateUserDetails(UserDetails userDetails);
}
