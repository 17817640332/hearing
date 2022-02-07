package com.jie.controller;


import com.jie.pojo.RespBean;

import com.jie.pojo.Teacher;
import com.jie.service.TeacherService;
import com.jie.service.impl.TeacherServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jie
 * @since 2021-03-02
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {


    @Autowired
    private TeacherServiceImpl teacherService;

    @PostMapping("/login")
    @ApiOperation("登录")
    public RespBean login(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpSession session){
        return teacherService.login(userName,password,session);
    }

    @PostMapping("/register")
    @ApiOperation("注册(code激活码44789)")
    public RespBean register(@RequestBody Teacher teacher,String code){
        if(null!=code&&code.equals("44789")) {
            boolean save = teacherService.save(teacher);
            if (save) {
                return RespBean.success("注册成功");
            }else return RespBean.error("注册失败");
        }
        return RespBean.error("激活码错误");
    }
    @PostMapping("/logout")
    @ApiOperation(value = "退出登录")
    public RespBean logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return RespBean.success("退出成功");
    }

    @PostMapping("/updatePassword")
    @ApiOperation(value = "修改密码")
    public RespBean updatePassword( @RequestParam("password") String password,@RequestParam("password") Integer teacherId) {
        Teacher teacher = teacherService.getById(teacherId);

        if (null != teacher) {
            teacher.setPassword(password);
            teacherService.updateById(teacher);
            return RespBean.success("修改成功");
        }
        return RespBean.error("修改失败");
    }
}

