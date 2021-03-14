package com.example.demo.service.impl;

import com.example.demo.redis.RedisTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.service.StaffService;
import com.example.demo.mapper.StaffMapper;
import com.example.demo.pojo.Staff;
import java.util.List;

@Service
public  class StaffServiceimpl implements StaffService{

    @Autowired StaffMapper staffMapper;
    @Autowired RedisTools redisTools;
    @Override
    public int register(Staff staff) {
        Staff test = staffMapper.findByStaffName(staff.getName());
        if(test == null){
            test = staff;
            staffMapper.add(test);
            redisTools.set(staff.getName(),staff);
            List<Staff> staffs = redisTools.getList(staff.getDepartment());
            staffs.add(staff);
            redisTools.setList(staff.getDepartment(),staffs);
            System.out.println("成功");
            return 1;
        }
        System.out.println("失败");
        return 0;
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

    @Override
    public Staff findByStaffName(String name){
        return staffMapper.findByStaffName(name);
    }

    @Override
    public List<Staff> findByStaffDepartment(String department){
        return staffMapper.findByStaffDepartment(department);
    }

}
