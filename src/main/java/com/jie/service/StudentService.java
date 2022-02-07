package com.jie.service;

import com.jie.pojo.RespBean;
import com.jie.pojo.Student;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 学生 服务类
 * </p>
 *
 * @author jie
 * @since 2021-03-02
 */
public interface StudentService extends IService<Student> {

    RespBean login(String userName, String password, HttpSession session);
}
