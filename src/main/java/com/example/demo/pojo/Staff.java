package com.example.demo.pojo;

public class Staff {
    private int id;
    private String name;
    private String sex;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    @Override
    public String toString() {
        return "Staff [id=" + id + ", name=" + name  + ", sex=" + sex + "]";
    }
}

