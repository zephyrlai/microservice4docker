package cn.zephyr.controller;

import cn.zephyr.dto.ResultDTO;
import cn.zephyr.dto.UserInfoDTO;
import cn.zephyr.redis.RedisClient;
import cn.zephyr.thrift.ServiceProvider;
import cn.zephyr.thrift.user.UserInfo;
import cn.zephyr.utils.MD5Utils;
import cn.zephyr.utils.RandomCodeUtils;
import org.apache.thrift.TException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value="login",method = RequestMethod.GET)
    public String loginPage(){
        return "login";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody
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
        System.err.println(MD5Utils.md5(password));
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

    /**
     * 给邮箱或者手机发送验证码（默认邮箱）
     * @param email
     * @param mobile
     * @return
     */
    @RequestMapping("sendVerifyCode")
    @ResponseBody
    public ResultDTO sendVerifyCode(@RequestParam(required = false) String email,
                                    @RequestParam(required = false) String mobile) throws Exception {
        String verifyCode = RandomCodeUtils.genVerifyCode(6);
        if(!StringUtils.isEmpty(email)){
            serviceProvider.getMessageService().send_sendEmailMessage(email,verifyCode);
            redisClient.set(email,verifyCode);
            redisClient.expire(email,3000);
            return ResultDTO.operSucc(null);
        }else if(!StringUtils.isEmpty(mobile)){
            serviceProvider.getMessageService().send_sendMobileMessage(mobile,verifyCode);
            redisClient.set(mobile,verifyCode);
            redisClient.expire(mobile,3000);
            return ResultDTO.operSucc(null);
        }
        return ResultDTO.EMAIL_OR_MOBILE_REQUIRED;
    }

    /**
     * 用户注册
     * @param username
     * @param password
     * @param email
     * @param mobile
     * @param verifyCode
     * @return
     */
    @RequestMapping("register")
    @ResponseBody
    public ResultDTO userRegister(  @RequestParam String username,
                                    @RequestParam String password,
                                    @RequestParam(required = false) String email,
                                    @RequestParam(required = false) String mobile,
                                    @RequestParam String verifyCode){
        if(StringUtils.isEmpty(email) && StringUtils.isEmpty(mobile)){
            return ResultDTO.EMAIL_OR_MOBILE_REQUIRED;
        }
        String verifyCodeFromCache;
        if(!StringUtils.isEmpty(email)){
            verifyCodeFromCache = redisClient.get(email);
        }else{
            verifyCodeFromCache = redisClient.get(mobile);
        }
        if(StringUtils.isEmpty(verifyCodeFromCache) || !verifyCodeFromCache.equals(verifyCode)){
            return ResultDTO.VERIFY_CODE_INVALID;
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(MD5Utils.md5(password));
        userInfo.setEmail(email);
        userInfo.setMobile(mobile);
        try {
            serviceProvider.getUserService().registerUser(userInfo);
        } catch (TException e) {
            e.printStackTrace();
            return ResultDTO.OPEARTION_FAILURE;
        }
        return ResultDTO.operSucc(null);

    }
}
