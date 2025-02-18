package com.project.ProjectTracker.service;

import com.project.ProjectTracker.Dao.UserRepository;
import com.project.ProjectTracker.entity.User;
import com.project.ProjectTracker.models.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        user.orElseThrow(()-> new UsernameNotFoundException("Not Found: "+ username));

        return user.map(CustomUserDetails::new).get();
//        return new User("user","1234", new ArrayList<>());
    }
}
