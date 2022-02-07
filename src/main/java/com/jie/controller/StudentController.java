package com.jie.controller;


import com.jie.pojo.RespBean;
import com.jie.pojo.Student;
import com.jie.service.StudentService;
import com.jie.service.impl.StudentServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;

/**
 * <p>
 * 学生 前端控制器
 * </p>
 *
 * @author jie
 * @since 2021-03-02
 */
@RestController
@RequestMapping("/student")
public class StudentController {


    @Autowired
    private StudentServiceImpl studentService;

    @PostMapping("/login")
    @ApiOperation("登录")
    public RespBean login(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpSession session){
      return studentService.login(userName,password,session);
    }

    @PostMapping("/register")
    @ApiOperation("注册")
    public RespBean register(@RequestBody Student student){
        boolean save = studentService.save(student);
        if(save){
            return RespBean.success("注册成功");
        }else {
            return RespBean.error("注册失败");
        }
    }
    @PostMapping("/logout")
    @ApiOperation(value = "退出登录")
    public RespBean logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return RespBean.success("退出成功");
    }

    @PostMapping("/updatePassword")
    @ApiOperation(value = "修改密码")
    public RespBean updatePassword(HttpSession session, @RequestParam("password") String password) {
        Student student = ((Student) session.getAttribute("student"));
        if (null != student) {
            student.setPassword(password);
            studentService.updateById(student);
            return RespBean.success("修改成功");
        }
        return RespBean.error("修改失败");

    }

    @PostMapping("/updateFace")
    @ApiOperation(value = "修改头像")
    public RespBean updateFace(HttpSession session, @RequestParam("face") MultipartFile img) {
        Student student = (Student) session.getAttribute("student");
        String path = System.getProperty("user.dir") + "/upload/";
        File realPath = new File(path);
        if (!realPath.exists()) {
            realPath.mkdir();
        }
        if (null != student) {

            if (null != img && img.getSize() > 0) {
                String imgName = img.getOriginalFilename();
                try {
                    img.transferTo(new File(realPath + "/" + imgName));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                student.setFace("/upload/"+imgName);
                studentService.updateById(student);
                return RespBean.success("修改成功");
            }
        }
        return RespBean.error("修改失败");
    }
}

