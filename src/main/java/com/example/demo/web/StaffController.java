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
            staff = redisTools.get(id1);
        }
        else{
            staff = staffService.get(id);
            redisTools.set(id1,staff);
        }
        return staff;
    }

    @PostMapping("/signin")
    public Staff signIn(@RequestParam("username") String username,
                      @RequestParam("password") String password,HttpServletRequest request, HttpServletResponse response)
    {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        System.out.println("请求发送成功");
        Staff staff = staffService.findByStaffName(username);
        if(staff.equals(null)){
            staff.setCode(400);
            System.out.println("用户不存在");
        }
        if(staff.getPassword().equals(password)){
            System.out.println("登陆成功");
            System.out.println(staff.toString());
            staff.setCode(200);
            return staff;
        }
        else{
            staff.setCode(400);
            System.out.println("密码错误");
        }
        return staff;
    }

    @GetMapping("/staff/findByStaffDepartment")
    public List<Staff> findByStaffDepartment(@RequestParam("department") String department){
        List<Staff> staffs;
        if(redisTools.exists(department)){
            staffs = redisTools.getList(department);
        }
        else{
            staffs = staffService.findByStaffDepartment(department);
            redisTools.setList(department,staffs);
        }
        return staffs;
    }

    @GetMapping("/staff/findByStaffName")
    public Staff findByStaffName(@RequestParam("username") String name){
        Staff staff;
        if(redisTools.exists(name)){
            staff = redisTools.get(name);
        }
        else{
            staff = staffService.findByStaffName(name);
            redisTools.set(name,staff);
        }
        return staff;
    }

    @PostMapping("/register")
    public int add(@RequestParam("username") String username,
                   @RequestParam("password") String password,
                   @RequestParam("sex") String sex,
                   @RequestParam("department") String department,
                   @RequestParam("level") int level,HttpServletRequest request, HttpServletResponse response)
    {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        Staff staff = new Staff(200);
        staff.setName(username);
        staff.setDepartment(department);
        staff.setLevel(level);
        staff.setSex(sex);
        staff.setPassword(password);
        return staffService.register(staff);//0用户名已存在，1成功
    }

    @PostMapping("/delete")
    public void delete(@RequestParam("id") Integer id,
                       @RequestParam("username") String username,
                       @RequestParam("department") String department
                       ){
        String id1 = id.toString();
        if(redisTools.exists(id1)) {
            redisTools.delete(id1);
        }
        List<Staff> staffs = redisTools.getList(department);
        for(Staff e:staffs){
            if (e.getId() == id) {
                staffs.remove(e);
                redisTools.setList(department,staffs);
                break;
            }
        }
        if(redisTools.exists(username)){
            redisTools.delete(username);
        }
        staffService.delete(id);
    }




}
