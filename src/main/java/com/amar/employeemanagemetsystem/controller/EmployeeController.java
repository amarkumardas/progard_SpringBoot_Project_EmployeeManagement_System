package com.amar.employeemanagemetsystem.controller;

import com.amar.employeemanagemetsystem.advice.CannotAccessId;
import com.amar.employeemanagemetsystem.models.Employee;
import com.amar.employeemanagemetsystem.repository.EmployeeRepo;
import com.amar.employeemanagemetsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/emp/")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeRepo employeeRepo;
    @PostMapping//it should be always above THE method ie. AT METHOD LEVEL . it is used for creation
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee){//ResponseEntity represents Http response including headers,body,status.we will get data in the form of ResponseEntity
        return new ResponseEntity<Employee>( employeeService.saveEmployee(employee), HttpStatus.CREATED);//here we are creating and saving data in db
    }                                                               //if it is created the status will be 201 Ok
    @GetMapping("getall")
    public List<Employee> getAllEmployee(){
        return  employeeService.getAllEmployee();
    }

    @GetMapping("getbyid/{id}")//or @RequestMapping(value="/{id}",method=RequestMethod.GET)
    public ResponseEntity<Employee> getAEmployeeById(@PathVariable("id") int id) throws CannotAccessId {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userID=employeeRepo.findByEmail(authentication.getName()).get().getId();

        if(userID != id && authentication.getAuthorities().contains(new SimpleGrantedAuthority("employee")))//only employee which is login then he can see its own details but not other details
            throw new CannotAccessId("cannot access other employee details");

        return new ResponseEntity<Employee>(employeeService.getEmployeeById(id),HttpStatus.OK);

//        if(userID != id && authentication.getAuthorities().contains(new SimpleGrantedAuthority("employee")))//only employee which is login then he can see its own details but not other details
//            return new ResponseEntity<String>("Unauthorized access Request ",HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("updatebyid/{id}")//for updation we can also use @Postmapping("{id}") which work in same manner
    public ResponseEntity<Employee> updateEmployee(@Valid @PathVariable("id")int id, @RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee,id),HttpStatus.OK);
    }
    @DeleteMapping("deletebyid/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id")int id){
        employeeService.deleteEmployee(id);
       return new ResponseEntity<String>("Employee data deleted successfully",HttpStatus.OK);

    }
}
