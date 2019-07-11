package com.mqb.springbootspringsecurity.pojo;

import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name = "t_hobby")
public class Hobby {
    private Integer id;
    private String hname;

    @Override
    public String toString() {
        return "Hobby{" +
                "id=" + id +
                ", hname='" + hname + '\'' +
                '}';
    }
}
