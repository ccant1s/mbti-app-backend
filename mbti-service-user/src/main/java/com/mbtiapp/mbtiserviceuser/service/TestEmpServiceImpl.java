package com.mbtiapp.mbtiserviceuser.service;

import com.mbtiapp.mbticommon.Res.TestEmpModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TestEmpServiceImpl implements TestEmpService{

    List<TestEmpModel> employees = new ArrayList<>();

    @Override
    public TestEmpModel addEmp(TestEmpModel param) {
        employees.add(param);
        return param;
    }

    @Override
    public List<TestEmpModel> listEmp() {
        return employees;
    }
}
