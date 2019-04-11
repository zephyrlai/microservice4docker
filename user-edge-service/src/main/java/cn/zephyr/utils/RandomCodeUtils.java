package cn.zephyr.utils;

import java.util.Random;

/**
 * @Author: zephyrLai
 * @Description: 随机字符串工具类
 * @Date: 2019/4/11 9:56
 */
public class RandomCodeUtils {

    private static final String NUMBER_ARRAY = "0123456789";

    public static String genVerifyCode(Integer length){
        if(null == length || length == 0){
            return "";
        }
        StringBuilder result = new StringBuilder(length);
        Random random = new Random();
        for (Integer i = 0; i < length; i++) {
            int loc = random.nextInt(NUMBER_ARRAY.length());
            result.append(NUMBER_ARRAY.charAt(loc));
        }
        return result.toString();
    }
}
