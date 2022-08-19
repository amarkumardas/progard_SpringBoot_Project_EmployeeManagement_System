package com.amar.employeemanagemetsystem.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data//to avoid boiler plate
@Entity//to make this class act as table of db
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")//it is added to class. to avoid infinite loop
@Table(name = "Employee")
public class Employee implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    @NotBlank(message = "Name is required")
    private String name;
    @Column(nullable = false,unique = true)//every username should be unique
    @Email(message = "Invalid email")
    @NotEmpty(message = "Email cannot be empty")//@NotNull not working
    private String email;
    @Column(nullable = false)
    @NotBlank(message = "Password is mandatory")
    @Size(min=3,max = 256,message = "Password must be minimum of 3 characters and maximum of 256 characters")
   // @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",message = "Password must be minimum eight characters, at least one letter and one number:")
    private String password;
    @NotNull(message = "Organization id is missing")
    @ManyToOne
    private  Organization fk_orgobj;

    @ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)//data will be loaded on the spot
    @JoinTable(name="role_assign",joinColumns = @JoinColumn(name="Employee",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name="Role",referencedColumnName = "id"))
    private Set<Role> roles=new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //return the authority granted to the user
        List<GrantedAuthority> authorities=this.roles.stream().map((role -> new SimpleGrantedAuthority(role.getRoleName()))).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword(){
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
