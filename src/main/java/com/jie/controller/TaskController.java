package com.jie.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jie.pojo.Count;
import com.jie.pojo.RespBean;
import com.jie.pojo.Task;
import com.jie.service.impl.CountServiceImpl;
import com.jie.service.impl.TaskServiceImpl;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 班级任务 前端控制器
 * </p>
 *
 * @author jie
 * @since 2021-03-02
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskServiceImpl taskService;

    @Autowired
    private CountServiceImpl countService;
    @ApiOperation(value = "在班级内发布任务")
    @PostMapping("/addTask")
    public RespBean addTask(@RequestBody Task task){
        taskService.save(task);
    return RespBean.success("发布成功");
    }

    @ApiOperation(value = "查看发布任务")
    @GetMapping("/getTasks")
    public List<Task> getTasks(Integer classId){
        return taskService.list(new QueryWrapper<Task>().eq("class_id",classId) );
    }

    @ApiOperation(value = "删除发布任务")
    @DeleteMapping("/deleteTaskById")
    public RespBean deleteTaskById(Integer taskId){
        boolean id = taskService.remove(new QueryWrapper<Task>().eq("id", taskId));
        if(id){
            return RespBean.success("删除成功");
        }else {
            return RespBean.error("删除失败");
        }
    }



    @ApiOperation(value = "查看已完成的任务")
    @GetMapping("/findwc")
    public List<Task> findwc(@RequestParam("classId") Integer classId,
                             @RequestParam("studentId")  Integer studentId){

        List<Task> sad = taskService.list(new QueryWrapper<Task>().eq("class_id", classId));
       List<Task> tasks1 = new ArrayList<>();
        if (!sad.isEmpty()) {

            for (Task task : sad) {

                List<Count> tasks = countService.list(new QueryWrapper<Count>().
                        eq("student_id", studentId).eq("task_id", task.getId()));

                if (tasks.isEmpty()) {
                   continue;
                }else {
                    tasks1.add(task);
                }
            }
            return tasks1;
        }

        return null;
    }



    @ApiOperation(value = "查看未完成的任务")
    @GetMapping("/findwwc")
    public List<Task> findwwc(@RequestParam("classId") Integer classId,
                              @RequestParam("studentId")  Integer studentId) {

        List<Task> sad = taskService.list(new QueryWrapper<Task>().eq("class_id", classId));
        ArrayList<Task> tasks1 = new ArrayList<>();
        if (!sad.isEmpty()) {

            for (Task task : sad) {

                List<Count> tasks = countService.list(new QueryWrapper<Count>().
                        eq("student_id", studentId).eq("task_id", task.getId()));

                if (tasks.isEmpty()) {
                    tasks1.add(task);
                }
            }
            return tasks1;
        }

        return null;
    }



    @ApiOperation("查看已完成的学生的成绩")
    @GetMapping("/getCountWc")
    public RespBean getCountWc(@RequestParam("studentId")  Integer studentId,@RequestParam("taskId" ) Integer taskId){

        List<Count> list = countService.list(new QueryWrapper<Count>().eq("student_id", studentId).eq("task_id", taskId));
        return RespBean.success("成绩：",list.get(0).getCount());

    }
}

