package com.amar.employeemanagemetsystem.repository;

import com.amar.employeemanagemetsystem.models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepo extends JpaRepository<Organization,Integer> {
}
