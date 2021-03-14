package com.example.demo.pojo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Staff {
    private int id;
    private String name;
    private String sex;
    private String password;
    private int level;
    private String department;
    private int code;
    public Staff(int code){
        this.code = code;
    }

    @Override
    public String toString() {
        return "Staff [id=" + id + ", name=" + name  + ", sex=" + sex + ", password=" + password + ", level=" + level + ", department=" + department + "]";
    }
}

