package com.amar.employeemanagemetsystem.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "fk_orgobj",orphanRemoval=true,cascade =CascadeType.ALL)//to delete child this attribute required orphanRemoval=true
    private List<Employee> employee=new ArrayList<Employee>();

    @OneToMany(mappedBy = "fk_org_obj",orphanRemoval=true,cascade =CascadeType.ALL)//to delete child this attribute required orphanRemoval=true
    private List<Asset> asset=new ArrayList<Asset>();;

}
