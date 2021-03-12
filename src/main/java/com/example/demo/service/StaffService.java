package com.example.demo.service;
import java.util.List;
import com.example.demo.pojo.Staff;

public interface StaffService {
    public int add(Staff staff);
    public void delete(int id);
    public Staff get(int id);
    public int update(Staff hero);
    public List<Staff> list();
}
