package com.jie.service.impl;

import com.jie.pojo.Word;
import com.jie.mapper.WordMapper;
import com.jie.service.WordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class WordServiceImpl extends ServiceImpl<WordMapper, Word> implements WordService {

    @Autowired
    private WordMapper wordMapper;
   public List<Word> getWordsByTaskId(Integer taskId) {
       return wordMapper.getWordsByTaskId(taskId);
    }
}
