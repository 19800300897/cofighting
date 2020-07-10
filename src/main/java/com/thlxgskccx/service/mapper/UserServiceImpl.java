package com.thlxgskccx.service.mapper;

import com.thlxgskccx.dao.UserDao;
import com.thlxgskccx.model.PatientInfo;
import com.thlxgskccx.model.User;
import com.thlxgskccx.model.UserTrack;
import com.thlxgskccx.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserDao userDao;

    @Override
    public List<User> getAllUser(){
        return userDao.getAllUser();
    }

    @Override
    public void addTrack(UserTrack userTrack) {
        userDao.addTrack(userTrack);
    }

    @Override
    public List<UserTrack> getAllUserTrack(String userphone) {
        return userDao.getAllUserTrack(userphone);
    }

    @Override
    public List<PatientInfo> getAllPatient() {
        return userDao.getAllPatient();
    }

}
