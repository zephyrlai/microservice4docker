package cn.zephyr.mapper;

import cn.zephyr.dto.CourseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: zephyrLai
 * @Description: TODO
 * @Date: 2019/4/12 11:20
 */
@Mapper
public interface CourseMapper {
    /**
     * 课程查询
     * @return
     */
    @Select("select id,title,description from pe_course")
    List<CourseDTO> queryList();


    /**
     * 根据课程id获取教师id
     * @param courseId
     * @return
     */
    @Select("select user_id from pr_user_course where course_id=#{courseId}")
    Integer getCourseTeacher(@Param("courseId") int courseId);
}
