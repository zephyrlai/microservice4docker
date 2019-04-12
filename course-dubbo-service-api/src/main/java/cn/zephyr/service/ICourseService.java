package cn.zephyr.service;

import cn.zephyr.dto.CourseDTO;

import java.util.List;

/**
 * @Author: zephyrLai
 * @Description: TODO
 * @Date: 2019/4/12 11:16
 */
public interface ICourseService {

    List<CourseDTO> queryList();
}
