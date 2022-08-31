package com.amar.employeemanagemetsystem.repository;

import com.amar.employeemanagemetsystem.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Integer> {
}
