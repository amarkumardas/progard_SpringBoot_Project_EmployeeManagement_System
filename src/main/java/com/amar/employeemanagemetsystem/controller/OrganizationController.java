package com.amar.employeemanagemetsystem.controller;

import com.amar.employeemanagemetsystem.models.Organization;
import com.amar.employeemanagemetsystem.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/org/")
public class OrganizationController {
    @Autowired
    OrganizationService organizationService;

    @PostMapping//it should be always above THE method ie. AT METHOD LEVEL . it is used for creation
    public ResponseEntity<Organization> saveOrganization(@Valid @RequestBody Organization organization){//ResponseEntity represents Http response including headers,body,status.we will get data in the form of ResponseEntity
        return new ResponseEntity<Organization>( organizationService.saveOrganization(organization), HttpStatus.CREATED);//here we are creating and saving data in db
    }                                                               //if it is created the status will be 201 Ok
    @GetMapping("getall")
    public List<Organization> getAllEmployee(){
        return  organizationService.getAllOrganization();
    }

    @GetMapping("getbyid/{id}")//or @RequestMapping(value="/{id}",method=RequestMethod.GET)
    public ResponseEntity<Organization> getOrganizationById(@PathVariable("id") int id){
        return new ResponseEntity<Organization>(organizationService.getOrganizationById(id),HttpStatus.OK);
    }

    @PutMapping("updatebyid/{id}")//for updation we can also use @Postmapping("{id}") which work in same manner
    public ResponseEntity<Organization> updateOrganization(@Valid @PathVariable("id")int id, @RequestBody Organization organization){
        return new ResponseEntity<Organization>(organizationService.updateOrganization(organization,id),HttpStatus.OK);
    }
    @DeleteMapping("deletebyid/{id}")
    public ResponseEntity<String> deleteOrganization(@PathVariable("id")int id){
        organizationService.deleteOrganization(id);
        return new ResponseEntity<String>("Organization data deleted successfully",HttpStatus.OK);
    }
}
