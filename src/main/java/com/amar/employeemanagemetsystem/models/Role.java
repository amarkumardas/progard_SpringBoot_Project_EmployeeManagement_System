package com.amar.employeemanagemetsystem.models;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name="Role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private  String roleName;
}
