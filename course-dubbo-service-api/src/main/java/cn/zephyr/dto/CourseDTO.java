package cn.zephyr.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: zephyrLai
 * @Description: TODO
 * @Date: 2019/4/12 11:09
 */
@Data
public class CourseDTO implements Serializable {
    private int id;
    private String title;
    private String description;
    private TeacherDTO teacher;

}
