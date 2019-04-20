package cn.zephyr.controller;

import cn.zephyr.dto.ResultDTO;
import cn.zephyr.service.ICourseService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zephyrLai
 * @Description: 对外课程服务
 * @Date: 2019/4/12 16:58
 */
@RequestMapping("course")
@RestController
public class CourseController {

    @Reference(timeout = 300000)
    private ICourseService iCourseService;

    @RequestMapping("queryList")
    public ResultDTO queryCourseList(){
        return ResultDTO.operSucc(iCourseService.queryList());
    }
}
