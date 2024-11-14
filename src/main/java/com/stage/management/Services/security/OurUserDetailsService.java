package com.stage.management.Services.security;


import com.stage.management.DAO.Entities.ERole;
import com.stage.management.DAO.Entities.OurUsers;
import com.stage.management.DAO.Repositories.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//a bridge between the Spring Security framework and the application's user data storage
@Service
public class OurUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepo usersRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        OurUsers user = usersRepo.findByEmail(username).orElseThrow();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
        return new User(user.getEmail(), user.getPassword(), authorities);
    }


    public List<OurUsers> getAllCollaborators() {
        return usersRepo.findByRole(ERole.COLLABORATOR);
    }


}