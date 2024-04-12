package com.istasyon.backend.services;

import com.istasyon.backend.entities.User;
import com.istasyon.backend.repositories.CompanyRepo;
import com.istasyon.backend.repositories.EmployeeRepo;
import com.istasyon.backend.repositories.UserRepo;
import com.istasyon.backend.security.CustomUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final EmployeeRepo employeeRepo;
    private final CompanyRepo companyRepo;

    public UserService(UserRepo userRepo, EmployeeRepo employeeRepo, CompanyRepo companyRepo) {
        this.userRepo = userRepo;
        this.employeeRepo = employeeRepo;
        this.companyRepo = companyRepo;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        boolean isCompany = companyRepo.existsBycUserNo(user.getUserId());
        boolean isEmployee = employeeRepo.existsByeUserNo(user.getUserId());
        GrantedAuthority authority;
        if(isCompany){
            authority = new SimpleGrantedAuthority("ROLE_COMPANY");
        } else if (isEmployee) {
            authority = new SimpleGrantedAuthority("ROLE_EMPLOYEE");
        } else {
            authority = new SimpleGrantedAuthority("ROLE_UNKNOWN");
        }

        return new CustomUserDetails(user, Collections.singletonList(authority));
    }
}
