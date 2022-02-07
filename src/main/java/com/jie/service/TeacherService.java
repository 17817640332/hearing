package com.jie.service;

import com.jie.pojo.RespBean;
import com.jie.pojo.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jie
 * @since 2021-03-02
 */
public interface TeacherService extends IService<Teacher> {

    RespBean login(String userName, String password, HttpSession session);
}
