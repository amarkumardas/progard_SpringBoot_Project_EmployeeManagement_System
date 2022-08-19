package com.amar.employeemanagemetsystem.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.validator.constraints.Range;


import javax.persistence.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name="Asset")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")//it is added to class. to avoid infinite loop
public class Asset {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)//GenerationType.Identity does not create any additional sequence tables like GenerationType.AUTO
    private int id;
    @Column(nullable = false)
    @Range(min = 1,message = "Number of computer must be greater than 1")
    private int noOfComputers;
    @ManyToOne
    @NotNull(message = "Organization id is missing")
    private  Organization fk_org_obj;

}
