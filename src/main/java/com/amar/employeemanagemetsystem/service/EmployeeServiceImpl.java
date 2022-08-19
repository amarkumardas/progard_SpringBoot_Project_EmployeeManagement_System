package com.amar.employeemanagemetsystem.service;

import com.amar.employeemanagemetsystem.models.Employee;
import com.amar.employeemanagemetsystem.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        employee.setPassword(this.passwordEncoder.encode(employee.getPassword()));//encrypting password and storing in db
        return employeeRepo.save(employee);
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
        employeeRepo.save(existingEmployee);
        return existingEmployee;
    }
    @Override
    public void deleteEmployee(int id) {
          employeeRepo.findById(id).orElseThrow();
         employeeRepo.deleteById(id);
    }
}
