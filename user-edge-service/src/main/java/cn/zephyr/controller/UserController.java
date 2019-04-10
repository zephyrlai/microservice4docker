package cn.zephyr.controller;

import cn.zephyr.dto.ResultDTO;
import cn.zephyr.dto.UserInfoDTO;
import cn.zephyr.redis.RedisClient;
import cn.zephyr.thrift.ServiceProvider;
import cn.zephyr.thrift.user.UserInfo;
import cn.zephyr.utils.MD5Utils;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.apache.thrift.TException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: zephyrLai
 * @Description: TODO
 * @Date: 2019/4/10 17:12
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private ServiceProvider serviceProvider;

    @Autowired
    private RedisClient redisClient;

    @RequestMapping("login")
    public ResultDTO userLogin(String username,String password){
        // 1. 验证用户密码
        UserInfo userByName;
        try {
            userByName = serviceProvider.getUserService().getUserByName(username);

        } catch (TException e) {
            e.printStackTrace();
            return ResultDTO.USERNAME_PASSWORD_INVALID;
        }
        if(null == userByName)
            return ResultDTO.USERNAME_PASSWORD_INVALID;
        if(!userByName.getPassword().equals(MD5Utils.md5(password)))
            return ResultDTO.USERNAME_PASSWORD_INVALID;
        // 2. 生成token
        String token = UUID.randomUUID().toString().replace("-","");
        // 3. 缓存用户信息
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        BeanUtils.copyProperties(userByName,userInfoDTO);
        redisClient.set(token,userInfoDTO,3600);
        Map<String,String> dataMap = new HashMap<String,String>();
        dataMap.put("token",token);
        return ResultDTO.operSucc(dataMap);
    }
}
