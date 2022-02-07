package com.jie.service;

import com.jie.pojo.Word;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jie
 * @since 2021-03-02
 */
public interface WordService extends IService<Word> {
    List<Word> getWordsByTaskId(Integer taskId);
}
