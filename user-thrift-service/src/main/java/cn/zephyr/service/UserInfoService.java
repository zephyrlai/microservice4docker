package cn.zephyr.service;

import cn.zephyr.mapper.UserMapper;
import cn.zephyr.thrift.user.UserInfo;
import cn.zephyr.thrift.user.UserService;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: zephyrLai
 * @Description: TODO
 * @Date: 2019/4/10 14:08
 */
@Service
public class UserInfoService implements UserService.Iface{

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserInfo getUserById(int id) throws TException {
        return userMapper.getUserById(id);
    }

    @Override
    public UserInfo getTeacherById(int id) throws TException {
        return userMapper.getTeacherById(id);
    }

    @Override
    public UserInfo getUserByName(String username) throws TException {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public void registerUser(UserInfo userInfo) throws TException {
        userMapper.registerUser(userInfo);
    }
}
