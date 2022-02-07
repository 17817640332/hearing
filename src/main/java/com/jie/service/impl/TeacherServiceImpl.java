package com.jie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jie.pojo.RespBean;
import com.jie.pojo.Teacher;
import com.jie.mapper.TeacherMapper;
import com.jie.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jie
 * @since 2021-03-02
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;
    @Override
    public RespBean login(String userName, String password, HttpSession session) {
        List<Teacher> teachers = teacherMapper.selectList(new QueryWrapper<Teacher>().eq("user_name", userName));
        Teacher teacher=teachers.get(0);
        if(null!=password&&password.equals(teacher.getPassword())){
            session.setAttribute("teacher",teacher);
            return RespBean.success("登录成功",teacher);
        }
    return RespBean.error("登录失败");
    }
}
