package cn.zephyr.mapper;

import cn.zephyr.thrift.user.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: zephyrLai
 * @Description: TODO
 * @Date: 2019/4/10 14:02
 */
@Mapper
public interface UserMapper {

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    @Select("select id,username,password,real_name as realName ,mobile,email " +
            "from pe_user where id = #{id}")
    UserInfo getUserById(@Param("id")Integer id);

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    @Select("select id,username,password,real_name as realName ,mobile,email " +
            "from pe_user where username = #{username}")
    UserInfo getUserByUsername(@Param("username") String username);

    /**
     * 用户注册
     * @param userInfo
     */
    @Insert("insert into pe_user (username,password,real_name,mobile,email)" +
            "values (#{u.username},#{u.password},#{u.realName},#{u.mobile},#{u.email})")
    void registerUser(@Param("u") UserInfo userInfo);


    /**
     * 根据id获取教师信息
     * @param id
     * @return
     */
    @Select("select u.id,u.username,u.password,u.real_name as realName," +
            "u.mobile,u.email,t.intro,t.stars from pe_user u," +
            "pe_teacher t where u.id=#{id} " +
            "and u.id=t.user_id")
    UserInfo getTeacherById(@Param("id")int id);
}
