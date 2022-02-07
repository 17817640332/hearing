package com.jie.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jie.pojo.*;
import com.jie.service.impl.*;
import io.swagger.annotations.Api;
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
 * @since 2021-03-31
 */
@RestController
@RequestMapping("/count")
@Api("学生成绩")
public class CountController {

   @Autowired
   private WordServiceImpl wordService;

   @Autowired
   private StudentServiceImpl studentService;
    @Autowired
    private AverageServiceImpl averageService;

    @Autowired
    private CountServiceImpl countService;

    @Autowired
    private TaskServiceImpl taskService;

    @Autowired
    private ClassStudentServiceImpl classStudentService;
    @PostMapping("/getCount")
    @ApiOperation("答题后提交得分数")
    public RespBean getCount(@RequestBody List<CountVO> resultWords, @RequestParam("taskId") Integer taskId,@RequestParam("studentId") Integer studentId){

        Student student = studentService.getById(studentId);
        Integer id = student.getId();
        int Count=0;
        int TotalCount=0;
        List<Word> taskWords= wordService.getWordsByTaskId(taskId);

        for (int i = 0; i < taskWords.size(); i++) {

               if ((resultWords.get(i).getWord()).equals(taskWords.get(i).getWordName())) {
                   Count += 1;
           }
        }

        if(Count==resultWords.size()){
            TotalCount=100;
        }else {
            TotalCount=(100/resultWords.size())*Count;
        }
        //保存成绩
        Count count = new Count();
        count.setStudentId(id);
        count.setTaskId(taskId);
        count.setCount(TotalCount);
        countService.save(count);

        //求平均成绩
        int totalCount=0;
        List<Count> list = countService.list(new QueryWrapper<Count>().eq("student_id",id));
        for (Count count1 : list) {
            totalCount+=count1.getCount();
        }
        int averageCount=totalCount/list.size();

        Average average = new Average();
        average.setStudentId(id);
        average.setAverageCount(averageCount);
        List<Average> student1 = averageService.list(new QueryWrapper<Average>().eq("student_id", id));
        System.out.println(student1);
        if(!student1.isEmpty()) {
            average.setStudentId(id);
            averageService.updateById(average);
        }else {
            averageService.save(average);
        }

        List<Task> tasks = taskService.list(new QueryWrapper<Task>().eq("id", taskId));
        Task task = tasks.get(0);
        return RespBean.success("分数为"+TotalCount,task);
    }


    @GetMapping("/getStudentCount")
    @ApiOperation("老师获取当前班级学生的成绩平均成绩")
    public List<Average> getAverageCount(@RequestParam("classId") Integer classId){
        List<ClassStudent> classStudents = classStudentService.list(new QueryWrapper<ClassStudent>().eq("class_id", classId));
        ArrayList<Average> classStudentAverage = new ArrayList<>();
        for (ClassStudent classStudent : classStudents) {
            Integer studentId = classStudent.getStudentId();
            List<Average> averages = averageService.list(new QueryWrapper<Average>().eq("student_id", studentId));
            Average average = averages.get(0);
            classStudentAverage.add(average);
        }
        return classStudentAverage;
    }
}



