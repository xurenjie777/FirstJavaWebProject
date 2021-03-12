package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.service.StaffService;
import com.example.demo.mapper.StaffMapper;
import com.example.demo.pojo.Staff;
import java.util.List;

@Service
public  class StaffServiceimpl implements StaffService{

    @Autowired StaffMapper staffMapper;

    @Override
    public int add(Staff staff) {
        return staffMapper.add(staff);
    }

    @Override
    public void delete(int id) {
        staffMapper.delete(id);
    }

    @Override
    public Staff get(int id) {
        return staffMapper.get(id);
    }

    @Override
    public int update(Staff staff) {
        return staffMapper.update(staff);
    }

    @Override
    public List<Staff> list() {
        return staffMapper.list();
    }

}
