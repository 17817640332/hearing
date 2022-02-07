package com.jie.controller;


import com.jie.pojo.RespBean;
import com.jie.pojo.TaskWord;
import com.jie.pojo.Word;
import com.jie.service.TaskWordService;
import com.jie.service.impl.TaskWordServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Api(value = "任务单词")
@RestController
@RequestMapping("/taskWord")
public class TaskWordController {

    @Autowired
    private TaskWordServiceImpl taskWordService;


    @ApiOperation(value = "添加任务单词")
    @PostMapping("/addTaskWords")
    public RespBean addTaskWords(@RequestBody List<Word> words, @RequestParam("taskId") Integer taskId){
        ArrayList<Integer> wordsId = new ArrayList<>();
        for (Word word : words) {
            wordsId.add(word.getId());
        }
        for (Integer o : wordsId) {
            TaskWord taskWord = new TaskWord();
            taskWord.setTaskId(taskId);
            taskWord.setWordId(o);
            taskWordService.save(taskWord);
        }
        return RespBean.success("添加成功");
    }


}

