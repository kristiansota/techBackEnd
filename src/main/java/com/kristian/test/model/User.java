package com.kristian.test.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kristian.test.annotations.UniqueEmail;
import com.kristian.test.annotations.UniqueUsername;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"), name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinTable (
            name = "user_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "roleId"))

    private Set<Roles> roles;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, name = "username", unique = true)
    @UniqueUsername
    @NotEmpty
    private String username;

    @Column(nullable = false, name = "password")
    @NotEmpty
    private String password;

    @Column(unique = true, nullable = false, name = "email")
    @NotEmpty
    @NotNull
    @Email
    @UniqueEmail(message = "Email is already registered")
    private String email;

    @Column(nullable = false, name = "name")
    @NotEmpty
    private String name;

    @Column(nullable = false, name = "age")
    private int age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "to_date")
    private Date to_date;

    public Date getTo_date() {
        return to_date;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }
}
