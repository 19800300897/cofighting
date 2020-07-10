package com.thlxgskccx.dao;

import com.thlxgskccx.model.Patient;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public interface PatientDao {
    @Autowired
    public void addpatient(Patient patient);
}
