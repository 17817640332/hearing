package com.jie.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jie.pojo.*;
import com.jie.pojo.Class;
import com.jie.service.ClassService;
import com.jie.service.impl.ClassServiceImpl;
import com.jie.service.impl.ClassStudentServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jie
 * @since 2021-03-02
 */
@RestController
@RequestMapping("/class")
public class ClassController {
    @Autowired
    private ClassServiceImpl classService;
    @Autowired
    private ClassStudentServiceImpl classStudentService;


    @ApiOperation("创建班级")
    @PostMapping("/addClass")
  @JsonIgnoreProperties(value = {"teacherId","studentAmount"})
    public RespBean addClass(@RequestBody Class aClass,@RequestParam("teacherId") Integer teacherId ){
        aClass.setTeacherId(teacherId);
        aClass.setStudentAmount(0);
        boolean save = classService.save(aClass);
        if(save){
            return RespBean.success("创建成功",aClass);
        }
        return RespBean.error("创建失败");
    }

    @DeleteMapping("/deleteClass")
    @ApiOperation(value = "删除班级")
    public RespBean deleteClass(@RequestParam("classId") String classId){
        boolean b = classService.removeById(classId);
        if(b){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @PostMapping("updateClassName")
    @ApiOperation(value ="编辑班级名称")
    public RespBean updateClassName(@RequestParam("classId") String classId, @RequestParam("className") String className){
        Class cl= classService.getById(classId);
        cl.setClassName(className);
        boolean b = classService.updateById(cl);
        if (b){
            return RespBean.success("修改成功");
        }
        return RespBean.error("修改失败");
    }



    @PostMapping("/addClassStudent")
    @ApiOperation(value = "学生加入班级")
    public RespBean addClassStudent(@RequestParam("studentId") Integer id,@RequestParam("code") String code){


        List<Class> aClass = classService.list(new QueryWrapper<Class>().eq("code", code));
        if(aClass!=null){
            Class aClass1 = aClass.get(0);
            Integer classId1 = aClass1.getId();
            List<Class> classes = classService.list(new QueryWrapper<Class>().eq("id", classId1));
            Class cl=classes.get(0);
            cl.setStudentAmount((cl.getStudentAmount())+1);
            classService.updateById(cl);
            ClassStudent classStudent = new ClassStudent();
            classStudent.setClassId(aClass1.getId());
            classStudent.setStudentId(id);
            classStudentService.save(classStudent);
            return RespBean.success("加入成功");
        }else {
            return RespBean.error("加入失败");
        }
    }


    @GetMapping("/getClassesByTeacher")
    @ApiOperation(value = "(老师)获取老师创建的班级")
    public List<Class> getClassesByTeacher(@RequestParam("teacherId") Integer teacherId){

        return classService.list(new QueryWrapper<Class>().eq("teacher_id", teacherId));
    }

    @GetMapping("/getClassesByStudent")
    @ApiOperation(value ="学生获取加入的班级")
   public List<Class> getClassesByStudent(@RequestParam("studentId") Integer studentId){
        List<Class> classes = new ArrayList<>();
        List<ClassStudent> classStudents = classStudentService.list(new QueryWrapper<ClassStudent>().eq("student_id", studentId));
        for (ClassStudent classStudent : classStudents) {
            Integer classId = classStudent.getClassId();
            classes.add((classService.list(new QueryWrapper<Class>().eq("id",classId)).get(0)));
        }
        return classes;
    }
}

