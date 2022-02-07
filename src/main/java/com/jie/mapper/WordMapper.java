package com.jie.mapper;

import com.jie.pojo.Word;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jie
 * @since 2021-03-02
 */
public interface WordMapper extends BaseMapper<Word> {


   public List<Word> getWordsByTaskId(Integer taskId);
}
