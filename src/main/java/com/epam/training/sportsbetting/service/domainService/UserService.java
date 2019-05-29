package com.epam.training.sportsbetting.service.domainService;

import com.epam.training.sportsbetting.domain.Role;
import com.epam.training.sportsbetting.domain.User;
import com.epam.training.sportsbetting.repository.RoleRepository;
import com.epam.training.sportsbetting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private static final String USER_ROLE = "ROLE_USER";
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByEmail(username);
        if (user == null) throw new UsernameNotFoundException(username);
        return new UserDetailsImpl(user, passwordEncoder);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void registerUser(User user) {
        Role userRole = roleRepository.findByRole(USER_ROLE);
        if (userRole != null) {
            user.getRoles().add(userRole);
        } else {
            user.addRole(USER_ROLE);
        }
        userRepository.save(user);
    }
}
