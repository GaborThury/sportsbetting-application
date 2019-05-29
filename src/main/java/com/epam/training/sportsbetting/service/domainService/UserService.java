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

import java.nio.charset.Charset;
import java.util.Random;

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

    public String registerUser(User userToRegister) {
        User userCheck = userRepository.findByEmail(userToRegister.getEmail());

        if (userCheck != null) {
            return "This email address is already registered!";
        }

        Role userRole = roleRepository.findByRole(USER_ROLE);
        if (userRole != null) {
            userToRegister.getRoles().add(userRole);
        } else {
            userToRegister.addRole(USER_ROLE);
        }
        userToRegister.setEnabled(false);
        userToRegister.setActivationCode(generateActivationCode());

        userRepository.save(userToRegister);
        return "ok";
    }

    private String generateActivationCode() {
        char[] word = new char[16];
        Random random = new Random();
        for (int i = 0; i < word.length; i++) {
            word[i] = (char) ('a' + random.nextInt(26));
        }
        return new String(word);
    }

    public String activateUser(String code) throws IllegalArgumentException {
        User user = userRepository.findByActivationCode(code);
        if (user == null) {
            throw new IllegalArgumentException("no user found with the given activation code");
        }
        user.setEnabled(true);
        user.setActivationCode("");
        userRepository.save(user);
        return "ok";
    }
}
