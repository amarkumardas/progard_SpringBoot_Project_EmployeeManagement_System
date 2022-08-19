package com.amar.employeemanagemetsystem.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Table(name="Role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter(AccessLevel.NONE)//to avoid creating getter method
    @Setter(AccessLevel.NONE)
    private int id;
    @Column(nullable = false)
    private  String roleName;
}
