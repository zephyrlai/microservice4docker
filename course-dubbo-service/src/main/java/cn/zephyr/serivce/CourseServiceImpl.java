package cn.zephyr.serivce;

import cn.zephyr.dto.CourseDTO;
import cn.zephyr.dto.TeacherDTO;
import cn.zephyr.mapper.CourseMapper;
import cn.zephyr.service.ICourseService;
import cn.zephyr.thrift.user.UserInfo;
import com.alibaba.dubbo.config.annotation.Service;
import org.apache.thrift.TException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: zephyrLai
 * @Description: TODO
 * @Date: 2019/4/12 11:17
 */
@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ServiceProvider serviceProvider;

    @Override
    public List<CourseDTO> queryList() {

        // 首先获取所有的课程
        List<CourseDTO> courseDTOS = courseMapper.queryList();
        if(courseDTOS!=null) {
            // 遍历获取教师信息
            for(CourseDTO courseDTO : courseDTOS) {
                Integer teacherId = courseMapper.getCourseTeacher(courseDTO.getId());
                if(teacherId!=null) {
                    try {
                        UserInfo userInfo = serviceProvider.getUserService().getTeacherById(teacherId);
                        courseDTO.setTeacher(trans2Teacher(userInfo));
                    } catch (TException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }
        }
        return courseDTOS;
    }

    private TeacherDTO trans2Teacher(UserInfo userInfo) {
        TeacherDTO teacherDTO = new TeacherDTO();
        BeanUtils.copyProperties(userInfo, teacherDTO);
        return teacherDTO;
    }
}
