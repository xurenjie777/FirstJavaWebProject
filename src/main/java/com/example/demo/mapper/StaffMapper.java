package com.example.demo.mapper;
import java.util.List;
import com.example.demo.pojo.Staff;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StaffMapper {
    public int add(Staff staff) ;
    public void delete(int id);
    public Staff get(int id);
    public int update(Staff staff);
    public List<Staff> list();

}
