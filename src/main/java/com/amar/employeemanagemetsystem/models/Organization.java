package com.amar.employeemanagemetsystem.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")//it is added to class. to avoid infinite loop
@Table(name = "Organization")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false,unique = true)
    @NotBlank(message = "Organization name is missing")
    private String organizationName;

    @JsonIgnore
    @OneToMany(mappedBy = "fk_orgobj",orphanRemoval=true,cascade =CascadeType.ALL)//to delete child this attribute required orphanRemoval=true
    private List<Employee> employee=new ArrayList<Employee>();
    @JsonIgnore
    @OneToMany(mappedBy = "fk_org_obj",orphanRemoval=true,cascade =CascadeType.ALL)//to delete child this attribute required orphanRemoval=true
    private List<Asset> asset=new ArrayList<Asset>();

}
