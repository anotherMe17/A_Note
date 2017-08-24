package com.anotherme17.anothernote.action;

import com.anotherme17.anothernote.entity.UserEntity;
import com.anotherme17.anothernote.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/8/24.
 */
@Controller
@RequestMapping(value = "user")
public class UserAction {

    @Autowired
    UserMapper mUserMapper;

    @ResponseBody
    @RequestMapping(value = "/createUser")
    public String createUser() {
        UserEntity user = new UserEntity("123", "456", "789", "11");

        mUserMapper.insertUserAutoKey(user);

        return "Success";
    }
}
