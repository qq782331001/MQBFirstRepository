package com.example.demo6.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "tb_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_id")
    private  Long rId;
    @Column(name = "r_name")
    private String rName;
    @Column(name = "r_qu")
    private String rQu;
    @JsonIgnoreProperties(value = { "roles" })
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public Long getrId() {
        return rId;
    }

    public void setrId(Long rId) {
        this.rId = rId;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getrQu() {
        return rQu;
    }

    public void setrQu(String rQu) {
        this.rQu = rQu;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "rId=" + rId +
                ", rName='" + rName + '\'' +
                ", rQu='" + rQu + '\'' +
                '}';
    }
}
