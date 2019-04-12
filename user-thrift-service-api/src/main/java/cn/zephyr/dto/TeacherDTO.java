package cn.zephyr.dto;

import lombok.Data;

/**
 * @Author: zephyrLai
 * @Description: TODO
 * @Date: 2019/4/12 11:11
 */
@Data
public class TeacherDTO extends UserInfoDTO{
    private String intro;
    private int stars;
}
