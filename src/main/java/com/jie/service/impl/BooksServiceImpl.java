package com.jie.service.impl;

import com.jie.pojo.Books;
import com.jie.mapper.BooksMapper;
import com.jie.service.BooksService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 书表 服务实现类
 * </p>
 *
 * @author jie
 * @since 2021-03-31
 */
@Service
public class BooksServiceImpl extends ServiceImpl<BooksMapper, Books> implements BooksService {

}
