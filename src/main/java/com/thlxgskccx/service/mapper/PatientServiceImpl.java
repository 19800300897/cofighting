package com.thlxgskccx.service.mapper;

import com.thlxgskccx.dao.PatientDao;
import com.thlxgskccx.dao.UserDao;
import com.thlxgskccx.model.Patient;
import com.thlxgskccx.service.PatientService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PatientServiceImpl implements PatientService {
    @Resource
    private UserDao userDao;

    @Override
    public void addpatient(Patient patient){
        userDao.addpatient(patient);
    }
}
