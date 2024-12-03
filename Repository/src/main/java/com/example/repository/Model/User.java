package com.example.repository.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Check(constraints = "role= 'user' or role= 'admin'")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name is empty")
    @Size(min = 4, message = "name must be at least 4 or more characters")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotEmpty(message = "username is empty")
    @Size(min = 4,message = "username must be more than 4 characters")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String username;

    @NotEmpty(message = "password is empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String password;

    @NotEmpty(message = "email is empty")
    @Email
    @Column(columnDefinition = "varchar(20) not null unique")
    private String email;

    @NotEmpty(message = "role is empty")
    @Pattern(regexp = "^(user|admin)",message = "role must be either 'user' or 'admin' ")
    @Column(columnDefinition = "varchar(5) not null")
    private String role;

    @NotNull(message = "age is null")
    @Column(columnDefinition = "int not null")
    private Integer age;
}
