package com.jie.mapper;

import com.jie.pojo.TaskWord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jie.pojo.Word;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jie
 * @since 2021-03-02
 */
public interface TaskWordMapper extends BaseMapper<TaskWord> {

    List<Word> getWordsByTaskId(Integer taskId);

}
