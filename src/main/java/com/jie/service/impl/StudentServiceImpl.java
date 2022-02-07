package com.jie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jie.pojo.RespBean;
import com.jie.pojo.Student;
import com.jie.mapper.StudentMapper;
import com.jie.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 学生 服务实现类
 * </p>
 *
 * @author jie
 * @since 2021-03-02
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public RespBean login(String userName, String password, HttpSession session) {
        List<Student> students= studentMapper.selectList(new QueryWrapper<Student>().eq("user_name",userName));
        Student student =students.get(0);
        if(null!=password&&password.equals(student.getPassword())){
            session.setAttribute("student",student);
            return RespBean.success("登陆成功",student);
        }
        return RespBean.error("登录失败");
    }
}
