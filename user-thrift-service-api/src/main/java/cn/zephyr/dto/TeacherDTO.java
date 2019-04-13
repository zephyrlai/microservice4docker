package cn.zephyr.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: zephyrLai
 * @Description: TODO
 * @Date: 2019/4/12 11:11
 */
@Data
public class TeacherDTO extends UserInfoDTO implements Serializable {
    private String intro;
    private int stars;
}
