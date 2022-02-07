package com.jie.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jie.pojo.Word;
import com.jie.service.WordService;
import com.jie.service.impl.WordServiceImpl;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/word")
public class WordController {



@Autowired
private WordServiceImpl wordService;
  @GetMapping("/getWords")
    @ApiOperation(value = "根据书本(大英读学3)和单元(U1/U2/U3)获取单词")
    public List<Word> getWords(@RequestParam("book") String book,@RequestParam("unit") String unit){
    return  wordService.list(new QueryWrapper<Word>().eq("book",book).eq("unit",unit));
  }




  @ApiOperation(value = "获取当前任务的单词")
  @GetMapping("/getWordsByTaskId")
  public List<Word> getWordsByTaskId(@RequestParam("taskId") Integer taskId){

    List<Word> wordsByTaskId = wordService.getWordsByTaskId(taskId);
    System.out.println(wordsByTaskId);
    return wordsByTaskId;
  }
}

