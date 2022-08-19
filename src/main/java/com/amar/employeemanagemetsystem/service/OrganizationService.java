package com.amar.employeemanagemetsystem.service;

import com.amar.employeemanagemetsystem.models.Organization;

import java.util.List;

public interface OrganizationService {
    Organization saveOrganization(Organization organization);

    List<Organization> getAllOrganization();

    Organization getOrganizationById(int id);

    Organization updateOrganization(Organization organization, int id);

    void deleteOrganization(int id);
}
