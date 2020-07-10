package com.thlxgskccx.service;

import com.thlxgskccx.model.PatientInfo;
import com.thlxgskccx.model.User;
import com.thlxgskccx.model.UserTrack;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    void addTrack(UserTrack userTrack);
    List<UserTrack> getAllUserTrack(String userphone);
    List<PatientInfo> getAllPatient();
}
