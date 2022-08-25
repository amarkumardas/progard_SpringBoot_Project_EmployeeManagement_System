package com.amar.employeemanagemetsystem.service;

import com.amar.employeemanagemetsystem.models.Employee;
import com.amar.employeemanagemetsystem.models.Organization;
import com.amar.employeemanagemetsystem.repository.OrganizationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrganizationServiceImpl implements OrganizationService{
    @Autowired
    OrganizationRepo organizationRepo;

    public OrganizationServiceImpl(OrganizationRepo organizationRepo) {
        this.organizationRepo = organizationRepo;
    }

    @Override
    public Organization saveOrganization(Organization organization) {
        return organizationRepo.save(organization);
    }

    @Override
    public List<Organization> getAllOrganization() {
        return organizationRepo.findAll();//get all data
    }

    @Override
    public Organization getOrganizationById(int id) {
        return organizationRepo.findById(id).orElseThrow();
    }

    @Override
    public Organization updateOrganization(Organization organization, int id) {
        Organization existingOrganization=organizationRepo.findById(id).orElseThrow();
        existingOrganization.setOrganizationName(organization.getOrganizationName());
        organizationRepo.save(existingOrganization);
        return existingOrganization;
    }

    @Override
    public void deleteOrganization(int id) {
     Organization obj=organizationRepo.findById(id).orElseThrow();
     List<Employee> emp=obj.getEmployee();
        for (int i=0; i<emp.size(); i++){//deleting employee role first due to foreign key
            Employee empObj=emp.get(i);
            empObj.getRoles().clear();
        }
    organizationRepo.deleteById(id);
    }
}
