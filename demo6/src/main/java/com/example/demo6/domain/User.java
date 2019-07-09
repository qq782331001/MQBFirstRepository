package com.example.demo6.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id")
    private Long uId;
    @Column(name = "u_name")
    private String uName;
    @JsonIgnoreProperties(value = { "users" })
    @ManyToMany(targetEntity = Role.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "tb_user_role",
            joinColumns = {@JoinColumn(name = "tb_user_id",referencedColumnName = "u_id")},
            inverseJoinColumns = {@JoinColumn(name="tb_role_id",referencedColumnName = "r_id")})
    private Set<Role> roles = new HashSet<>();

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "uId=" + uId +
                ", uName='" + uName + '\'' +
                ", roles=" + roles +
                '}';
    }
}
