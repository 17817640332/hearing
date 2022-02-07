package com.jie.service.impl;

import com.jie.pojo.TaskWord;
import com.jie.mapper.TaskWordMapper;
import com.jie.pojo.Word;
import com.jie.service.TaskWordService;
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
public class TaskWordServiceImpl extends ServiceImpl<TaskWordMapper, TaskWord> implements TaskWordService {

    @Autowired
    private TaskWordMapper taskWordMapper;

}
