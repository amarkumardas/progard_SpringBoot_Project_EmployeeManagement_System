package com.amar.employeemanagemetsystem.controller;

import com.amar.employeemanagemetsystem.models.Employee;
import com.amar.employeemanagemetsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/emp/")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

//    EmployeeController(EmployeeService employeeService){
//        this.employeeService=employeeService;
//    }
    @PostMapping//it should be always above THE method ie. AT METHOD LEVEL . it is used for creation
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee){//ResponseEntity represents Http response including headers,body,status.we will get data in the form of ResponseEntity
        return new ResponseEntity<Employee>( employeeService.saveEmployee(employee), HttpStatus.CREATED);//here we are creating and saving data in db
    }                                                               //if it is created the status will be 201 Ok
    @GetMapping("getall")
    public List<Employee> getAllEmployee(){
        return  employeeService.getAllEmployee();
    }

    @GetMapping("getbyid/{id}")//or @RequestMapping(value="/{id}",method=RequestMethod.GET)
    public ResponseEntity<Employee> getAEmployeeById(@PathVariable("id") int id){
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(id),HttpStatus.OK);
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
