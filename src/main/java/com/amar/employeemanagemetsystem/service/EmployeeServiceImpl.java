package com.amar.employeemanagemetsystem.service;

import com.amar.employeemanagemetsystem.models.Employee;
import com.amar.employeemanagemetsystem.models.Organization;
import com.amar.employeemanagemetsystem.models.Role;
import com.amar.employeemanagemetsystem.repository.EmployeeRepo;
import com.amar.employeemanagemetsystem.repository.OrganizationRepo;
import com.amar.employeemanagemetsystem.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private OrganizationRepo organizationRepo;
    @Autowired
    private RoleRepo roleRepo;

    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        employee.setPassword(this.passwordEncoder.encode(employee.getPassword()));//encrypting password and storing in db

        List<Employee> emp=employeeRepo.findAll();
        if (!emp.isEmpty()){//if employee available then make role as employee
            Role roles=roleRepo.findById(3).orElseThrow();
            Set<Role> roleObj=new HashSet<>();
            roleObj.add(roles);
            employee.setRoles(roleObj);
            return employeeRepo.save(employee);
        }
        else {//if employee not available then make role as admin
            Role roleobj1=new Role();
            roleobj1.setRoleName("admin");
            roleRepo.save(roleobj1);

            Role roleobj2=new Role();
            roleobj2.setRoleName("manager");
            roleRepo.save(roleobj2);

            Role roleobj3=new Role();
            roleobj3.setRoleName("employee");
            roleRepo.save(roleobj3);

             Organization orgObj=new Organization();
             orgObj.setOrganizationName("defaultOrg");
             organizationRepo.save(orgObj);

            Role roles=roleRepo.findById(1).orElseThrow();//by default first employee role is admin
            Set<Role> roleObj=new HashSet<>();
            roleObj.add(roles);
            employee.setRoles(roleObj);
            return employeeRepo.save(employee);
        }


    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepo.findAll();//to get all employee
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeRepo.findById(id).orElseThrow();//if error occur throw NoSuchElementException
    }

    @Override
    public Employee updateEmployee(Employee employee, int id) {
        Employee existingEmployee=employeeRepo.findById(id).orElseThrow();
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPassword(this.passwordEncoder.encode(employee.getPassword()));//encrypting password and storing in dbemployee.getPassword());
        existingEmployee.setName(employee.getName());
        existingEmployee.setFk_orgobj(employee.getFk_orgobj());
        employeeRepo.save(existingEmployee);
        return existingEmployee;
    }
    @Override
    public void deleteEmployee(int id) {
          Employee obj=employeeRepo.findById(id).orElseThrow();
          obj.getRoles().clear();//first clear role before delete due to foreign ket constraint
         employeeRepo.deleteById(id);
    }
}
