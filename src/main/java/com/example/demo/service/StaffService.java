package com.example.demo.service;
import java.util.List;
import com.example.demo.pojo.Staff;

public interface StaffService {
    public int register(Staff staff);
    public void delete(int id);
    public Staff get(int id);
    public int update(Staff staff);
    public List<Staff> list();
    public Staff findByStaffName(String name);
    public List<Staff> findByStaffDepartment(String department);
}
