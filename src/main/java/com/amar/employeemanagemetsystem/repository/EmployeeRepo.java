package com.amar.employeemanagemetsystem.repository;

import com.amar.employeemanagemetsystem.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
    Optional<Employee> findByEmail(String email);//Optional there are chances of null so Optional is used

}
