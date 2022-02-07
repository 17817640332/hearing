package com.jie.service.impl;

import com.jie.pojo.Task;
import com.jie.mapper.TaskMapper;
import com.jie.service.TaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 班级任务 服务实现类
 * </p>
 *
 * @author jie
 * @since 2021-03-02
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

}
