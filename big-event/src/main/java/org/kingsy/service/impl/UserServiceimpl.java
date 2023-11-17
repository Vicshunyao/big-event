package org.kingsy.service.impl;

import org.kingsy.mapper.UserMapper;
import org.kingsy.pojo.User;
import org.kingsy.service.UserService;
import org.kingsy.utils.Md5Util;
import org.kingsy.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceimpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUserName(String username) {
      User u1 =  userMapper.findeByUserName(username);
        return u1;
    }

    @Override
    public void register(String username, String password) {
//        加密
       String md5String= Md5Util.getMD5String(password);
//       添加
        userMapper.add(username,md5String);


    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
       Map<String,Object> map = ThreadLocalUtil.get();
      Integer id= (Integer) map.get("id");
        userMapper.updaAvatar(avatarUrl,id);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id= (Integer) map.get("id");
        userMapper.updatePws(Md5Util.getMD5String(newPwd),id);
    }
}
