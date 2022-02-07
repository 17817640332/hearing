package com.jie.service.impl;

import com.jie.pojo.Count;
import com.jie.mapper.CountMapper;
import com.jie.service.CountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jie
 * @since 2021-03-31
 */
@Service
public class CountServiceImpl extends ServiceImpl<CountMapper, Count> implements CountService {

}
