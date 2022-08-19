package com.amar.employeemanagemetsystem.security;

import com.amar.employeemanagemetsystem.models.Employee;
import com.amar.employeemanagemetsystem.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDetailsImpl implements UserDetailsService {//UserDetailsService is interface used to load specific user data.it has only one read only method which simplifies for new data access startegies
    @Autowired
    private EmployeeRepo employeeRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {//spring security will call this method when user enter their username to authenticate their username and password
       Employee employee=this.employeeRepo.findByEmail(username).orElseThrow();
       return employee;//can return this object because Employee class implements UserDetails
    }
}
