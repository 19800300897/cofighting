package com.thlxgskccx.dao;

import com.thlxgskccx.model.Patient;
import com.thlxgskccx.model.PatientInfo;
import com.thlxgskccx.model.User;
import com.thlxgskccx.model.UserTrack;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface UserDao {
    List<User> getAllUser();
    void addTrack(UserTrack userTrack);
    List<UserTrack> getAllUserTrack(String userphone);
    List<PatientInfo> getAllPatient();
    public void addpatient(Patient patient);
}
