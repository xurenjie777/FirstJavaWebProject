package com.example.demo.web;
import java.util.List;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Staff;
import com.example.demo.service.StaffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.example.demo.redis.RedisTools;

@RestController
public class StaffController {
    @Autowired
    StaffService staffService;

    @Autowired
    RedisTools redisTools;

    @GetMapping("/staff/findAllStaff")
    public List<Staff> findAllStaff(HttpServletRequest request, HttpServletResponse response)
    {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");

        List<Staff> staffs;


        staffs = staffService.list();
        return staffs;
    }

    @GetMapping("/staff/findStaffById")
    public Staff findStaffById(@RequestParam("id") Integer id)
    {
        Staff staff;
        String id1 = id.toString();
        if(redisTools.exists(id1)){
            System.out.println("从缓存读取");
            staff = redisTools.get(id1);
        }
        else{
            staff = staffService.get(id);
            System.out.println("从数据库读取");
            redisTools.set(id1,staff);
        }
        return staff;

    }

}
